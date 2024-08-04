package org.example.repository;

import org.example.order.Order;
import org.junit.jupiter.api.BeforeEach;
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
    void create() {
        Order order = new Order();
        orderRepository.create(order);

        List<Order> orders = orderRepository.read();
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
        assertEquals(1, order.getId());
    }

    @Test
    void read() {
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
    void update() {
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
    void delete() {
        Order order = new Order();
        orderRepository.create(order);

        orderRepository.delete(order);

        List<Order> orders = orderRepository.read();
        assertEquals(0, orders.size());
    }
}
