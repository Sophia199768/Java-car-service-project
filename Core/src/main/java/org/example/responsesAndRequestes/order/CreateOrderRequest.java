package org.example.responsesAndRequestes.order;

import lombok.*;

import java.util.Date;

/**
 * The {@code CreateOrderRequest} class represents a request to create a new order
 * with specified details such as user ID, car ID, additional information, date, and status.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderRequest {
    /**
     * The unique identifier of the user placing the order.
     */
    private Integer userId;

    /**
     * The unique identifier of the car being ordered.
     */
    private Integer carId;

    /**
     * Additional information related to the order.
     */
    private String information;

    /**
     * The date when the order is created.
     */
    private Date date;

    /**
     * The status of the order (e.g., pending, completed).
     */
    private String status;
}
