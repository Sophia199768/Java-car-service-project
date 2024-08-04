package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.Exception.Exceptions;
import org.example.mapper.OrderMapper;
import org.example.order.Order;
import org.example.repository.OrderRepository;
import org.example.responsesAndRequestes.order.CreateOrderRequest;
import org.example.responsesAndRequestes.order.FilterOrderRequest;
import org.example.responsesAndRequestes.order.ShowOrderResponse;
import org.example.responsesAndRequestes.order.UpdateOrderRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The OrderService class provides functionality for managing orders within the application.
 */
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    /**
     * Processes a cancel order and throws an exception if there is not such order.
     *
     * @param id The Integer object representing id to cansel order.
     * @throws Exceptions If there is no such order.
     */
    public void cancelOrder(Integer id) throws Exceptions {
        Order order = orderRepository.read().stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
        if (order == null) throw new Exceptions("There is no such order");
        orderRepository.delete(order);
    }


    /**
     * Processes a car update order and throws an exception if there is not such order.
     *
     * @param request The UpdateOrderRequest object representing to update.
     * @throws Exceptions If there is no such order.
     */
    public void updateOrder(UpdateOrderRequest request) throws Exceptions {
        Order order = orderRepository.read().stream().filter(it -> it.getId().equals(request.getId())).findFirst().orElse(null);
        if (order == null) throw new Exceptions("There is no such order");

        Order updatedOrder = order.clone();

        if (request.getInformation() != null)  updatedOrder.setInformation(request.getInformation());
        if (request.getStatus() != null)  updatedOrder.setStatus(request.getStatus());


        orderRepository.update(updatedOrder);
    }

    /**
     * Retrieves a list of all orders from the repository and maps them to OrderResponse objects.
     *
     * @return A list of OrderResponse objects representing all orders in the repository.
     */
    public List<ShowOrderResponse> read() {
        List<Order> orders = orderRepository.read();

        List<ShowOrderResponse> ordersResponses = new ArrayList<>();

        for (Order order : orders) {
            ordersResponses.add(orderMapper.toOrderResponse(order));
        }

        return ordersResponses;
    }

    /**
     * Processes a car purchase order and throws an exception if the car has already been bought.
     *
     * @param buyCarRequest The OrderRequest object representing the purchase order.
     * @throws Exceptions If the car has already been bought.
     */
    public void createOrder(CreateOrderRequest buyCarRequest) throws Exceptions {
        List<Order> orders = orderRepository.read();

        for (Order order : orders) {
            if (Objects.equals(order.getCarId(), buyCarRequest.getCarId()) && order.getUserId() != null) {
                throw new Exceptions("This car was bought");
            }
        }

        orderRepository.create(orderMapper.toOrder(buyCarRequest));
    }

    /**
     * Filters the list of cars based on the specified criteria.
     *
     * @param request The FilterOrderRequest where information is written what we need to filter.
     * @return A list of ShowOrderResponse objects that match the filter criteria.
     */
    public List<ShowOrderResponse> filter(FilterOrderRequest request) {
        List<Order> answer = orderRepository.read();

        if (request.getDate() != null) {
            answer = answer.stream().filter(order -> order.getDate().equals(request.getDate())).toList();
        }

        if (request.getUserId() != null) {
            answer = answer.stream().filter(order -> order.getUserId().equals(request.getUserId())).toList();
        }

        if (request.getStatus() != null) {
            answer = answer.stream().filter(order -> order.getStatus().equals(request.getStatus())).toList();
        }

        if (request.getCarId() != null) {
            answer = answer.stream().filter(order -> order.getCarId().equals(request.getCarId())).toList();
        }

        return answer.stream().map(orderMapper::toOrderResponse).collect(Collectors.toList());
    }
}
