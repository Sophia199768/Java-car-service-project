package org.example.application.controller;

import lombok.RequiredArgsConstructor;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.CarService;


import java.util.List;

/**
 * The {@code CarController} class provides methods to manage operations Create, Update, Delete, and Retrieve cars.
 */
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    /**
     * Updates the details of an existing car.
     *
     * @param request the request containing the car details to update
     * @throws Exceptions if an error occurs during the update
     */
    public void update(UpdateCarRequest request) throws Exceptions {
        carService.updateCar(request);
    }

    /**
     * Deletes a car by its identifier.
     *
     * @param id the identifier of the car to delete
     * @throws Exceptions if an error occurs during the deletion
     */
    public void deleteCar(Integer id) throws Exceptions {
        carService.deleteCar(id);
    }

    /**
     * Creates a new car with the specified details.
     *
     * @param car the request containing the details of the car to create
     */
    public void createNewCar(CreateCarRequest car) {
        carService.createCar(car);
    }

    /**
     * Retrieves a list of all cars.
     * @return a list of all cars
     */
    public List<ShowCarResponse> readCars() {
        return carService.read();
    }

    /**
     * Retrieves a list of cars that match the specified filter criteria.
     * @param request the request containing the filter criteria
     * @return a list of cars that match the filter criteria
     */
    public List<ShowCarResponse> getFilterCars(FilterCarRequest request) {
        return carService.filter(request);
    }
}
