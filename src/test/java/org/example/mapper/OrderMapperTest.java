package org.example.mapper;

import org.example.core.order.Order;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.service.mapper.OrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    @Test
    @DisplayName("Should correctly map Order to ShowOrderResponse")
    void toOrderResponse_shouldReturnCorrectShowOrderResponse_whenOrderIsProvided() {
        OrderMapper mapper = new OrderMapper();
        Order newOrder = Order.builder()
                .id(1)
                .status("Done")
                .information("CarCreated")
                .carId(1)
                .userId(1)
                .date(new Date(1))
                .build();

        ShowOrderResponse expectedResponse = new ShowOrderResponse(1, 1, 1, "CarCreated");
        ShowOrderResponse actualResponse = mapper.toOrderResponse(newOrder);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should correctly map CreateOrderRequest to Order")
    void toOrder_shouldReturnCorrectOrder_whenCreateOrderRequestIsProvided() {
        OrderMapper mapper = new OrderMapper();
        Order expectedOrder = Order.builder()
                .id(1)
                .status("Done")
                .information("CarCreated")
                .carId(1)
                .userId(1)
                .date(new Date(1))
                .build();

        CreateOrderRequest request = new CreateOrderRequest(1, 1, "CarCreated", new Date(1), "Done");
        Order actualOrder = mapper.toOrder(request);
        actualOrder.setId(1);

        assertEquals(expectedOrder, actualOrder);
    }
}
