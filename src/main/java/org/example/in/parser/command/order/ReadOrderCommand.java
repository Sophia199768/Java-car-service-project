package org.example.in.parser.command.order;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.OrderController;
import org.example.in.parser.command.Commandable;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;


import java.util.List;

/**
 * The {@code ReadOrderCommand} class represents a command for retrieving and displaying all orders.
 * It implements the {@code Commandable} interface and defines the behavior for fetching and displaying orders
 * based on the user's role.
 */
@AllArgsConstructor
public class ReadOrderCommand implements Commandable {
    private OrderController controller;

    /**
     * Executes the read orders command. This method retrieves all orders from the controller and
     * displays them.
     *
     * @param state The current application state, used to fetch and filter orders based on user role.
     * @throws Exception If an error occurs during the retrieval or display of orders.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        List<ShowOrderResponse> orders = controller.readAllOrders();

        if (state.getUser().getRole().toString().equals("CLIENT")) {
            orders = orders.stream()
                    .filter(it -> it.getUserId().equals(state.getUser().getId()))
                    .toList();
        }

        for (ShowOrderResponse order : orders) {
            System.out.println(order);
        }
    }
}
