package org.example.responsesAndRequestes.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@code FilterUserRequest} class represents a request to filter users
 * based on specified criteria such as name, phone, and email.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterUserRequest {
    /**
     * The name of the user to filter by.
     */
    private String name;

    /**
     * The phone number of the user to filter by.
     */
    private String phone;

    /**
     * The email address of the user to filter by.
     */
    private String email;
}
