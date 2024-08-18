package org.example.service.auth;

import org.example.core.model.user.User;

/**
 * Singleton class for managing the authentication context.
 * <p>
 * This class provides a centralized location to store and access the currently authenticated user.
 * It follows the Singleton design pattern to ensure that only one instance of the context exists
 * throughout the application.
 * </p>
 */
public class AuthContext {
    private static AuthContext INSTANCE;
    private User user;

    /**
     * Private constructor to prevent instantiation.
     */
    private AuthContext() {
    }

    /**
     * Returns the singleton instance of {@code AuthContext}.
     * <p>
     * If the instance does not already exist, it is created.
     * </p>
     *
     * @return the singleton instance of {@code AuthContext}
     */
    public static AuthContext getInstance() {
        if (INSTANCE == null) INSTANCE = new AuthContext();
        return INSTANCE;
    }

    /**
     * Gets the currently authenticated user.
     *
     * @return the currently authenticated user, or {@code null} if no user is authenticated
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the currently authenticated user.
     *
     * @param user the user to set as the currently authenticated user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
