package org.example.mapper;

import org.example.order.Order;
import org.example.responsesAndRequestes.order.CreateOrderRequest;
import org.example.responsesAndRequestes.order.ShowOrderResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    @Test
    void toOrderResponse() {
        OrderMapper mapper = new OrderMapper();
        Order newOrder = new Order(new Date(1), 1, 1, "Done", "CarCreated");
        newOrder.setId(1);

        ShowOrderResponse response = new ShowOrderResponse(1, 1, 1, "CarCreated");

        ShowOrderResponse carResponse = mapper.toOrderResponse(newOrder);

        assertEquals(carResponse, response);
    }

    @Test
    void toOrder() {
        OrderMapper mapper = new OrderMapper();
        Order newOrder = new Order(new Date(1), 1, 1, "Done", "CarCreated");
        newOrder.setId(1);

        CreateOrderRequest response = new CreateOrderRequest(1, 1, "CarCreated", new Date(1), "Done");
        Order order = mapper.toOrder(response);
        order.setId(1);

        assertEquals(order, newOrder);
    }
}