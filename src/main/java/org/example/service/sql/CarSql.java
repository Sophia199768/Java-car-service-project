package org.example.service.sql;

/**
 * This class contains SQL query constants for the `cars` table in the `objects` schema.
 * The queries in this class can be used to perform basic CRUD operations (Create, Read, Update, Delete) on the `cars` table.
 *
 * <p>All SQL queries are defined as public static final strings, making them accessible
 * without the need to instantiate the class.</p>
 */
public class CarSql {

    /**
     * SQL query to insert a new car into the `cars` table.
     * <p>Inserts values for car brand, car model, release year, condition, and price.</p>
     */
    public static final String INSERT_CAR = "INSERT INTO objects.cars (car_brand, car_model, release_year, condition, price) VALUES (?, ?, ?, ?, ?)";

    /**
     * SQL query to select all cars from the `cars` table.
     * <p>Retrieves all columns for every car in the table.</p>
     */
    public static final String SELECT_ALL = "SELECT * FROM objects.cars";

    /**
     * SQL query to update an existing car in the `cars` table.
     * <p>Updates car brand, car model, release year, condition, and price for the car with the specified `car_id`.</p>
     */
    public static final String UPDATE_CAR = "UPDATE objects.cars SET car_brand = ?, car_model = ?, release_year = ?, condition = ?, price = ? WHERE car_id = ?";

    /**
     * SQL query to delete a car from the `cars` table.
     * <p>Deletes the car with the specified `car_id`.</p>
     */
    public static final String DELETE_CAR = "DELETE FROM objects.cars WHERE car_id = ?";

}
