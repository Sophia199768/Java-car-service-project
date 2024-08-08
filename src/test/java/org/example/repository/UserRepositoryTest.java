package org.example.repository;

import org.example.core.model.user.User;
import org.example.service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    @DisplayName("create should add a new user to the repository")
    void create_shouldAddNewUserToRepository() {
        User user = new User();
        userRepository.create(user);

        List<User> users = userRepository.read();
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
        assertEquals(1, user.getId());
    }

    @Test
    @DisplayName("read should return all users in the repository")
    void read_shouldReturnAllUsersInRepository() {
        User user1 = new User();
        User user2 = new User();
        userRepository.create(user1);
        userRepository.create(user2);

        List<User> users = userRepository.read();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    @DisplayName("update should modify an existing user in the repository")
    void update_shouldModifyExistingUserInRepository() {
        User user = new User();
        userRepository.create(user);

        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setName("Updated Name");
        userRepository.update(updatedUser);

        List<User> users = userRepository.read();
        assertEquals(1, users.size());
        assertEquals(updatedUser, users.get(0));
    }

    @Test
    @DisplayName("delete should remove a user from the repository")
    void delete_shouldRemoveUserFromRepository() {
        User user = new User();
        userRepository.create(user);

        userRepository.delete(user);

        List<User> users = userRepository.read();
        assertEquals(0, users.size());
    }
}
