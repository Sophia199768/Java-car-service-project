package org.example.repository;

import org.example.order.Order;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The OrderRepository class supports basic CRUD operations on the order collection.
 */

public class OrderRepository {
    private final List<Order> orders = new LinkedList<>();
    private Integer nextId = 1;

    /**
     * Adds a new Order entity to the repository.
     * The Order's ID is automatically assigned and incremented.
     *
     * @param order The Order entity to be added.
     */
    public void create(Order order) {
        order.setId(nextId++);
        orders.add(order);
    }

    /**
     * Retrieves all Order entities from the repository.
     *
     * @return An unmodifiable list of all Order entities.
     */
    public List<Order> read() {
        return Collections.unmodifiableList(orders);
    }

    /**
     * Updates an existing Order entity in the repository.
     * The Order is identified by its ID, and if found, it is replaced with the updated Order.
     *
     * @param order The Order entity with updated information.
     */
    public void update(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.set(i, order);
            }
        }
    }

    /**
     * Removes an Order entity from the repository.
     *
     * @param order The Order entity to be removed.
     */
    public void delete(Order order) {
        orders.remove(order);
    }
}