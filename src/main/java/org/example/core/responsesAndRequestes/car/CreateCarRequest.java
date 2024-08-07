package org.example.core.responsesAndRequestes.car;

import lombok.*;

import java.util.Date;

/**
 * The {@code CreateCarRequest} class represents a request to create a new car
 * with specified details such as brand, model, release year, condition, and price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    /**
     * The brand of the car.
     */
    private String carBrand;

    /**
     * The model of the car.
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
}
