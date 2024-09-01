package org.example.service.validation;

import org.example.core.responsesAndRequestes.car.CreateCarRequest;

import java.time.LocalDate;
import java.util.Date;

/**
 * The CarValidation class supports basic validation for the car release year.
 */
public class CarValidation {
    /**
     * Check the year
     *
     * @param request the CreateCarRequest object containing cars release year.
     */
    public Boolean valid(CreateCarRequest request) {
        return request.getReleaseYear().isBefore(LocalDate.now());
    }
}
