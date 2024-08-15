package org.example.service.repository;



import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.service.Exception.Exceptions;
import org.example.service.sql.UserSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The UserRepository class supports basic CRUD operations on the user collection.
 */
public class UserRepository {

    private final Connection connection;

    public UserRepository() {
        try {
            DataBaseConfig config = new DataBaseConfig();
            connection = config.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new User entity to the repository.
     * @param user The User entity to be added.
     */
    public User create(User user) throws Exceptions {
        String sql = UserSql.INSERT_USER;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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

        try (Statement statement = connection.createStatement();
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("user_id"))
                        .login(resultSet.getString("login"))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getString( "phone"))
                        .build();
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
    public void update(User user) throws Exceptions {
        String sql = UserSql.UPDATE_USER;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
    }

    /**
     * Removes a User entity from the repository.
     * @param user The User entity to be removed.
     */
    public void delete(User user) throws Exceptions {
        String sql =  UserSql.DELETE_USER;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new Exceptions("SQL Exception");
        }
    }
}