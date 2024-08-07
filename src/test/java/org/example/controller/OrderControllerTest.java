package org.example.controller;

import org.example.application.controller.OrderController;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.FilterOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create new order when all details are correct")
    void createOrder_shouldCreateNewOrder_whenAllCorrect() throws Exceptions {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        doNothing().when(orderService).createOrder(createOrderRequest);

        orderController.createOrder(createOrderRequest);

        verify(orderService, times(1)).createOrder(createOrderRequest);
    }

    @Test
    @DisplayName("Should update order when all details are correct")
    void updateOrder_shouldUpdateOrder_whenAllCorrect() throws Exceptions {
        UpdateOrderRequest request = new UpdateOrderRequest();
        doNothing().when(orderService).updateOrder(request);

        orderController.updateOrder(request);

        verify(orderService, times(1)).updateOrder(request);
    }

    @Test
    @DisplayName("Should cancel order when order ID is provided")
    void cancelOrder_shouldCancelOrder_whenOrderIdIsProvided() throws Exceptions {
        doNothing().when(orderService).cancelOrder(1);

        orderController.cancelOrder(1);

        verify(orderService, times(1)).cancelOrder(1);
    }

    @Test
    @DisplayName("Should return all orders when requested")
    void readAllOrders_shouldReturnAllOrders_whenRequested() {
        List<ShowOrderResponse> expectedResponse = Collections.singletonList(new ShowOrderResponse());
        when(orderService.read()).thenReturn(expectedResponse);

        List<ShowOrderResponse> actualResponse = orderController.readAllOrders();

        assertEquals(expectedResponse, actualResponse);
        verify(orderService, times(1)).read();
    }

    @Test
    @DisplayName("Should return filtered orders when filter criteria are provided")
    void getFilterOrders_shouldReturnFilteredOrders_whenFilterCriteriaAreProvided() {
        FilterOrderRequest request = new FilterOrderRequest();
        List<ShowOrderResponse> expectedResponse = Collections.singletonList(new ShowOrderResponse());
        when(orderService.filter(request)).thenReturn(expectedResponse);

        List<ShowOrderResponse> actualResponse = orderController.getFilterOrders(request);

        assertEquals(expectedResponse, actualResponse);
        verify(orderService, times(1)).filter(request);
    }
}
