package org.example.in.parser.command.user;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;
import org.example.service.Exception.Exceptions;


import java.util.List;

/**
 * The {@code ReadUsersCommand} class represents a command for retrieving and displaying
 * a list of all users. It implements the {@code Commandable} interface and interacts
 * with the {@code UserController} to fetch and print user information.
 */
@AllArgsConstructor
public class ReadUsersCommand implements Commandable {
    private UserController controller;

    /**
     * Executes the command to read and display all users. This method retrieves the list
     * of users from the controller and prints each user's information to the console.
     *
     * @param state The current application state. This parameter is not used in this method,
     *              but is required by the {@code Commandable} interface.
     * @throws Exceptions If an error occurs while retrieving the user data from the controller.
     */
    @Override
    public void execute(ApplicationState state) throws Exceptions {
        List<ShowUserResponse> users = controller.readAllUsers();
        for (ShowUserResponse user : users) {
            System.out.println(user);
        }
    }
}
