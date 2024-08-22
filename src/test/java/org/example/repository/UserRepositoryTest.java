package org.example.repository;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.service.Exception.Exceptions;
import org.example.service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class UserRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = DriverManager.getConnection(
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword()
        );

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.yml", new ClassLoaderResourceAccessor(), database);

        liquibase.update();

        userRepository = new UserRepository(connection);
    }

    @Test
    @DisplayName("create should add a new user to the repository")
    void create_shouldAddNewUserToRepository() throws Exceptions {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setLogin("testLogin");
        user.setPassword("1234");

        user = userRepository.create(user);

        List<User> users = userRepository.read();
        assertEquals(2, users.size());
        assertTrue(users.contains(user));

    }

    @Test
    @DisplayName("read should return all users in the repository")
    void read_shouldReturnAllUsersInRepository() throws Exceptions {
        User user1 = new User();
        user1.setRole(Role.ADMIN);
        user1.setLogin("testLogin");
        user1.setPassword("1234");

        User user2 = new User();
        user2.setRole(Role.ADMIN);
        user2.setLogin("testLogin");
        user2.setPassword("1234");

        user1 = userRepository.create(user1);
        user2 = userRepository.create(user2);

        List<User> users = userRepository.read();
        assertEquals(3, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    @DisplayName("update should modify an existing user in the repository")
    void update_shouldModifyExistingUserInRepository() throws Exceptions {

        User user = new User();
        user.setRole(Role.ADMIN);
        user.setLogin("testLogin");
        user.setPassword("1234");

        user = userRepository.create(user);

        user.setName("Updated Name");
        userRepository.update(user);

        List<User> users = userRepository.read();
        assertEquals(2, users.size());
        assertTrue(users.contains(user));
    }

    @Test
    @DisplayName("delete should remove a user from the repository")
    void delete_shouldRemoveUserFromRepository() throws Exceptions {

        User user = new User();
        user.setRole(Role.ADMIN);
        user.setLogin("testLogin");
        user.setPassword("1234");

        user = userRepository.create(user);

        userRepository.delete(user);

        List<User> users = userRepository.read();
        assertEquals(1, users.size());
    }
}
