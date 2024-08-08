package org.example.service;

import org.example.core.order.Order;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.FilterOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.mapper.OrderMapper;
import org.example.service.repository.OrderRepository;
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

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createOrder should create order when request is valid")
    void createOrder_shouldCreateOrder_whenRequestIsValid() throws Exceptions {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        Order order = new Order();
        when(orderMapper.toOrder(createOrderRequest)).thenReturn(order);
        doNothing().when(orderRepository).create(order);

        orderService.createOrder(createOrderRequest);

        verify(orderRepository, times(1)).create(order);
    }

    @Test
    @DisplayName("cancelOrder should delete order when order exists")
    void cancelOrder_shouldDeleteOrder_whenOrderExists() throws Exceptions {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.read()).thenReturn(List.of(order));
        doNothing().when(orderRepository).delete(order);

        orderService.cancelOrder(1);

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    @DisplayName("updateOrder should update order status when order exists")
    void updateOrder_shouldUpdateStatus_whenOrderExists() throws Exceptions {
        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setId(1);
        Order order = new Order();
        order.setId(1);
        when(orderRepository.read()).thenReturn(List.of(order));
        doNothing().when(orderRepository).update(order);

        orderService.updateOrder(request);

        verify(orderRepository, times(1)).update(order);
    }

    @Test
    @DisplayName("read should return list of orders")
    void read_shouldReturnListOfOrders() {
        Order order = new Order();
        ShowOrderResponse showOrderResponse = new ShowOrderResponse();
        when(orderRepository.read()).thenReturn(Collections.singletonList(order));
        when(orderMapper.toOrderResponse(order)).thenReturn(showOrderResponse);

        List<ShowOrderResponse> showOrderResponses = orderService.read();

        assertEquals(1, showOrderResponses.size());
        assertEquals(showOrderResponse, showOrderResponses.get(0));
        verify(orderRepository, times(1)).read();
    }

    @Test
    @DisplayName("filter should return list of orders based on filter criteria")
    void filter_shouldReturnOrdersBasedOnCriteria() {
        Order order = Order.builder()
                .status("status")
                .carId(1)
                .userId(1)
                .date(null)
                .build();
        ShowOrderResponse showOrderResponse = new ShowOrderResponse();
        when(orderRepository.read()).thenReturn(List.of(order));
        when(orderMapper.toOrderResponse(order)).thenReturn(showOrderResponse);

        List<ShowOrderResponse> showOrderResponses = orderService.filter(new FilterOrderRequest(null, 1, "status", null));

        assertEquals(1, showOrderResponses.size());
        assertEquals(showOrderResponse, showOrderResponses.get(0));
        verify(orderRepository, times(1)).read();
    }
}
