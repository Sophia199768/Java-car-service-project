package org.example.service.sql;

public class CarSql {
    public static final String INSERT_CAR = "INSERT INTO objects.cars (car_brand, car_model, release_year, condition, price) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM objects.cars";
    public static final String UPDATE_CAR = "UPDATE objects.cars SET car_brand = ?, car_model = ?, release_year = ?, condition = ?, price = ? WHERE car_id = ?";
    public static final String DELETE_CAR = "DELETE FROM objects.cars WHERE car_id = ?";

}

