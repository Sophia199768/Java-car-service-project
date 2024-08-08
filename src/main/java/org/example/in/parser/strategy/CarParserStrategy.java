package org.example.in.parser.strategy;

import lombok.AllArgsConstructor;
import org.example.application.controller.CarController;
import org.example.in.parser.command.Commandable;
import org.example.in.parser.command.car.*;

@AllArgsConstructor
public class CarParserStrategy implements Strategy {
    private CarController carController;

    @Override
    public Commandable parse(String[] tokens) {
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
    }
}
