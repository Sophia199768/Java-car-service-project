package org.example.mapper;

import org.example.model.user.User;
import org.example.responsesAndRequestes.user.CreateUserRequest;
import org.example.responsesAndRequestes.user.ShowUserResponse;

/**
 * The UserMapper class is responsible for mapping between User entities and their corresponding request and response objects
 */
public class UserMapper {

    /**
     * Converts a User entity to a UserResponse object
     *
     * @param user The User entity to be converted
     * @return A UserResponse object containing the data from the User entity
     */
    public ShowUserResponse toShowUserResponse(User user) {
        return new ShowUserResponse(user.getId(),
                user.getRole(),
                user.getLogin(),
                user.getName(),
                user.getPhone(),
                user.getEmail());
    }

    /**
     * Converts a UserRequest object to a User entity
     *
     * @param createUserRequest The UserRequest object to be converted
     * @return A User entity containing the data from the UserRequest object
     */
    public User toUser(CreateUserRequest createUserRequest) {
        return new User(createUserRequest.getRole(),
                createUserRequest.getLogin(),
                createUserRequest.getPassword(),
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                createUserRequest.getPhone());
    }
}
