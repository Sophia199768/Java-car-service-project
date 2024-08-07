package org.example.in.parser.command.order;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.OrderController;
import org.example.in.parser.command.Commandable;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.service.Exception.Exceptions;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code CreateOrderCommand} class represents a command for creating a new order.
 * It implements the {@code Commandable} interface and defines the behavior for
 * creating an order in the application.
 */
@AllArgsConstructor
public class CreateOrderCommand implements Commandable {
    private OrderController controller;
    private String[] tokens;

    /**
     * Executes the create order command. This method parses the input tokens to extract
     * the order details, checks the necessary permissions and order constraints, and then
     * performs the order creation.
     *
     * @param state The current application state, used to determine the user's role and
     *              to log the action performed.
     * @throws Exception If an error occurs during the order creation process, such as missing
     *                    required fields, invalid date format, or insufficient permissions.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        Integer userId = null;
        Integer carId = null;
        String information = null;
        Date date = null;
        String status = null;

        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--userId":
                    userId = Integer.parseInt(tokens[++i]);
                    break;
                case "--carId":
                    carId = Integer.parseInt(tokens[++i]);
                    break;
                case "--information":
                    information = tokens[++i];
                    break;
                case "--date":
                    date = parseDate(tokens[++i]);
                    break;
                case "--status":
                    status = tokens[++i];
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        if (userId == null || carId == null || status == null) {
            throw new IllegalArgumentException("Required fields are missing for order creation: --userId, --carId, --status");
        }

        if (state.getUser().getRole().toString().equals("CLIENT") && !status.equals("new")) {
            throw new Exceptions("You can create only \"new\" orders");
        }

        controller.createOrder(new CreateOrderRequest(userId, carId, information, date, status));

        state.getAudit().logAction(state.getUser().getLogin(), "Created an order");
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" to a {@code Date} object.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed {@code Date} object.
     * @throws ParseException If the date string cannot be parsed.
     */
    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }
}
