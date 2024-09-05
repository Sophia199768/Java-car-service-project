package org.example.core.responsesAndRequestes.car;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * The {@code ShowCarResponse} class represents the response containing details of a car
 * that is displayed to the user, including its identifier, brand, model, release year, condition, and price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowCarResponse {
    /**
     * The unique identifier of the car.
     */
    private Integer id;

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
    private LocalDate releaseYear;

    /**
     * The condition of the car (e.g., new, used).
     */
    private String condition;

    /**
     * The price of the car.
     */
    private Long price;
}
