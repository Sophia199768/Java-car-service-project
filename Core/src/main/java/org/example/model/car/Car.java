package org.example.model.car;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * The Car class represents a car entity with attributes such as brand, model, release year, condition, and price.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
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
     * Constructs a new Car object with the specified details.
     *
     * @param carBrand    The brand of the car.
     * @param carModel    The model of the car.
     * @param releaseYear The release year of the car.
     * @param condition   The condition of the car.
     * @param price       The price of the car.
     */
    public Car(String carBrand, String carModel, Date releaseYear, String condition, Long price) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.releaseYear = releaseYear;
        this.condition = condition;
        this.price = price;
    }


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
