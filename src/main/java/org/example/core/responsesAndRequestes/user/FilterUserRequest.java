package org.example.core.responsesAndRequestes.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code FilterUserRequest} class represents a request to filter users
 * based on specified criteria such as name, phone, and email.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
