package org.example.responsesAndRequestes.user;

import lombok.*;

/**
 * The {@code LogInRequest} class represents a request to log in a user
 * with specified login credentials, including username and password.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {
    /**
     * The login username for the user attempting to log in.
     */
    private String login;

    /**
     * The password for the user attempting to log in.
     */
    private String password;
}
