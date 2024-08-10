package org.example.service.repository;


import org.example.core.model.car.Car;
import org.example.service.Exception.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The CarRepository class supports basic CRUD  operations on the car collection.
 */
public class CarRepository {

    private final Connection connection;

    public CarRepository() {
        try {
            DataBaseConfig config = new DataBaseConfig();
            connection = config.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CarRepository(Connection connection) {
        this.connection = connection;
    }


    /**
     * Adds a new car entity to the repository.
     * @param car The Car entity to be added.
     */
    public Car create(Car car) throws Exceptions {
        String sql = "INSERT INTO objects.cars (car_brand, car_model, release_year, condition, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarModel());
            preparedStatement.setObject(3, car.getReleaseYear(), Types.TIMESTAMP);
            preparedStatement.setString(4, car.getCondition());
            preparedStatement.setObject(5, car.getPrice(), Types.BIGINT);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT last_value FROM objects.cars_car_id_seq")) {
            if (resultSet.next()) {
                car.setId(resultSet.getInt(1));
            } else {
                throw new SQLException("Creating car failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return car;
    }


    /**
     * Updates an existing Car entity in the repository.
     *
     * @param car The Car entity with updated information.
     */
    public void update(Car car) throws Exceptions {
        String sql = "UPDATE objects.cars SET car_brand = ?, car_model = ?, release_year = ?, condition = ?, price = ? WHERE car_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarModel());
            preparedStatement.setObject(3, car.getReleaseYear(), Types.TIMESTAMP);
            preparedStatement.setString(4, car.getCondition());
            preparedStatement.setObject(    5, car.getPrice(), Types.BIGINT);
            preparedStatement.setInt(6, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
    }

    /**
     * Read all Car entities from the repository.
     *
     * @return An unmodifiable list of all Car entities.
     */
    public List<Car> read() throws Exceptions {
        String sql = "SELECT * FROM objects.cars";
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = Car.builder()
                        .id(resultSet.getInt("car_id"))
                        .carBrand(resultSet.getString("car_brand"))
                        .carModel(resultSet.getString("car_model"))
                        .releaseYear(resultSet.getDate("release_year"))
                        .condition(resultSet.getString("condition"))
                        .price(resultSet.getLong("price"))
                        .build();
                cars.add(car);
            }

        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
        return cars;
    }

    /**
     * Removes a Car entity from the repository.
     *
     * @param car The Car entity to be removed.
     */
    public void delete(Car car) throws Exceptions {
        String sql = "DELETE FROM objects.cars WHERE car_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, car.getId());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
    }

}
