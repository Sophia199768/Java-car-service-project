package org.example.userAction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents an action performed by a user, including details such as the user, action, timestamp, and a unique action ID.
 */
@Setter
@Getter
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
     * Constructs a new UserAction with specified user and action.
     * The timestamp is automatically set to the current date and time.
     *
     * @param user   The username or identifier of the user who performed the action.
     * @param action A description of the action performed by the user.
     */
    public UserAction(String user, String action) {
        this.user = user;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

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
