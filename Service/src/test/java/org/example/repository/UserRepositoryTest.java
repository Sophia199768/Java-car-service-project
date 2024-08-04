package org.example.repository;

import org.example.model.user.User;
import org.junit.jupiter.api.BeforeEach;
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
    void create() {
        User user = new User();
        userRepository.create(user);

        List<User> users = userRepository.read();
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
        assertEquals(0, user.getId());
    }

    @Test
    void read() {
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
    void update() {

    }

    @Test
    void delete() {
        User user = new User();
        userRepository.create(user);

        userRepository.delete(user);

        List<User> users = userRepository.read();
        assertEquals(0, users.size());
    }
}
