package org.example.parser.command.order;

import lombok.AllArgsConstructor;
import org.example.ApplicationState;
import org.example.Exception.Exceptions;
import org.example.controller.OrderController;
import org.example.parser.command.Commandable;
import org.example.responsesAndRequestes.order.UpdateOrderRequest;

/**
 * The {@code UpdateOrderCommand} class represents a command for updating an existing order.
 * It implements the {@code Commandable} interface and defines the behavior for updating an order
 * by processing the provided command line arguments.
 */
@AllArgsConstructor
public class UpdateOrderCommand implements Commandable {
    private OrderController controller;
    private String[] tokens;

    /**
     * Executes the update order command. This method processes the command line arguments to extract
     * the order ID, status, and additional information, then updates the order through the controller.
     * If the user has the role "CLIENT", an exception is thrown, as they do not have permission to update orders.
     *
     * @param state The current application state, used to check user role and log actions.
     * @throws Exception If an error occurs during the update process or if the user lacks permissions.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        Integer id = null;
        String status = null;
        String information = null;

        // Process command line arguments
        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--id":
                    id = Integer.parseInt(tokens[++i]);
                    break;
                case "--status":
                    status = tokens[++i];
                    break;
                case "--information":
                    information = tokens[++i];
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        if (id == null) {
            throw new IllegalArgumentException("Required field is missing for order update: --id");
        }

        if (state.getUser().getRole().toString().equals("CLIENT")) {
            throw new Exceptions("You have no permission to do this");
        }

        controller.updateOrder(new UpdateOrderRequest(id, status, information));
        state.getAudit().logAction(state.getUser().getLogin(), "Updated an order");
    }
}
