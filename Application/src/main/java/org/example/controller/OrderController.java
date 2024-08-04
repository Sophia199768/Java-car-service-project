package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Exception.Exceptions;
import org.example.responsesAndRequestes.order.CreateOrderRequest;
import org.example.responsesAndRequestes.order.FilterOrderRequest;
import org.example.responsesAndRequestes.order.ShowOrderResponse;
import org.example.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.OrderService;

import java.util.List;

/**
 * The {@code OrderController} class provides methods to manage operations Create, Update, Delete, and Retrieve orders.
 */
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * Creates a new order with the specified details.
     *
     * @param request the request containing the details of the order to create
     * @throws Exceptions if an error occurs during the creation
     */
    public void createOrder(CreateOrderRequest request) throws Exceptions {
        orderService.createOrder(request);
    }

    /**
     * Updates the details of an existing order.
     *
     * @param request the request containing the order details to update
     * @throws Exceptions if an error occurs during the update
     */
    public void updateOrder(UpdateOrderRequest request) throws Exceptions {
        orderService.updateOrder(request);
    }

    /**
     * Cancels an order by its identifier.
     *
     * @param id the identifier of the order to cancel
     * @throws Exceptions if an error occurs during the cancellation
     */
    public void cancelOrder(Integer id) throws Exceptions {
        orderService.cancelOrder(id);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return a list of all orders
     */
    public List<ShowOrderResponse> readAllOrders() {
        return orderService.read();
    }

    /**
     * Retrieves a list of orders that match the specified filter criteria.
     *
     * @param request the request containing the filter criteria
     * @return a list of orders that match the filter criteria
     */
    public List<ShowOrderResponse> getFilterOrders(FilterOrderRequest request) {
        return orderService.filter(request);
    }
}
