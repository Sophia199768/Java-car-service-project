package org.example.in.parser.command.user;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.core.responsesAndRequestes.user.FilterUserRequest;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;

import java.util.List;

/**
 * The {@code FilterUsersCommand} class represents a command for filtering user accounts based on various criteria.
 * It implements the {@code Commandable} interface and defines the behavior for filtering users by processing
 * the provided command line arguments.
 */
@AllArgsConstructor
public class FilterUsersCommand implements Commandable {
    private UserController controller;
    private String[] tokens;

    /**
     * Executes the filter users command. This method processes the command line arguments to extract filtering
     * criteria such as name, email, and phone. It then retrieves the list of users that match the specified criteria
     * through the controller and prints each user's information.
     *
     * @param state The current application state, used to manage user sessions and log actions.
     * @throws Exception If an error occurs during the filtering process.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        String name = null;
        String email = null;
        String phone = null;

        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
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

        List<ShowUserResponse> users = controller.getFilterUsers(new FilterUserRequest(name, phone, email));
        for (ShowUserResponse user : users) {
            System.out.println(user);
        }
    }
}
