package org.example.controller;

import org.example.Exception.Exceptions;
import org.example.responsesAndRequestes.order.CreateOrderRequest;
import org.example.responsesAndRequestes.order.FilterOrderRequest;
import org.example.responsesAndRequestes.order.ShowOrderResponse;
import org.example.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
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
    void createOrder() throws Exceptions {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        doNothing().when(orderService).createOrder(createOrderRequest);

        orderController.createOrder(createOrderRequest);

        verify(orderService, times(1)).createOrder(createOrderRequest);
    }

    @Test
    void updateOrder() throws Exceptions {
        UpdateOrderRequest request = new UpdateOrderRequest();
        doNothing().when(orderService).updateOrder(request);

        orderController.updateOrder(request);

        verify(orderService, times(1)).updateOrder(request);
    }

    @Test
    void cancelOrder() throws Exceptions {
        doNothing().when(orderService).cancelOrder(1);

        orderController.cancelOrder(1);

        verify(orderService, times(1)).cancelOrder(1);
    }

    @Test
    void readAllOrders() {
        List<ShowOrderResponse> expectedResponse = Collections.singletonList(new ShowOrderResponse());
        when(orderService.read()).thenReturn(expectedResponse);

        List<ShowOrderResponse> actualResponse = orderController.readAllOrders();

        assertEquals(expectedResponse, actualResponse);
        verify(orderService, times(1)).read();
    }


    @Test
    void getFilterOrders() {
        FilterOrderRequest request = new FilterOrderRequest();
        List<ShowOrderResponse> expectedResponse = Collections.singletonList(new ShowOrderResponse());
        when(orderService.filter(request))
                .thenReturn(expectedResponse);

        List<ShowOrderResponse> actualResponse = orderController.getFilterOrders(request);

        assertEquals(expectedResponse, actualResponse);
        verify(orderService, times(1)).filter(request);
    }
}
