package org.example.repository;

import org.example.core.model.car.Car;
import org.example.service.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("create should add a new car to the repository")
    void create_shouldAddNewCarToRepository() {
        Car car = Car.builder()
                .carBrand("Toyota")
                .carModel("Camry")
                .releaseYear(new Date(1))
                .condition("New")
                .price(30000L)
                .build();

        carRepository.create(car);

        List<Car> cars = carRepository.read();
        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
    }

    @Test
    @DisplayName("update should modify an existing car in the repository")
    void update_shouldModifyExistingCarInRepository() {
        Car car = Car.builder()
                .carBrand("Toyota")
                .carModel("Camry")
                .releaseYear(new Date(1))
                .condition("New")
                .price(30000L)
                .build();

        carRepository.create(car);

        Car updatedCar = Car.builder()
                .id(1)
                .carBrand("Toyota")
                .carModel("Camry")
                .releaseYear(new Date(1))
                .condition("Used")
                .price(28000L)
                .build();

        carRepository.update(updatedCar);

        List<Car> cars = carRepository.read();
        assertEquals(1, cars.size());
        assertEquals(updatedCar, cars.get(0));
    }

    @Test
    @DisplayName("read should return all cars in the repository")
    void read_shouldReturnAllCarsInRepository() {
        Car car1 = Car.builder()
                .carBrand("Toyota")
                .carModel("Camry")
                .releaseYear(new Date(1))
                .condition("New")
                .price(30000L)
                .build();

        Car car2 = Car.builder()
                .carBrand("Honda")
                .carModel("Accord")
                .releaseYear(new Date(1))
                .condition("Used")
                .price(25000L)
                .build();

        carRepository.create(car1);
        carRepository.create(car2);

        List<Car> cars = carRepository.read();
        assertEquals(2, cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
    }

    @Test
    @DisplayName("delete should remove a car from the repository")
    void delete_shouldRemoveCarFromRepository() {
        Car car = Car.builder()
                .carBrand("Toyota")
                .carModel("Camry")
                .releaseYear(new Date(1))
                .condition("New")
                .price(30000L)
                .build();

        carRepository.create(car);

        carRepository.delete(car);

        List<Car> cars = carRepository.read();
        assertEquals(0, cars.size());
    }
}
