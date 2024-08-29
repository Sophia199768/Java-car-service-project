package org.example.application.controllers;

import org.example.core.responsesAndRequestes.user.CreateUserRequest;
import org.example.core.responsesAndRequestes.user.FilterUserRequest;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;
import org.example.core.responsesAndRequestes.user.UpdateUserRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing user operations.
 * This controller provides a REST API for user registration, updates, deletion, filtering, and retrieval.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * Constructor with dependency injection.
     *
     * @param userService the service for handling user-related operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param createUserRequest the request containing the details for creating a user.
     * @throws Exceptions if an error occurs during user registration.
     */
    @PostMapping("/register")
    public void registerUser(@RequestBody CreateUserRequest createUserRequest) throws Exceptions {
        userService.createUser(createUserRequest);
    }

    /**
     * Updates an existing user.
     *
     * @param updateUserRequest the request containing the updated user details.
     * @throws Exceptions if an error occurs during user update.
     */
    @PutMapping
    public void updateUser(@RequestBody UpdateUserRequest updateUserRequest) throws Exceptions {
        userService.updateUser(updateUserRequest);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @throws Exceptions if an error occurs during user deletion.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) throws Exceptions {
        userService.deleteUser(id);
    }

    /**
     * Filters users based on specified criteria.
     *
     * @param filterUserRequest the request containing the filtering criteria.
     * @return a list of users that match the filtering criteria.
     * @throws Exceptions if an error occurs during user filtering.
     */
    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<List<ShowUserResponse>> filterUsers(@RequestBody FilterUserRequest filterUserRequest) throws Exceptions {
        return new ResponseEntity<>(userService.filter(filterUserRequest), HttpStatus.OK);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users.
     * @throws Exceptions if an error occurs during user retrieval.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ShowUserResponse>> read() throws Exceptions {
        return new ResponseEntity<>(userService.read(), HttpStatus.OK);
    }
}
