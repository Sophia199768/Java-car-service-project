package org.example.service.service;

import org.example.core.order.Order;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.FilterOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.mapper.OrderMapper;
import org.example.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The OrderService class provides functionality for managing orders within the application.
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


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
        if (order == null) {
            throw new Exceptions("There is no such order");
        }

        orderRepository.update(order);
    }

    /**
     * Retrieves a list of all orders from the repository and maps them to OrderResponse objects.
     *
     * @return A list of OrderResponse objects representing all orders in the repository.
     */
    public List<ShowOrderResponse> read() throws Exceptions {
        List<Order> orders = orderRepository.read();

        List<ShowOrderResponse> ordersResponses = new ArrayList<>();

        for (Order order : orders) {
            ordersResponses.add(OrderMapper.INSTANCE.toOrderResponse(order));
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

        orderRepository.create(OrderMapper.INSTANCE.toOrder(buyCarRequest));
    }

    /**
     * Filters the list of cars based on the specified criteria.
     *
     * @param request The FilterOrderRequest where information is written what we need to filter.
     * @return A list of ShowOrderResponse objects that match the filter criteria.
     */
    public List<ShowOrderResponse> filter(FilterOrderRequest request) throws Exceptions {
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

        return answer.stream().map(OrderMapper.INSTANCE::toOrderResponse).collect(Collectors.toList());
    }
}
