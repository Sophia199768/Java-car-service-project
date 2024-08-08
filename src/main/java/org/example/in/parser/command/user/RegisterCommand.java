package org.example.in.parser.command.user;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.core.model.user.Role;
import org.example.core.responsesAndRequestes.user.CreateUserRequest;

/**
 * The {@code RegisterCommand} class represents a command for registering a new user.
 * It implements the {@code Commandable} interface and interacts with the {@code UserController}
 * to process user registration requests.
 */
@AllArgsConstructor
public class RegisterCommand implements Commandable {
    private UserController controller;
    private String[] tokens;

    /**
     * Executes the command to register a new user. This method parses the input tokens to
     * retrieve the required user information, then uses the {@code UserController} to
     * create a new user. Finally, it logs the registration action to the audit system.
     *
     * @param state The current application state. It is used to access the audit system for logging actions.
     * @throws Exception If an error occurs during the registration process or if required fields are missing.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        Role role = null;
        String login = null;
        String password = null;
        String name = null;
        String email = null;
        String phone = null;

        for (int i = 1; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--role":
                    role = Role.valueOf(tokens[++i].toUpperCase());
                    break;
                case "--login":
                    login = tokens[++i];
                    break;
                case "--password":
                    password = tokens[++i];
                    break;
                case "--name":
                    name = tokens[++i];
                    break;
                case "--email":
                    email = tokens[++i];
                    break;
                case "--phone":
                    phone = tokens[++i];
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        if (role == null || login == null || password == null) {
            throw new IllegalArgumentException("Required fields are missing for registration: --role, --login, --password");
        }

        controller.registration(new CreateUserRequest(role, login, password, name, email, phone));

        state.getAudit().logAction(login, "Created an account");
    }
}
