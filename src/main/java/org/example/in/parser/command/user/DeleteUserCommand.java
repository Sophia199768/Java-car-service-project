package org.example.in.parser.command.user;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.service.Exception.Exceptions;


/**
 * The {@code DeleteUserCommand} class represents a command for deleting a user account.
 * It implements the {@code Commandable} interface and defines the behavior for deleting a user
 * by processing the provided command line arguments.
 */
@AllArgsConstructor
public class DeleteUserCommand implements Commandable {
    private UserController controller;
    private String[] tokens;

    /**
     * Executes the delete user command. This method processes the command line arguments to extract
     * the user ID, then deletes the user account through the controller. If the user attempting to delete
     * the account is a "CLIENT" and does not own the account being deleted, an exception is thrown,
     * as they do not have permission to delete other accounts.
     *
     * @param state The current application state, used to check user permissions and log actions.
     * @throws Exception If an error occurs during the delete process or if the user lacks permissions.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        Integer id = null;

        for (int i = 2; i < tokens.length; i++) {
            if ("--id".equals(tokens[i])) {
                id = Integer.parseInt(tokens[++i]);
            } else {
                throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        if (id == null) {
            throw new IllegalArgumentException("Required field is missing for user deletion: --id");
        }

        if (!id.equals(state.getUser().getId()) && state.getUser().getRole().toString().equals("CLIENT")) {
            throw new Exceptions("You have no permission to do this");
        }

        controller.delete(id);
        state.getAudit().logAction(state.getUser().getLogin(), "deleted an account");
    }
}
