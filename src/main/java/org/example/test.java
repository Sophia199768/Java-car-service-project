package org.example;

import org.example.core.model.car.Car;
import org.example.service.Exception.Exceptions;
import org.example.service.repository.CarRepository;


import java.util.Date;

public class test {
    public static void main(String[] args) throws Exceptions {
        CarRepository carRepository = new CarRepository();
        Car car = Car.builder()
                .build();

        carRepository.create(car);
    }
}
