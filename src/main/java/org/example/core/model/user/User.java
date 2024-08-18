package org.example.core.model.user;

import org.example.core.model.car.Car;

import java.util.List;
import java.util.Objects;

/**
 * Represents a user in the system with attributes such as login, role, and associated cars.
 */

public class User {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getLogin(), user.getLogin()) && getRole() == user.getRole() && Objects.equals(getCars(), user.getCars()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhone(), user.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getRole(), getCars(), getPassword(), getName(), getEmail(), getPhone());
    }

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
