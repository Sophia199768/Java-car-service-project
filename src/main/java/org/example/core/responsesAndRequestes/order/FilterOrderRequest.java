package org.example.core.responsesAndRequestes.order;

import lombok.*;

import java.util.Date;

/**
 * The {@code FilterOrderRequest} class represents a request to filter orders
 * based on specified criteria such as user ID, car ID, status, and date.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterOrderRequest {
    /**
     * The unique identifier of the user whose orders are to be filtered.
     */
    private Integer userId;

    /**
     * The unique identifier of the car associated with the orders to be filtered.
     */
    private Integer carId;

    /**
     * The status of the orders to be filtered (e.g., pending, completed).
     */
    private String status;

    /**
     * The date of the orders to be filtered.
     */
    private Date date;
}
