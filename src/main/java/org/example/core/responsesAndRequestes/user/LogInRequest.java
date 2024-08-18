package org.example.core.responsesAndRequestes.user;


import java.util.Objects;

/**
 * The {@code LogInRequest} class represents a request to log in a user
 * with specified login credentials, including username and password.
 */

public class LogInRequest {
    /**
     * The login username for the user attempting to log in.
     */
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogInRequest that)) return false;
        return Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The password for the user attempting to log in.
     */
    private String password;
}
