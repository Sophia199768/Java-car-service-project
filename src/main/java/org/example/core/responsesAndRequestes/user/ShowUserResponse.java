package org.example.core.responsesAndRequestes.user;

import org.example.core.model.user.Role;

import java.util.Objects;


/**
 * The {@code ShowUserResponse} class represents the response containing details of a user
 * that is displayed to the user or administrator, including user ID, role, login credentials,
 * and personal contact information.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowUserResponse that)) return false;
        return Objects.equals(getId(), that.getId()) && getRole() == that.getRole() && Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole(), getLogin(), getName(), getPhone(), getEmail());
    }

    /**
     * The email address of the user.
     */
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
