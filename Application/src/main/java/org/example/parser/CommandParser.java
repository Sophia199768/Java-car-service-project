package org.example.parser;

import lombok.RequiredArgsConstructor;
import org.example.ApplicationState;
import org.example.controller.CarController;
import org.example.controller.OrderController;
import org.example.controller.UserController;
import org.example.parser.command.Commandable;
import org.example.parser.command.car.*;
import org.example.parser.command.order.*;
import org.example.parser.command.user.*;

/**
 * The {@code CommandParser} class is responsible for parsing user input and
 * converting it into appropriate {@code Commandable} objects based on the
 * current application state and the provided input.
 */
@RequiredArgsConstructor
public class CommandParser {
    private final CarController carController;
    private final OrderController orderController;
    private final UserController userController;

    /**
     * Parses the given input string and returns the corresponding {@code Commandable}
     * object that represents the desired command to be executed.
     * <p>
     * This method analyzes the input string to determine the appropriate command
     * based on the state of the application and the user's input. It supports different
     * commands for user management, car management, and order management.
     * </p>
     *
     * @param input The input string representing the command to be parsed.
     * @param state The current application state, used for determining user authentication
     *              and permissions.
     * @return A {@code Commandable} object representing the command to be executed,
     *         or {@code null} if the input is invalid or no command is found.
     */
    public Commandable parse(String input, ApplicationState state) {
        String[] tokens = input.split("\\s+");

        if (tokens.length == 0) {
            System.out.println("Please enter your command");
            return null;
        }

        if (state.getUser() == null) {
            switch (tokens[0]) {
                case "login":
                    return new LogInCommand(userController, tokens);
                case "register":
                    return new RegisterCommand(userController, tokens);
                default:
                    System.out.println("There is no such command. You can use only login or register");
                    return null;
            }
        } else {
            switch (tokens[0]) {
                case "user":
                    if (tokens.length == 1) {
                        System.out.println("""
                                Options:
                                read
                                delete
                                filter
                                """);
                        return null;
                    }
                    switch (tokens[1]) {
                        case "read":
                            return new ReadUsersCommand(userController);
                        case "delete":
                            return new DeleteUserCommand(userController, tokens);
                        case "filter":
                            return new FilterUsersCommand(userController, tokens);
                        default:
                            System.out.println("Invalid user command. Options are: read, delete, filter");
                            return null;
                    }
                case "car":
                    if (tokens.length == 1) {
                        System.out.println("""
                                Options:
                                create
                                update
                                read
                                delete
                                filter
                                """);
                        return null;
                    }
                    switch (tokens[1]) {
                        case "create":
                            return new CreateCarCommand(carController, tokens);
                        case "update":
                            return new UpdateCarCommand(carController, tokens);
                        case "read":
                            return new ReadCarCommand(carController);
                        case "delete":
                            return new DeleteCarCommand(carController, tokens);
                        case "filter":
                            return new FilterCarCommand(carController, tokens);
                        default:
                            System.out.println("Invalid car command. Options are: create, update, read, delete, filter");
                            return null;
                    }
                case "order":
                    if (tokens.length == 1) {
                        System.out.println("""
                                Options:
                                create
                                update
                                read
                                cancel
                                filter
                                """);
                        return null;
                    }
                    switch (tokens[1]) {
                        case "create":
                            return new CreateOrderCommand(orderController, tokens);
                        case "read":
                            return new ReadOrderCommand(orderController);
                        case "update":
                            return new UpdateOrderCommand(orderController, tokens);
                        case "cancel":
                            return new CancelOrderCommand(orderController, tokens);
                        case "filter":
                            return new FilterOrderCommand(orderController, tokens);
                        default:
                            System.out.println("Invalid order command. Options are: create, update, read, cancel, filter");
                            return null;
                    }
                default:
                    System.out.println("There is no such command. Available categories are: user, car, order");
                    return null;
            }
        }
    }
}
