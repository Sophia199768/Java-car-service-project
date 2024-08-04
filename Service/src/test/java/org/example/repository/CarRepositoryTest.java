package org.example.repository;

import org.example.model.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void create() {
        Car car = new Car( "Toyota", "Camry", new Date(1), "New", 30000L);
        carRepository.create(car);

        List<Car> cars = carRepository.read();
        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
    }

    @Test
    void update() {
        Car car = new Car("Toyota", "Camry", new Date(1), "New", 30000L);
        carRepository.create(car);

        Car updatedCar = new Car("Toyota", "Camry", new Date(1), "Used", 28000L);
        updatedCar.setId(1);
        carRepository.update(updatedCar);

        List<Car> cars = carRepository.read();
        assertEquals(1, cars.size());
        assertEquals(updatedCar, cars.get(0));
    }

    @Test
    void read() {
        Car car1 = new Car( "Toyota", "Camry",  new Date(1),"New", 30000L);
        Car car2 = new Car( "Honda", "Accord", new Date(1),"Used", 25000L);
        carRepository.create(car1);
        carRepository.create(car2);

        List<Car> cars = carRepository.read();
        assertEquals(2, cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
    }

    @Test
    void delete() {
        Car car = new Car("Toyota", "Camry", new Date(1),"New", 30000L);
        carRepository.create(car);

        carRepository.delete(car);

        List<Car> cars = carRepository.read();
        assertEquals(0, cars.size());
    }
}
