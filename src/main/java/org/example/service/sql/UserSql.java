package org.example.service.sql;

/**
 * A utility class that contains SQL query templates for operations on the 'users' table.
 * This class provides centralized access to SQL statements used throughout the application
 * for performing CRUD operations on user records.
 *
 * <p>The class contains static constants representing SQL queries for various operations
 * such as inserting, selecting, updating, and deleting records in the 'users' table.</p>
 *
 * <p>Each query is a string template where placeholders ("?") can be substituted with actual values using
 * prepared statements.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     String sql = UserSql.INSERT_USER;
 *     PreparedStatement preparedStatement = connection.prepareStatement(sql);
 *     preparedStatement.setString(1, login);
 *     preparedStatement.setString(2, role);
 *     preparedStatement.setString(3, password);
 *     preparedStatement.setString(4, name);
 *     preparedStatement.setString(5, email);
 *     preparedStatement.setString(6, phone);
 * </pre>
 */
public class UserSql {

        /**
         * SQL query template for inserting a new user into the 'users' table.
         * The query expects six parameters: login, role, password, name, email, and phone.
         */
        public static final String INSERT_USER = "INSERT INTO objects.users (login, role, password, name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";

        /**
         * SQL query template for selecting a user from the 'users' table by user ID.
         * The query expects one parameter: user_id.
         */
        public static final String SELECT_USER_BY_ID = "SELECT * FROM objects.users WHERE user_id = ?";

        /**
         * SQL query template for selecting all users from the 'users' table.
         * This query retrieves all columns from the table.
         */
        public static final String SELECT_ALL = "SELECT * FROM objects.users";

        /**
         * SQL query template for updating an existing user in the 'users' table.
         * The query expects seven parameters: login, role, password, name, email, phone, and user_id.
         */
        public static final String UPDATE_USER = "UPDATE objects.users SET login = ? , role = ?, password = ?, name = ?, email = ?, phone = ? WHERE user_id = ?";

        /**
         * SQL query template for deleting a user from the 'users' table.
         * The query expects one parameter: user_id.
         */
        public static final String DELETE_USER = "DELETE FROM objects.users WHERE user_id = ?";

        /**
         * Private constructor to prevent instantiation of this utility class.
         */
        private UserSql() {
        }
}
