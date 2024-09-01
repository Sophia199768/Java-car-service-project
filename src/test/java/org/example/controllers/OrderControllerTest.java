package org.example.controllers;

import org.example.application.controllers.OrderController;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.FilterOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;

import org.example.service.Exception.Exceptions;
import org.example.service.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    public OrderControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create order - should create an order successfully when valid request is provided")
    void createCar() throws Exceptions {
        CreateOrderRequest request = new CreateOrderRequest();
        // Call the method
        orderController.createOrder(request);
        // Verify the service method was called
        verify(orderService).createOrder(request);
    }

    @Test
    @DisplayName("Update order - should update the order successfully when valid request is provided")
    void updateOrder() throws Exceptions {
        UpdateOrderRequest request = new UpdateOrderRequest();
        // Call the method
        orderController.updateOrder(request);
        // Verify the service method was called
        verify(orderService).updateOrder(request);
    }

    @Test
    @DisplayName("Cancel order - should cancel the order successfully when valid id is provided")
    void cancelOrder() throws Exceptions {
        Integer id = 1;
        // Call the method
        orderController.cancelOrder(id);
        // Verify the service method was called
        verify(orderService).cancelOrder(id);
    }

    @Test
    @DisplayName("Filter orders - should return a list of orders when valid filter request is provided")
    void filterOrders() throws Exceptions {
        FilterOrderRequest request = new FilterOrderRequest();
        List<ShowOrderResponse> expectedResponse = List.of(new ShowOrderResponse());
        when(orderService.filter(any(FilterOrderRequest.class))).thenReturn(expectedResponse);
        // Call the method
        List<ShowOrderResponse> response = orderController.filterOrders(request).getBody();
        // Assert the response
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Read orders - should return a list of all orders")
    void read() throws Exceptions {
        List<ShowOrderResponse> expectedResponse = List.of(new ShowOrderResponse());
        when(orderService.read()).thenReturn(expectedResponse);
        // Call the method
        List<ShowOrderResponse> response = orderController.read().getBody();
        // Assert the response
        assertEquals(expectedResponse, response);
    }
}
