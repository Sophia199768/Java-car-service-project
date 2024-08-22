package org.example.service.sql;

/**
 * A utility class that contains SQL query templates for operations on the 'orders' table.
 * This class is intended to provide centralized access to SQL statements used throughout the application.
 *
 * <p>The class contains static constants representing SQL queries for various operations
 * such as inserting, selecting, updating, and deleting records in the 'orders' table.</p>
 *
 * <p>Each query is a string template where placeholders ("?") can be substituted with actual values using
 * prepared statements.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     String sql = OrderSql.INSERT_ORDER;
 *     PreparedStatement preparedStatement = connection.prepareStatement(sql);
 *     preparedStatement.setDate(1, new java.sql.Date(orderDate.getTime()));
 *     preparedStatement.setInt(2, userId);
 *     preparedStatement.setInt(3, carId);
 *     preparedStatement.setString(4, status);
 *     preparedStatement.setString(5, information);
 * </pre>
 */
public class OrderSql {

    /**
     * SQL query template for inserting a new order into the 'orders' table.
     * The query expects five parameters: date, user_id, car_id, status, and information.
     */
    public static final String INSERT_ORDER = "INSERT INTO objects.orders (date, user_id, car_id, status, information) VALUES (?, ?, ?, ?, ?)";

    /**
     * SQL query template for selecting all orders from the 'orders' table.
     * This query retrieves all columns from the table.
     */
    public static final String SELECT_ALL = "SELECT * FROM objects.orders";

    /**
     * SQL query template for updating an existing order in the 'orders' table.
     * The query expects six parameters: date, user_id, car_id, status, information, and order_id.
     */
    public static final String UPDATE_ORDER = "UPDATE objects.orders SET date = ?, user_id = ?, car_id = ?, status = ?, information = ? WHERE order_id = ?";

    /**
     * SQL query template for deleting an order from the 'orders' table.
     * The query expects one parameter: order_id.
     */
    public static final String DELETE_ORDER = "DELETE FROM objects.orders WHERE order_id = ?";
}
