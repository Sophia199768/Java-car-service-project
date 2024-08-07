package org.example.in.parser.command;


import org.example.application.ApplicationState;

/**
 * The {@code Commandable} interface represents a command that can be executed
 * within the application. Implementing classes should provide the logic for
 * executing the command by interacting with the {@code ApplicationState}.
 */
public interface Commandable {

    /**
     * Executes the command using the given application state.
     *
     * @param state The current application state, used for performing actions
     *              and accessing application-wide resources.
     * @throws Exception If any error occurs during command execution, such
     *                    as invalid arguments or operational issues.
     */
    void execute(ApplicationState state) throws Exception;
}
