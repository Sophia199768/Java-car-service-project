package org.example.core.order;

import lombok.*;

import java.util.Date;

/**
 * Represents an order in the system with details such as date, user, car, status, and additional information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Cloneable {
    /**
     * Unique identifier for the order.
     */
    private Integer id;

    /**
     * Date when the order was placed.
     */
    private Date date;

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

    @Override
    public Order clone() {
        try {
            return (Order) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
