package org.example.core.responsesAndRequestes.user;

import org.example.core.model.user.Role;

import java.util.Objects;


/**
 * The {@code CreateUserRequest} class represents a request to create a new user
 * with specified details such as role, login credentials, personal information, and contact details.
 */

public class CreateUserRequest {
    /**
     * The role assigned to the new user (e.g., ADMIN, MANAGER, CLIENT).
     */
    private Role role;

    /**
     * The login username for the new user.
     */
    private String login;

    /**
     * The password for the new user.
     */
    private String password;

    /**
     * The full name of the new user.
     */
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserRequest that)) return false;
        return getRole() == that.getRole() && Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getLogin(), getPassword(), getName(), getEmail(), getPhone());
    }

    /**
     * The email address of the new user.
     */
    private String email;

    /**
     * The phone number of the new user.
     */
    private String phone;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
