package org.example.core.responsesAndRequestes.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The {@code LogInRequest} class represents a request to log in a user
 * with specified login credentials, including username and password.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
