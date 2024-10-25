package org.example.core.responsesAndRequestes.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The {@code ShowOrderResponse} class represents the response containing details of an order
 * that is displayed to the user, including its identifier, user ID, car ID, and additional information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowOrderResponse {
    /**
     * The unique identifier of the order.
     */
    private Integer id;

    /**
     * The unique identifier of the user who placed the order.
     */
    private Integer userId;

    /**
     * The unique identifier of the car associated with the order.
     */
    private Integer carId;

    /**
     * Additional information related to the order.
     */
    private String information;
}
