package org.example.core.responsesAndRequestes.user;


import java.util.Objects;

/**
 * The {@code FilterUserRequest} class represents a request to filter users
 * based on specified criteria such as name, phone, and email.
 */

public class FilterUserRequest {
    /**
     * The name of the user to filter by.
     */
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterUserRequest that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail());
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

    /**
     * The phone number of the user to filter by.
     */
    private String phone;

    /**
     * The email address of the user to filter by.
     */
    private String email;
}
