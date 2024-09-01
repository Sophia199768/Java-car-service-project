package org.example.service.repository;


import org.example.core.model.car.Car;
import org.example.service.exception.Exceptions;
import org.example.service.sql.CarSql;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The CarRepository class supports basic CRUD  operations on the car collection.
 */
@Repository
public class CarRepository {

    private final DataSource source;

    public CarRepository(DataSource source) {
        this.source = source;
    }


    /**
     * Adds a new car entity to the repository.
     * @param car The Car entity to be added.
     */
    public Car create(Car car) throws Exceptions {
        String sql = CarSql.INSERT_CAR;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, car.getCarBrand());
            preparedStatement.setString(2, car.getCarModel());
            preparedStatement.setObject(3, car.getReleaseYear(), Types.TIMESTAMP);
            preparedStatement.setString(4, car.getCondition());
            preparedStatement.setObject(5, car.getPrice(), Types.BIGINT);
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
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
    public Car update(Car car) throws Exceptions {
        String sql = CarSql.UPDATE_CAR;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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

        return car;
    }


    /**
     * build a Car entity to add in list
     *
     * @return Car
     */
    private Car buildCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("car_id"));
        car.setCarBrand(resultSet.getString("car_brand"));
        car.setCarModel(resultSet.getString("car_model"));
        car.setReleaseYear(resultSet.getDate("release_year").toLocalDate());
        car.setCondition(resultSet.getString("condition"));
        car.setPrice(resultSet.getLong("price"));
        return car;
    }

    /**
     * Read all Car entities from the repository.
     *
     * @return An unmodifiable list of all Car entities.
     */
    public List<Car> read() throws Exceptions {
        String sql = CarSql.SELECT_ALL;
        List<Car> cars = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = buildCar(resultSet);
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
        String sql = CarSql.DELETE_CAR;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, car.getId());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
    }

}
