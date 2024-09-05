package org.example.core.responsesAndRequestes.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.model.user.Role;



/**
 * The {@code CreateUserRequest} class represents a request to create a new user
 * with specified details such as role, login credentials, personal information, and contact details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    /**
     * The role assigned to the new user (e.g., ADMIN, MANAGER, CLIENT).
     */
    private Role role;

    /**
     * The login username for the new user.
     */
    private String login;

    /**
     * The password for the new user.
     */
    private String password;

    /**
     * The full name of the new user.
     */
    private String name;


    /**
     * The email address of the new user.
     */
    private String email;

    /**
     * The phone number of the new user.
     */
    private String phone;
}
