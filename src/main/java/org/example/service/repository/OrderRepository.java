package org.example.service.repository;



import org.example.core.order.Order;
import org.example.service.exception.Exceptions;
import org.example.service.sql.OrderSql;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The OrderRepository class supports basic CRUD operations on the order collection.
 */
@Repository
public class OrderRepository {
    private final DataSource source;

    public OrderRepository(DataSource source) {
        this.source = source;
    }

    /**
     * Adds a new Order entity to the repository.
     * The Order's ID is automatically assigned and incremented.
     *
     * @param order The Order entity to be added.
     */
    public Order create(Order order) throws Exceptions {
        String sql = OrderSql.INSERT_ORDER;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, order.getDate(), Types.TIMESTAMP);
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getCarId());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setString(5, order.getInformation());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }


        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT last_value FROM objects.orders_order_id_seq")) {
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            } else {
                throw new SQLException("Creating car failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    /**
     * Retrieves all Order entities from the repository.
     *
     * @return An unmodifiable list of all Order entities.
     */
    public List<Order> read() throws Exceptions {
        String sql = OrderSql.SELECT_ALL;
        List<Order> orders = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {

               Order order = new Order();
               order.setDate(resultSet.getDate("date").toLocalDate());
               order.setUserId(resultSet.getInt("user_id"));
               order.setCarId(resultSet.getInt("car_id"));
               order.setStatus(resultSet.getString("status"));
               order.setInformation(resultSet.getString("information"));

               orders.add(order);
           }
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        return Collections.unmodifiableList(orders);
    }

    /**
     * Updates an existing Order entity in the repository.
     * The Order is identified by its ID, and if found, it is replaced with the updated Order.
     *
     * @param order The Order entity with updated information.
     */
    public Order update(Order order) throws Exceptions {
        String sql = OrderSql.UPDATE_ORDER;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, order.getDate(), Types.TIMESTAMP);
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.setInt(3, order.getCarId());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setString(5, order.getInformation());
            preparedStatement.setInt(6, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        return order;
    }

    /**
     * Removes an Order entity from the repository.
     *
     * @param order The Order entity to be removed.
     */
    public void delete(Order order) throws Exceptions {
        String sql = OrderSql.DELETE_ORDER;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
    }
}