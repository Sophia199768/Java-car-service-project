package org.example.parser.command.car;

import lombok.AllArgsConstructor;
import org.example.ApplicationState;
import org.example.Exception.Exceptions;
import org.example.controller.CarController;
import org.example.parser.command.Commandable;
import org.example.responsesAndRequestes.car.CreateCarRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code CreateCarCommand} class implements the {@code Commandable} interface
 * to handle the creation of a new car. It parses command-line arguments, validates them,
 * and then uses the {@code CarController} to create a new car with the provided details.
 */
@AllArgsConstructor
public class CreateCarCommand implements Commandable {
    private final CarController controller;
    private final String[] tokens;

    /**
     * Executes the command to create a new car based on the provided tokens.
     *
     * @param state The current application state, used for authorization and logging actions.
     * @throws Exception If any error occurs during the execution of the command.
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

        if (carBrand == null || carModel == null || releaseYear == null) {
            throw new IllegalArgumentException("Required fields are missing for car creation: --carBrand, --carModel, --releaseYear");
        }

        if (state.getUser().getRole().toString().equals("CLIENT")) {
            throw new Exceptions("You have no permission to do this");
        }

        controller.createNewCar(new CreateCarRequest(carBrand, carModel, releaseYear, condition, price));
        state.getAudit().logAction(state.getUser().getLogin(), "Added a new car");
    }

    /**
     * Parses a date string into a {@code Date} object using the format "yyyy-MM-dd".
     *
     * @param dateString The date string to parse.
     * @return The parsed {@code Date} object.
     * @throws ParseException If the date string cannot be parsed.
     */
    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }
}
