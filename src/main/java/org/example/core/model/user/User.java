package org.example.core.model.user;

import lombok.*;
import org.example.core.model.car.Car;

import java.util.List;

/**
 * Represents a user in the system with attributes such as login, role, and associated cars.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Cloneable {
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

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
