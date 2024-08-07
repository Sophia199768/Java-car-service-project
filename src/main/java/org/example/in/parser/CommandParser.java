package org.example.in.parser;

import lombok.RequiredArgsConstructor;

import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.application.controller.OrderController;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.in.parser.command.user.LogInCommand;
import org.example.in.parser.command.user.RegisterCommand;
import org.example.in.parser.strategy.CarParserStrategy;
import org.example.in.parser.strategy.OrderParserStrategy;
import org.example.in.parser.strategy.UserParserStrategy;


/**
 * The {@code CommandParser} class is responsible for parsing user input and
 * converting it into appropriate {@code Commandable} objects based on the
 * current application state and the provided input.
 */
@RequiredArgsConstructor
public class CommandParser implements Parser {
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
                    UserParserStrategy userParserStrategy = new UserParserStrategy(userController);
                    userParserStrategy.parse(tokens);
                case "car":
                    CarParserStrategy carParserStrategy = new CarParserStrategy(carController);
                    carParserStrategy.parse(tokens);
                case "order":
                    OrderParserStrategy orderParserStrategy = new OrderParserStrategy(orderController);
                    orderParserStrategy.parse(tokens);
                default:
                    System.out.println("There is no such command. Available categories are: user, car, order");
                    return null;
            }
        }
    }
}
