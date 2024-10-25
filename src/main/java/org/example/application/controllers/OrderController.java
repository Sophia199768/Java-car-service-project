package org.example.application.controllers;

import lombok.RequiredArgsConstructor;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.FilterOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.exception.Exceptions;
import org.example.service.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing order operations.
 * This controller provides a REST API for creating, updating, canceling, and filtering orders.
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * Creates a new order.
     *
     * @param createOrderRequest the request containing the details for creating an order.
     * @throws Exceptions if an error occurs during the order creation process.
     */
    @PostMapping("/create")
    public ResponseEntity<ShowOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws Exceptions {
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest), HttpStatus.OK);
    }

    /**
     * Updates an existing order.
     *
     * @param updateOrderRequest the request containing the updated order details.
     * @throws Exceptions if an error occurs during the order update process.
     */
    @PutMapping
    public ResponseEntity<ShowOrderResponse> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest) throws Exceptions {
        return new ResponseEntity<>(orderService.updateOrder(updateOrderRequest), HttpStatus.OK);
    }

    /**
     * Cancels an order by its ID.
     *
     * @param id the ID of the order to cancel.
     * @throws Exceptions if an error occurs during the order cancellation process.
     */
    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable("id") Integer id) throws Exceptions {
        orderService.cancelOrder(id);
    }

    /**
     * Filters orders based on specified criteria.
     *
     * @param filterOrderRequest the request containing the filtering criteria.
     * @return a list of orders that match the filtering criteria.
     * @throws Exceptions if an error occurs during the order filtering process.
     */
    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<List<ShowOrderResponse>> filterOrders(@RequestBody FilterOrderRequest filterOrderRequest) throws Exceptions {
        return new ResponseEntity<>(orderService.filter(filterOrderRequest), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return a list of all orders.
     * @throws Exceptions if an error occurs during the order retrieval process.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ShowOrderResponse>> read() throws Exceptions {
        return new ResponseEntity<>(orderService.read(), HttpStatus.OK);
    }
}
