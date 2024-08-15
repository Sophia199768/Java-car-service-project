package org.example.service.sql;

public class UserSql {

        public static final String INSERT_USER = "INSERT INTO objects.users (login, role, password, name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        public static final String SELECT_USER_BY_ID = "SELECT * FROM objects.users WHERE user_id = ?";
        public static final String SELECT_ALL = "SELECT * FROM objects.users";
        public static final String UPDATE_USER = "UPDATE objects.users SET login = ? , role = ?, password = ?, name = ?, email = ?, phone = ?  WHERE user_id = ?";
        public static final String DELETE_USER = "DELETE FROM objects.users WHERE user_id = ?";


        private UserSql() {
        }
}
