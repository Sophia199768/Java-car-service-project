package org.example.repository;

import org.example.core.order.Order;
import org.example.service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    @DisplayName("create should add a new order to the repository")
    void create_shouldAddNewOrderToRepository() {
        Order order = new Order();
        orderRepository.create(order);

        List<Order> orders = orderRepository.read();
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
        assertEquals(1, order.getId());
    }

    @Test
    @DisplayName("read should return all orders in the repository")
    void read_shouldReturnAllOrdersInRepository() {
        Order order1 = new Order();
        Order order2 = new Order();
        orderRepository.create(order1);
        orderRepository.create(order2);

        List<Order> orders = orderRepository.read();
        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
    }

    @Test
    @DisplayName("update should modify an existing order in the repository")
    void update_shouldModifyExistingOrderInRepository() {
        Order order = new Order();
        orderRepository.create(order);

        Order updatedOrder = new Order();
        updatedOrder.setId(order.getId());
        updatedOrder.setInformation("Updated Value");
        orderRepository.update(updatedOrder);

        List<Order> orders = orderRepository.read();
        assertEquals(1, orders.size());
        assertEquals(updatedOrder, orders.get(0));
    }

    @Test
    @DisplayName("delete should remove an order from the repository")
    void delete_shouldRemoveOrderFromRepository() {
        Order order = new Order();
        orderRepository.create(order);

        orderRepository.delete(order);

        List<Order> orders = orderRepository.read();
        assertEquals(0, orders.size());
    }
}
