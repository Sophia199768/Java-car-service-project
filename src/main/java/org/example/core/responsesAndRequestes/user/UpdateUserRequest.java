package org.example.core.responsesAndRequestes.user;

import lombok.*;
import org.example.core.model.user.Role;


/**
 * The {@code UpdateUserRequest} class represents a request to update an existing user's details.
 * It includes the user's identifier and the updated information such as role, name, email, and phone.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    /**
     * The unique identifier of the user to be updated.
     */
    private Integer id;

    /**
     * The new role to assign to the user (e.g., ADMIN, MANAGER, CLIENT).
     */
    private Role role;

    /**
     * The updated full name of the user.
     */
    private String name;

    /**
     * The updated email address of the user.
     */
    private String email;

    /**
     * The updated phone number of the user.
     */
    private String phone;
}
