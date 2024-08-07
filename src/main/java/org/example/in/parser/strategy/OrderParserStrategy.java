package org.example.in.parser.strategy;

import lombok.AllArgsConstructor;
import org.example.application.controller.OrderController;
import org.example.in.parser.command.Commandable;
import org.example.in.parser.command.order.*;

@AllArgsConstructor
public class OrderParserStrategy implements Strategy {
    private OrderController orderController;

    @Override
    public Commandable parse(String[] tokens) {
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
    }
}
