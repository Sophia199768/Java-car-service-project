package org.example.in.parser.command.user;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.LogInRequest;


/**
 * The {@code LogInCommand} class represents a command for user authentication and login.
 * It implements the {@code Commandable} interface and handles user login by processing
 * command line arguments for login credentials.
 */
@AllArgsConstructor
public class LogInCommand implements Commandable {
    private UserController controller;
    private String[] tokens;

    /**
     * Executes the login command. This method processes the command line arguments to extract
     * the login credentials, then attempts to authenticate the user through the controller.
     * If successful, the user is set in the application state and an action is logged.
     *
     * @param state The current application state, used to manage user sessions and log actions.
     * @throws Exception If an error occurs during the login process or if required fields are missing.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        String login = null;
        String password = null;

        for (int i = 1; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--login":
                    login = tokens[++i];
                    break;
                case "--password":
                    password = tokens[++i];
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        if (login == null || password == null) {
            throw new IllegalArgumentException("Required fields are missing for login: --login, --password");
        }

        User user = controller.logIn(new LogInRequest(login, password));
        state.setUser(user);
        state.getAudit().logAction(user.getLogin(), "Logged in");
    }
}
