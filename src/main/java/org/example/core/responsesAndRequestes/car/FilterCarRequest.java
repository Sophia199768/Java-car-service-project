package org.example.core.responsesAndRequestes.car;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * The {@code FilterCarRequest} class represents a request to filter cars
 * based on various criteria such as brand, model, release year, condition, and price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterCarRequest {
    /**
     * The brand of the car to filter by.
     */
    private String carBrand;

    /**
     * The model of the car to filter by.
     */
    private String carModel;

    /**
     * The release year of the car to filter by.
     */
    private LocalDate releaseYear;

    /**
     * The condition of the car to filter by.
     */
    private String condition;

    /**
     * The price of the car to filter by.
     */
    private Long price;
}
