package org.example.Exception;


/**
 * Custom exception class for handling application-specific errors.
 */
public class Exceptions extends java.lang.Exception {

    /**
     * Constructs a new Exceptions with the specified detail message.
     * @param message The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public Exceptions(String message) {
        super(message);
    }
}