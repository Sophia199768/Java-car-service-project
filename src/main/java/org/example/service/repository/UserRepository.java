package org.example.service.repository;



import org.example.core.model.user.User;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * The UserRepository class supports basic CRUD operations on the user collection.
 */
public class UserRepository {
    private final List<User> users = new LinkedList<>();
    private Integer nextId = 1;

    /**
     * Adds a new User entity to the repository.
     * @param user The User entity to be added.
     */
    public void create(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    /**
     * Retrieves all User entities from the repository.
     * @return An unmodifiable list of all User entities.
     */
    public List<User> read() {
        return Collections.unmodifiableList(users);
    }

    /**
     * Updates an existing User entity in the repository.
     * @param user The User entity with updated information.
     */
    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
            }
        }
    }

    /**
     * Removes a User entity from the repository.
     * @param user The User entity to be removed.
     */
    public void delete(User user) {
        users.remove(user);
    }
}