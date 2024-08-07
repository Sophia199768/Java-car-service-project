package org.example.service.mapper;


import org.example.core.order.Order;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;

/**
 * The OrderMapper class is responsible for mapping between Order entities and their corresponding request and response objects
 */
public class OrderMapper {

    /**
     * Converts a Car entity to a CarResponse object
     *
     * @param order The order entity to be converted
     * @return A OrderResponse object containing the data from the Order entity
     */
    public ShowOrderResponse toOrderResponse(Order order) {
        return new ShowOrderResponse(order.getId(), order.getUserId(), order.getCarId(), order.getInformation());
    }


    /**
     * Converts a CarRequest object to a Car entity
     *
     * @param createOrderRequest The orderRequest object to be converted
     * @return A order entity containing the data from the orderRequest object
     */

    public Order toOrder(CreateOrderRequest createOrderRequest) {
        return Order.builder()
                .date(createOrderRequest.getDate())
                .carId(createOrderRequest.getCarId())
                .userId(createOrderRequest.getUserId())
                .information(createOrderRequest.getInformation())
                .status(createOrderRequest.getStatus())
                .build();
    }
 }
