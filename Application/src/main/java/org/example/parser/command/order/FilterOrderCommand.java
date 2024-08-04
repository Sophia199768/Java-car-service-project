package org.example.parser.command.order;

import lombok.AllArgsConstructor;
import org.example.ApplicationState;
import org.example.controller.OrderController;
import org.example.parser.command.Commandable;
import org.example.responsesAndRequestes.order.FilterOrderRequest;
import org.example.responsesAndRequestes.order.ShowOrderResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The {@code FilterOrderCommand} class represents a command for filtering orders based on various criteria.
 * It implements the {@code Commandable} interface and defines the behavior for filtering orders in the application.
 */
@AllArgsConstructor
public class FilterOrderCommand implements Commandable {
    private OrderController controller;
    private String[] tokens;

    /**
     * Executes the filter orders command. This method parses the input tokens to extract
     * the filtering criteria, performs the filtering, and then displays the results.
     *
     * @param state The current application state, used to fetch and filter orders.
     * @throws Exception If an error occurs during the filtering process, such as an invalid date format.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        Integer userId = null;
        Integer carId = null;
        String status = null;
        Date date = null;

        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--userId":
                    userId = Integer.parseInt(tokens[++i]);
                    break;
                case "--carId":
                    carId = Integer.parseInt(tokens[++i]);
                    break;
                case "--status":
                    status = tokens[++i];
                    break;
                case "--date":
                    date = parseDate(tokens[++i]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        List<ShowOrderResponse> orders = controller.getFilterOrders(new FilterOrderRequest(userId, carId, status, date));
        for (ShowOrderResponse order : orders) {
            System.out.println(order);
        }
    }

    /**
     * Parses a date string in the format "yyyy-MM-dd" to a {@code Date} object.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed {@code Date} object.
     * @throws IllegalArgumentException If the date string cannot be parsed due to an invalid format.
     */
    private Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
        }
    }
}
