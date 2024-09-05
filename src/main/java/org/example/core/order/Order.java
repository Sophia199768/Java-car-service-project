package org.example.core.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Represents an order in the system with details such as date, user, car, status, and additional information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    /**
     * Unique identifier for the order.
     */
    private Integer id;

    /**
     * Date when the order was placed.
     */
    private LocalDate date;

    /**
     * Identifier of the user who placed the order.
     */
    private Integer userId;

    /**
     * Identifier of the car associated with the order.
     */
    private Integer carId;

    /**
     * Status of the order (e.g., pending, completed).
     */
    private String status;

    /**
     * Additional information related to the order.
     */
    private String information;
}
