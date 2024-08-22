package org.example.core.responsesAndRequestes.user;

import org.example.core.model.user.Role;

import java.util.Objects;


/**
 * The {@code UpdateUserRequest} class represents a request to update an existing user's details.
 * It includes the user's identifier and the updated information such as role, name, email, and phone.
 */
public class UpdateUserRequest {
    /**
     * The unique identifier of the user to be updated.
     */
    private Integer id;

    /**
     * The new role to assign to the user (e.g., ADMIN, MANAGER, CLIENT).
     */
    private Role role;

    /**
     * The updated full name of the user.
     */
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUserRequest that)) return false;
        return Objects.equals(getId(), that.getId()) && getRole() == that.getRole() && Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole(), getName(), getEmail(), getPhone());
    }

    /**
     * The updated email address of the user.
     */
    private String email;

    /**
     * The updated phone number of the user.
     */
    private String phone;

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
