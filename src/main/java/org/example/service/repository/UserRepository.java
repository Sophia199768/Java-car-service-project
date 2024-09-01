package org.example.service.repository;



import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.service.exception.Exceptions;
import org.example.service.sql.UserSql;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The UserRepository class supports basic CRUD operations on the user collection.
 */
@Repository
public class UserRepository {

    private final DataSource source;


    public UserRepository(DataSource source) {
        this.source = source;
    }

    /**
     * Adds a new User entity to the repository.
     * @param user The User entity to be added.
     */
    public User create(User user) throws Exceptions {
        String sql = UserSql.INSERT_USER;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, String.valueOf(user.getRole()));
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        try (
                Connection connection = source.getConnection();
                Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT last_value FROM objects.users_user_id_seq")) {
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            } else {
                throw new SQLException("Creating car failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }

    /**
     * Retrieves all User entities from the repository.
     * @return An unmodifiable list of all User entities.
     */
    public List<User> read() throws Exceptions {
        String sql = UserSql.SELECT_ALL;
        List<User> users = new ArrayList<>();
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString( "phone"));

                users.add(user);
            }

        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        return users;
    }

    /**
     * Updates an existing User entity in the repository.
     * @param user The User entity with updated information.
     */
    public User update(User user) throws Exceptions {
        String sql = UserSql.UPDATE_USER;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, String.valueOf(user.getRole()));
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setInt(7, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }

        return user;
    }

    /**
     * Removes a User entity from the repository.
     * @param user The User entity to be removed.
     */
    public void delete(User user) throws Exceptions {
        String sql =  UserSql.DELETE_USER;
        try (
                Connection connection = source.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
    }
}