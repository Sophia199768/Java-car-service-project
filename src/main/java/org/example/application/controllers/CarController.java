package org.example.application.controllers;

import lombok.RequiredArgsConstructor;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.exception.Exceptions;
import org.example.service.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller for managing car operations.
 * This controller provides a REST API for creating, updating, deleting, and filtering cars.
 */
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    /**
     * Creates a new car.
     *
     * @param createCarRequest the request containing the details for creating a car.
     * @throws Exceptions if an error occurs during the car creation process.
     */
    @PostMapping
    public ResponseEntity<ShowCarResponse> createCar(@RequestBody CreateCarRequest createCarRequest) throws Exceptions {
        return new ResponseEntity<>(carService.createCar(createCarRequest), HttpStatus.OK);
    }

    /**
     * Updates an existing car.
     *
     * @param updateCarRequest the request containing the updated car details.
     * @throws Exceptions if an error occurs during the car update process.
     */
    @PutMapping
    public ResponseEntity<ShowCarResponse> updateCar(@RequestBody UpdateCarRequest updateCarRequest) throws Exceptions {
        return new ResponseEntity<>(carService.updateCar(updateCarRequest), HttpStatus.OK);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the car to delete.
     * @throws Exceptions if an error occurs during the car deletion process.
     */
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Integer id) throws Exceptions {
        carService.deleteCar(id);
    }

    /**
     * Filters cars based on specified criteria.
     *
     * @param filterCarRequest the request containing the filtering criteria.
     * @return a list of cars that match the filtering criteria.
     * @throws Exceptions if an error occurs during the car filtering process.
     */
    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<List<ShowCarResponse>> filterCars(@RequestBody FilterCarRequest filterCarRequest) throws Exceptions {
        return new ResponseEntity<>(carService.filter(filterCarRequest), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all cars.
     *
     * @return a list of all cars.
     * @throws Exceptions if an error occurs during the car retrieval process.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ShowCarResponse>> read() throws Exceptions {
        return new ResponseEntity<>(carService.read(), HttpStatus.OK);
    }
}
