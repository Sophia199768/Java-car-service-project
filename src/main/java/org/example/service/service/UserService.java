package org.example.service.service;

import lombok.RequiredArgsConstructor;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.*;
import org.example.service.exception.Exceptions;
import org.example.service.mapper.UserMapper;
import org.example.service.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The UserService class provides functionality for managing users within the application.
 * It offers methods for user creation, login, role changes, and user retrieval, as well as filtering users based on criteria.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Creates a new user based on the provided RegisterRequest.
     *
     * @throws Exceptions If the login is already used by another user.
     */
    public ShowUserResponse createUser(CreateUserRequest request) throws Exceptions {
        String newLogin = request.getLogin();

        List<User> users = userRepository.read();

        for (User user : users) {
            if (Objects.equals(user.getLogin(), newLogin)) {
                throw new Exceptions("This login is used");
            }
        }

        return UserMapper.INSTANCE.toShowUserResponse(userRepository.create(UserMapper.INSTANCE.toUser(request)));
    }

    /**
     * Handles user login based on the provided LogInRequest.
     *
     * @param logInRequest The LogInRequest object containing login details.
     * @return A UserRequest object if login is successful.
     * @throws Exceptions If the login or password is invalid.
     */
    public User logIn(LogInRequest logInRequest) throws Exceptions {
        List<User> users = userRepository.read();

        for (User user : users) {
            if (Objects.equals(user.getLogin(), logInRequest.getLogin())) {
                return user;
            }
        }
        throw new Exceptions("Invalid login or password");
    }


    /**
     * Processes a car update user and throws an exception if there is not such user.
     *
     * @param request The UpdateUserRequest object representing to update.
     * @throws Exceptions If there is no such user.
     */
    public ShowUserResponse updateUser(UpdateUserRequest request) throws Exceptions {
        User user = userRepository.read().stream().filter(it -> it.getId().equals(request.getId())).findFirst().orElse(null);
        if (user == null) {
            throw new Exceptions("There is no such user");
        }

        return UserMapper.INSTANCE.toShowUserResponse(userRepository.update(user));
    }

    /**
     * Retrieves a list of all users from the repository and maps them to UserResponse objects.
     *
     * @return A list of UserResponse objects representing all users in the repository.
     */
    public List<ShowUserResponse> read() throws Exceptions {
        List<User> users = userRepository.read();

        List<ShowUserResponse> usersResponses = new ArrayList<>();

        for (User user : users) {
            usersResponses.add(UserMapper.INSTANCE.toShowUserResponse(user));
        }

        return usersResponses;
    }


    public void deleteUser(Integer id) throws Exceptions {
        User user = userRepository.read().stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
        if (user == null) throw new Exceptions("There is no such user");
        userRepository.delete(user);
    }


    /**
     * Filters the list of cars based on the specified criteria.
     *
     * @param request The FilterUserRequest where information is written what we need to filter.
     * @return A list of ShowUserResponse objects that match the filter criteria.
     */
    public List<ShowUserResponse> filter(FilterUserRequest request) throws Exceptions {
        List<User> answer = userRepository.read();

        if (request.getEmail() != null) {
            answer = answer.stream().filter(user -> user.getEmail().equals(request.getEmail())).toList();
        }

        if (request.getName() != null) {
            answer = answer.stream().filter(user -> user.getName().equals(request.getName())).toList();
        }

        if (request.getPhone() != null) {
            answer = answer.stream().filter(user -> user.getPhone().equals(request.getPhone())).toList();
        }

        return answer.stream().map(UserMapper.INSTANCE::toShowUserResponse).collect(Collectors.toList());
    }
}
