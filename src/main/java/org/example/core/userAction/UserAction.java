package org.example.core.userAction;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

/**
 * Represents an action performed by a user, including details such as the user, action, timestamp, and a unique action ID.
 */
@Data
@Builder
public class UserAction {
    /**
     * Unique identifier for the user action.
     */
    private Integer actionId;

    /**
     * Username or identifier of the user who performed the action.
     */
    private String user;

    /**
     * Description of the action performed by the user.
     */
    private String action;

    /**
     * Timestamp when the action was performed.
     */
    private LocalDateTime timestamp;

    /**
     * Returns a string representation of the UserAction in the format:
     *
     * @return A string representation of the UserAction.
     */
    @Override
    public String toString() {
        return timestamp + " - " + user + " - " + action;
    }
}
