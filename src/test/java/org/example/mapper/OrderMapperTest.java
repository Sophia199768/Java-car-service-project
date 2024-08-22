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
        Order newOrder = new Order();
        newOrder.setId(1);
        newOrder.setStatus("Done");
        newOrder.setInformation("CarCreated");
        newOrder.setCarId(1);
        newOrder.setUserId(1);
        newOrder.setDate(new Date(1));

        ShowOrderResponse expectedResponse = new ShowOrderResponse();
        expectedResponse.setId(1);
        expectedResponse.setCarId(1);
        expectedResponse.setUserId(1);
        expectedResponse.setInformation("CarCreated");

        ShowOrderResponse actualResponse = OrderMapper.INSTANCE.toOrderResponse(newOrder);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should correctly map CreateOrderRequest to Order")
    void toOrder_shouldReturnCorrectOrder_whenCreateOrderRequestIsProvided() {
        Order expectedOrder = new Order();
        expectedOrder.setId(1);
        expectedOrder.setStatus("Done");
        expectedOrder.setInformation("CarCreated");
        expectedOrder.setCarId(1);
        expectedOrder.setUserId(1);
        expectedOrder.setDate(new Date(1));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setCarId(1);
        request.setUserId(1);
        request.setStatus("Done");
        request.setDate(new Date(1));
        request.setInformation("CarCreated");
        Order actualOrder = OrderMapper.INSTANCE.toOrder(request);
        actualOrder.setId(1);

        assertEquals(expectedOrder, actualOrder);
    }
}
