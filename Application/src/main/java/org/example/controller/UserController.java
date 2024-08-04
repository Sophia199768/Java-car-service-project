package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Exception.Exceptions;
import org.example.model.user.User;
import org.example.responsesAndRequestes.user.*;
import org.example.service.UserService;

import java.util.List;

/**
 * The {@code UserController} class provides methods to manage user operations Registration, Login, Update, Delete, and retrieving users.
 */
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Registers a new user with the specified details.
     *
     * @param request the request containing the details of the user to register
     * @throws Exception if an error occurs during registration
     */
    public void registration(CreateUserRequest request) throws Exception {
        userService.createUser(request);
    }

    /**
     * Logs in a user with the specified credentials.
     *
     * @param request the request containing the login credentials
     * @return the logged-in user
     * @throws Exception if an error occurs during login
     */
    public User logIn(LogInRequest request) throws Exception {
        return userService.logIn(request);
    }

    /**
     * Deletes a user by their identifier.
     *
     * @param id the identifier of the user to delete
     * @throws Exceptions if an error occurs during deletion
     */
    public void delete(Integer id) throws Exceptions {
        userService.deleteUser(id);
    }

    /**
     * Updates the details of an existing user.
     *
     * @param request the request containing the user details to update
     * @throws Exceptions if an error occurs during the update
     */
    public void update(UpdateUserRequest request) throws Exceptions {
        userService.updateUser(request);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users
     */
    public List<ShowUserResponse> readAllUsers() {
        return userService.read();
    }

    /**
     * Retrieves a list of users that match the specified filter criteria.
     *
     * @param request the request containing the filter criteria
     * @return a list of users that match the filter criteria
     */
    public List<ShowUserResponse> getFilterUsers(FilterUserRequest request) {
        return userService.filter(request);
    }
}
