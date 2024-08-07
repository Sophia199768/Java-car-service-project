package org.example.core.responsesAndRequestes.user;

import lombok.*;
import org.example.core.model.user.Role;


/**
 * The {@code ShowUserResponse} class represents the response containing details of a user
 * that is displayed to the user or administrator, including user ID, role, login credentials,
 * and personal contact information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowUserResponse {
    /**
     * The unique identifier of the user.
     */
    private Integer id;

    /**
     * The role assigned to the user (e.g., ADMIN, MANAGER, CLIENT).
     */
    private Role role;

    /**
     * The login username of the user.
     */
    private String login;

    /**
     * The full name of the user.
     */
    private String name;

    /**
     * The phone number of the user.
     */
    private String phone;

    /**
     * The email address of the user.
     */
    private String email;
}
