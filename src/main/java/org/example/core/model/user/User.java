package org.example.core.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.model.car.Car;

import java.util.List;

/**
 * Represents a user in the system with attributes such as login, role, and associated cars.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * Unique identifier for the user.
     */
    private Integer id;

    /**
     * Login username of the user.
     */
    private String login;

    /**
     * Role assigned to the user.
     */
    private Role role;

    /**
     * List of cars associated with the user.
     */
    private List<Car> cars;

    /**
     * Password for user authentication.
     */
    private String password;

    private String name;
    private String email;
    private String phone;

}
