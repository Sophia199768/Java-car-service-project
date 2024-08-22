package org.example.service.service;

import org.example.core.userAction.UserAction;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The AuditService class manages and records user actions within the application.
 */

public class AuditService {
    private final List<UserAction> actions;

    /**
     * Constructs a new AuditService instance with an empty list of user actions.
     */
    public AuditService() {
        actions = new ArrayList<>();
    }

    /**
     * Logs a user action by creating a new UserAction object and adding it to the list of actions.
     *
     * @param user   The user who performed the action.
     * @param action The description of the action performed.
     */
    public void logAction(String user, String action) {
        UserAction userAction = new UserAction();
        userAction.setUser(user);
        userAction.setAction(action);
        userAction.setTimestamp(LocalDateTime.now());

        actions.add(userAction);
    }

    /**
     * Retrieves the list of all logged user actions.
     * @return A list of UserAction objects representing all logged actions.
     */
    public List<UserAction> getActions() {
        return actions;
    }

    /**
     * Filters the list of logged actions based on the specified criteria.
     *
     * @param user      The user whose actions are to be filtered (can be null).
     * @param action    The action description to filter by (can be null).
     * @param startDate The start date of the period to filter by (can be null).
     * @param endDate   The end date of the period to filter by (can be null).
     * @return A list of UserAction objects that match the filter criteria.
     */
    public List<UserAction> filterActions(String user, String action, LocalDateTime startDate, LocalDateTime endDate) {
        List<UserAction> filteredActions = new ArrayList<>();
        for (UserAction userAction : actions) {
            if ((user == null || userAction.getUser().equals(user)) &&
                    (action == null || userAction.getAction().equals(action)) &&
                    (startDate == null || !userAction.getTimestamp().isBefore(startDate)) &&
                    (endDate == null || !userAction.getTimestamp().isAfter(endDate))) {
                filteredActions.add(userAction);
            }
        }
        return filteredActions;
    }

    /**
     * Exports the list of logged user actions to a file.
     *
     * @param filename The name of the file to which the actions will be exported.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void exportLog(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (UserAction userAction : actions) {
                writer.write(userAction.toString() + "\n");
            }
        }
    }
}
