package org.example.service;

import org.example.Exception.Exceptions;
import org.example.mapper.OrderMapper;
import org.example.order.Order;
import org.example.repository.OrderRepository;
import org.example.responsesAndRequestes.order.*;
import org.junit.jupiter.api.BeforeEach;
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
    void createOrder() throws Exceptions {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        Order order = new Order();
        when(orderMapper.toOrder(createOrderRequest)).thenReturn(order);
        doNothing().when(orderRepository).create(order);

        orderService.createOrder(createOrderRequest);

        verify(orderRepository, times(1)).create(order);
    }

    @Test
    void deleteOrder() throws Exceptions {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        Order order = new Order();
        order.setId(1);
        when(orderRepository.read()).thenReturn(List.of(order));
        when(orderMapper.toOrder(createOrderRequest)).thenReturn(order);
        doNothing().when(orderRepository).delete(order);

        orderService.cancelOrder(1);

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    void changeOrderStatus() throws Exceptions {
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
    void read() {
        Order order = new Order();
        ShowOrderResponse showOrderResponse = new ShowOrderResponse();
        when(orderRepository.read()).thenReturn(Collections.singletonList(order));
        when(orderMapper.toOrderResponse(order)).thenReturn(showOrderResponse);

        List<ShowOrderResponse> showOrderRespons = orderService.read();

        assertEquals(1, showOrderRespons.size());
        assertEquals(showOrderResponse, showOrderRespons.get(0));
        verify(orderRepository, times(1)).read();
    }


    @Test
    void filter() {
        Order order = new Order(null, 1, 1, "status", null);
        ShowOrderResponse showOrderResponse = new ShowOrderResponse();
        when(orderRepository.read()).thenReturn(List.of(order));
        when(orderMapper.toOrderResponse(order)).thenReturn(showOrderResponse);

        List<ShowOrderResponse> showOrderRespons = orderService.filter(new FilterOrderRequest(null, 1, "status", null));

        assertEquals(1, showOrderRespons.size());
        assertEquals(showOrderResponse, showOrderRespons.get(0));
        verify(orderRepository, times(1)).read();
    }
}
