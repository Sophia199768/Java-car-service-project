package org.example.service.service;

import org.example.annotations.Loggable;
import org.example.core.model.car.Car;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.mapper.CarMapper;
import org.example.service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CarService class provides functionality for managing car data within the application.
 */
@Loggable
@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Creates a new car entry in the repository based on the provided CarRequest.
     *
     * @param car The CarRequest object containing car details to be created.
     */
    public void createCar(CreateCarRequest car) throws Exceptions {
        carRepository.create(CarMapper.INSTANCE.toCar(car));
    }


    /**
     * Processes a car update order and throws an exception if there is not such car.
     *
     * @param request The UpdateCarRequest object representing to update.
     * @throws Exceptions If there is no such car.
     */
    public void updateCar(UpdateCarRequest request) throws Exceptions {
        Car car = carRepository.read().stream().filter(it -> it.getId().equals(request.getId())).findFirst().orElse(null);

        if (car == null) {
            throw new Exceptions("There is no such car");
        }

        carRepository.update(car);
    }


    /**
     * Delete a car entry in the repository based on the provided Id.
     *
     * @param id The Integer object containing car details to be deleted.
     */
    public void deleteCar(Integer id) throws Exceptions {
        Car car = carRepository.read().stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
        if (car == null) throw new Exceptions("There is no such car");
        carRepository.delete(car);
    }

    /**
     * Retrieves a list of all cars in the repository and maps them to CarResponse objects.
     *
     * @return A list of CarResponse objects representing all cars in the repository.
     */
    public List<ShowCarResponse> read() throws Exceptions {
        List<Car> cars = carRepository.read();

        List<ShowCarResponse> showCarResponse = new ArrayList<>();

        for (Car car : cars) {
            showCarResponse.add(CarMapper.INSTANCE.toCarResponse(car));
        }

        return showCarResponse;
    }

    /**
     * Filters the list of cars based on the specified criteria.
     *
     * @param request The FilterCarRequest where information is written what we need to filter.
     * @return A list of ShowCarResponse objects that match the filter criteria.
     */
    public List<ShowCarResponse> filter(FilterCarRequest request) throws Exceptions {
        List<Car> answer = carRepository.read();

        if (request.getCarBrand() != null) {
            answer = answer.stream().filter(car -> car.getCarBrand().equals(request.getCarBrand())).toList();
        }

        if (request.getCarModel() != null) {
            answer = answer.stream().filter(car -> car.getCarModel().equals(request.getCarModel())).toList();
        }

        if (request.getReleaseYear() != null) {
            answer = answer.stream().filter(car -> car.getReleaseYear().equals(request.getReleaseYear())).toList();
        }

        if (request.getCondition() != null) {
            answer = answer.stream().filter(car -> car.getCondition().equals(request.getCondition())).toList();
        }

        if (request.getPrice() != null) {
            answer = answer.stream().filter(car -> car.getPrice().equals(request.getPrice())).toList();
        }

        return answer.stream().map(CarMapper.INSTANCE::toCarResponse).collect(Collectors.toList());
    }
}
