package org.example.core.userAction;



import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an action performed by a user, including details such as the user, action, timestamp, and a unique action ID.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAction that)) return false;
        return Objects.equals(getActionId(), that.getActionId()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getAction(), that.getAction()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionId(), getUser(), getAction(), getTimestamp());
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

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
