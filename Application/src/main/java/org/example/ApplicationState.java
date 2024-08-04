package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.user.User;
import org.example.service.AuditService;

/**
 * The {@code ApplicationState} class represents the state of the application during its runtime.
 * It holds information about the currently logged-in user and provides access to the audit service.
 */
@Getter
@Setter
@NoArgsConstructor
public class ApplicationState {
    /**
     * The currently logged-in user. This field holds the user details for the current session.
     */
    private User user;

    /**
     * The audit service used for logging actions performed by users. This field is initialized
     * when an {@code ApplicationState} object is created.
     */
    private final AuditService audit = new AuditService();
}
