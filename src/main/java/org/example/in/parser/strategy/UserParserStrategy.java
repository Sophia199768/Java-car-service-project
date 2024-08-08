package org.example.in.parser.strategy;


import lombok.AllArgsConstructor;
import org.example.application.controller.UserController;
import org.example.in.parser.command.Commandable;
import org.example.in.parser.command.user.DeleteUserCommand;
import org.example.in.parser.command.user.FilterUsersCommand;
import org.example.in.parser.command.user.ReadUsersCommand;

@AllArgsConstructor
public class UserParserStrategy implements Strategy {
    private UserController userController;

    @Override
    public Commandable parse(String[] tokens) {
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
    }

}
