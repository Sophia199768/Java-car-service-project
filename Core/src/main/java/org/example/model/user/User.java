package org.example.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.car.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system with attributes such as login, role, and associated cars.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
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
    private List<Car> cars = new ArrayList<>();

    /**
     * Password for user authentication.
     */
    private String password;

    private String name;
    private String email;
    private String phone;

    /**
     * Constructs a new User with specified role, login, password, and list of cars.
     *
     * @param role     The role of the user.
     * @param login    The login username of the user.
     * @param password The password for user authentication.
     *
     */
    public User(Role role, String login, String password, String name, String email, String phone) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
