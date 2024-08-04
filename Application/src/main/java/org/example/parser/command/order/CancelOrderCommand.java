package org.example.parser.command.order;

import lombok.AllArgsConstructor;
import org.example.ApplicationState;
import org.example.Exception.Exceptions;
import org.example.controller.OrderController;
import org.example.parser.command.Commandable;

/**
 * The {@code CancelOrderCommand} class represents a command for canceling an order.
 * It implements the {@code Commandable} interface and defines the behavior for
 * canceling an order in the application.
 */
@AllArgsConstructor
public class CancelOrderCommand implements Commandable {
    private OrderController controller;
    private String[] tokens;

    /**
     * Executes the cancel order command. This method parses the input tokens to extract
     * the order ID, checks the necessary permissions, and then performs the order cancellation.
     *
     * @param state The current application state, used to determine the user's role and
     *              to log the action performed.
     * @throws Exception If an error occurs during the cancellation process or if
     *                    the user does not have permission to cancel the order.
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
            throw new IllegalArgumentException("Required field is missing for order cancellation: --id");
        }

        if (state.getUser().getRole().toString().equals("CLIENT")) {
            throw new Exceptions("You have no permission to do this");
        }

        controller.cancelOrder(id);
        state.getAudit().logAction(state.getUser().getLogin(), "Canceled an order");
    }
}
