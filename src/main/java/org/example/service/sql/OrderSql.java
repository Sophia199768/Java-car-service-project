package org.example.service.sql;

public class OrderSql {
    public static final String INSERT_ORDER = "INSERT INTO objects.orders (date, user_id, car_id, status, information) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM objects.orders";
    public static final String UPDATE_ORDER ="UPDATE objects.orders SET date = ?, user_id = ?, car_id = ?, status = ?, information = ?  WHERE order_id = ?";
    public static final String DELETE_ORDER = "DELETE FROM objects.orders WHERE order_id = ?";
}
