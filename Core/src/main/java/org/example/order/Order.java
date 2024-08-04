package org.example.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Represents an order in the system with details such as date, user, car, status, and additional information.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
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

    /**
     * Constructs a new Order with specified date, user ID, car ID, status, and information.
     *
     * @param date        The date when the order was placed.
     * @param userId      The ID of the user who placed the order.
     * @param carId       The ID of the car associated with the order.
     * @param status      The status of the order.
     * @param information Additional information related to the order.
     */
    public Order(Date date, Integer userId, Integer carId, String status, String information) {
        this.date = date;
        this.userId = userId;
        this.carId = carId;
        this.status = status;
        this.information = information;
    }

    @Override
    public Order clone() {
        try {
            return (Order) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
