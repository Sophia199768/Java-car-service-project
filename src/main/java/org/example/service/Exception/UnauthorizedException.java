package org.example.service.Exception;

/**
 * Exception thrown when a user is not authorized to access a resource or perform an action.
 * <p>
 * This exception indicates that the user is not properly authenticated or lacks the necessary
 * credentials to access or perform a specific operation.
 * </p>
 */
public class UnauthorizedException extends Exception {
    /**
     * Constructs a new {@code UnauthorizedException} with the specified detail message.
     *
     * @param message the detail message explaining why the user is unauthorized
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
