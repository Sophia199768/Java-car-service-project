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
        Order order = new Order();
        order.setCarId(1);
        order.setUserId(1);

        orderRepository.create(order);

        List<Order> orders = orderRepository.read();
        assertEquals(2, orders.size());
    }

    @Test
    @DisplayName("read should return all orders in the repository")
    void read_shouldReturnAllOrdersInRepository() throws Exceptions {
        Order order1 = new Order();
        order1.setCarId(1);
        order1.setUserId(1);
        order1.setInformation("Order 1");

        Order order2 = new Order();
        order2.setCarId(2);
        order2.setUserId(2);
        order2.setInformation("Order 2");

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
        Order order = new Order();
        order.setCarId(1);
        order.setUserId(1);
        order.setInformation("Initial Value");


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
        Order order = new Order();
        order.setCarId(1);
        order.setUserId(1);
        order.setInformation("Test Order");

        order = orderRepository.create(order);

        orderRepository.delete(order);

        List<Order> orders = orderRepository.read();
        assertEquals(1, orders.size());
    }
}
