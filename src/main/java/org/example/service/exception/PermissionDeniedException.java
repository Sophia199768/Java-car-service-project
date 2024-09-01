package org.example.service.exception;

/**
 * Exception thrown when a user is denied permission to perform a certain action.
 * <p>
 * This exception indicates that the user does not have the necessary permissions
 * to access or modify a resource or perform an operation.
 * </p>
 */
public class PermissionDeniedException extends Exception {
    /**
     * Constructs a new {@code PermissionDeniedException} with the specified detail message.
     *
     * @param message the detail message explaining why the permission was denied
     */
    public PermissionDeniedException(String message) {
        super(message);
    }
}
