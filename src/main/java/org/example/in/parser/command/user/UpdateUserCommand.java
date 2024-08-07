package org.example.in.parser.command.user;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.UpdateUserRequest;
import org.example.service.Exception.Exceptions;


/**
 * The {@code UpdateUserCommand} class represents a command for updating an existing user.
 * It implements the {@code Commandable} interface and interacts with the {@code UserController}
 * to process user update requests.
 */
@AllArgsConstructor
public class UpdateUserCommand implements Commandable {
    private UserController controller;
    private String[] tokens;

    /**
     * Executes the command to update an existing user. This method parses the input tokens to
     * retrieve the required user information and updates the user details via the {@code UserController}.
     * It also performs authorization checks to ensure the user has the necessary permissions.
     * Finally, it logs the update action to the audit system.
     *
     * @param state The current application state. It is used to access the current user and the audit system.
     * @throws Exceptions If an error occurs during the update process or if required fields are missing,
     *                    or if the user does not have permission to perform the update.
     */
    @Override
    public void execute(ApplicationState state) throws Exceptions {
        User user = state.getUser();
        Integer id = null;
        Role role = null;
        String name = null;
        String email = null;
        String phone = null;

        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--id":
                    id = Integer.parseInt(tokens[++i]);
                    break;
                case "--role":
                    role = Role.valueOf(tokens[++i].toUpperCase());
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

        if (id == null) {
            throw new IllegalArgumentException("Required field is missing for update: --id");
        }

        if (!id.equals(user.getId()) && user.getRole().toString().equals("CLIENT")) {
            throw new Exceptions("You have no permission to do this");
        }

        if (!user.getRole().toString().equals("ADMIN") && role != null) {
            throw new Exceptions("You have no permission to do this");
        }

        controller.update(new UpdateUserRequest(id, role, name, email, phone));

        state.getAudit().logAction(user.getLogin(), "Updated");
    }
}
