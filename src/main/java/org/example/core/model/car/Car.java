package org.example.core.model.car;

import lombok.*;

import java.util.Date;

/**
 * The Car class represents a car entity with attributes such as brand, model, release year, condition, and price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car implements Cloneable {
    /**
     * The unique identifier for the car.
     */
    private Integer id;

    /**
     * The brand of the car (e.g., Toyota, Ford).
     */
    private String carBrand;

    /**
     * The model of the car (e.g., Corolla, Mustang).
     */
    private String carModel;

    /**
     * The release year of the car.
     */
    private Date releaseYear;

    /**
     * The condition of the car (e.g., new, used).
     */
    private String condition;

    /**
     * The price of the car.
     */
    private Long price;

    /**
     * Clone a Car entity to the same a Car object
     *
     * @return A Car object
     */
    @Override
    public Car clone() {
        try {
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
