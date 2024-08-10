package org.example.repository;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.core.order.Order;
import org.example.service.Exception.Exceptions;
import org.example.service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class OrderRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    private OrderRepository orderRepository;
    private Connection connection;

    @BeforeAll
    static void init() throws Exception {
        Class.forName("org.postgresql.Driver");
    }

    @BeforeEach
    void setUp() throws Exception {
        connection = DriverManager.getConnection(
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword()
        );

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.yml", new ClassLoaderResourceAccessor(), database);

        liquibase.update();

        orderRepository = new OrderRepository(connection);
    }

    @Test
    @DisplayName("create should add a new order to the repository")
    void create_shouldAddNewOrderToRepository() throws Exceptions {
        Order order = Order.builder()
                .userId(1)
                .carId(1)
                .build();
        orderRepository.create(order);

        List<Order> orders = orderRepository.read();
        assertEquals(2, orders.size());
    }

    @Test
    @DisplayName("read should return all orders in the repository")
    void read_shouldReturnAllOrdersInRepository() throws Exceptions {
        Order order1 = Order.builder()
                .userId(1)
                .carId(1)
                .information("Order 1")
                .build();

        Order order2 = Order.builder()
                .userId(2)
                .carId(2)
                .information("Order 2")
                .build();

        orderRepository.create(order1);
        orderRepository.create(order2);

        List<Order> orders = orderRepository.read();
        assertEquals(3, orders.size());
        assertTrue(orders.stream().anyMatch(o -> o.getInformation().equals("Order 1")));
        assertTrue(orders.stream().anyMatch(o -> o.getInformation().equals("Order 2")));
    }

    @Test
    @DisplayName("update should modify an existing order in the repository")
    void update_shouldModifyExistingOrderInRepository() throws Exceptions {
        Order order = Order.builder()
                .userId(1)
                .carId(1)
                .information("Initial Value")
                .build();


        order = orderRepository.create(order);


        order.setInformation("Updated Value");
        orderRepository.update(order);

        List<Order> orders = orderRepository.read();
        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(o -> o.getInformation().equals("Updated Value")));
    }

    @Test
    @DisplayName("delete should remove an order from the repository")
    void delete_shouldRemoveOrderFromRepository() throws Exceptions {
        Order order = Order.builder()
                .userId(1)
                .carId(1)
                .information("Test Order")
                .build();

        order = orderRepository.create(order);

        orderRepository.delete(order);

        List<Order> orders = orderRepository.read();
        assertEquals(1, orders.size());
    }
}
