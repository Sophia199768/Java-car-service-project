package org.example.repository;

import org.example.model.car.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The CarRepository class supports basic CRUD  operations on the car collection.
 */
public class CarRepository {
    private final List<Car> cars = new ArrayList<>();
    private Integer nextId = 1;


    /**
     * Adds a new car entity to the repository.
     * @param car The Car entity to be added.
     */
    public void create(Car car) {
        car.setId(nextId++);
        cars.add(car);
    }


    /**
     * Updates an existing Car entity in the repository.
     *
     * @param car The Car entity with updated information.
     */
    public void update(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId().equals(car.getId())) {
                cars.set(i, car);
            }
        }
    }

    /**
     * Read all Car entities from the repository.
     *
     * @return An unmodifiable list of all Car entities.
     */
    public List<Car> read() {
        return Collections.unmodifiableList(cars);
    }

    /**
     * Removes a Car entity from the repository.
     *
     * @param car The Car entity to be removed.
     */
    public void delete(Car car) {
        cars.remove(car);
    }
}
