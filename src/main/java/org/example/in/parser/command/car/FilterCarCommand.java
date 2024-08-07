package org.example.in.parser.command.car;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.Commandable;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The {@code FilterCarCommand} class implements the {@code Commandable} interface
 * to handle filtering and retrieving cars based on provided criteria. It parses command-line arguments
 * to build a {@code FilterCarRequest} and then uses the {@code CarController} to fetch and display
 * the filtered cars.
 */
@AllArgsConstructor
public class FilterCarCommand implements Commandable {
    private final CarController controller;
    private final String[] tokens;

    /**
     * Executes the command to filter and retrieve cars based on the provided tokens.
     *
     * @param state The current application state, which includes user information and audit logging.
     * @throws Exception If any error occurs during the execution of the command, such as
     *                   invalid arguments or date parsing issues.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        String carBrand = null;
        String carModel = null;
        Date releaseYear = null;
        String condition = null;
        Long price = null;

        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--carBrand":
                    carBrand = tokens[++i];
                    break;
                case "--carModel":
                    carModel = tokens[++i];
                    break;
                case "--releaseYear":
                    releaseYear = parseDate(tokens[++i]);
                    break;
                case "--condition":
                    condition = tokens[++i];
                    break;
                case "--price":
                    price = Long.parseLong(tokens[++i]);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + tokens[i]);
            }
        }

        List<ShowCarResponse> cars = controller.getFilterCars(new FilterCarRequest(carBrand, carModel, releaseYear, condition, price));
        for (ShowCarResponse car : cars) {
            System.out.println(car);
        }
    }

    /**
     * Parses a date string into a {@code Date} object using the format "yyyy-MM-dd".
     *
     * @param dateString The date string to parse.
     * @return The parsed {@code Date} object.
     * @throws IllegalArgumentException If the date string cannot be parsed.
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
