package org.example.model.user;

import java.util.Arrays;

/**
 * Enum representing different roles in the system.
 */
public enum Role {
    /**
     * Admin role with the highest level of access.
     */
    ADMIN,

    /**
     * Manager role with permissions to manage resources and users.
     */
    MANAGER,

    /**
     * Client role with access to basic functionalities.
     */
    CLIENT;

    /**
     * Converts a string representation of a role to its corresponding {@link Role} enum.
     *
     * @param role The string representation of the role.
     * @return The corresponding {@link Role} enum.
     * @throws IllegalArgumentException If no matching role is found.
     */
    public static Role fromString(String role) {
        return Arrays.stream(Role.values())
                .filter(it -> it.toString().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + role));
    }
}
