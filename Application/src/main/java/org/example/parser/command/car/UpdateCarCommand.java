package org.example.parser.command.car;

import lombok.AllArgsConstructor;
import org.example.ApplicationState;
import org.example.Exception.Exceptions;
import org.example.controller.CarController;
import org.example.parser.command.Commandable;
import org.example.responsesAndRequestes.car.UpdateCarRequest;

/**
 * The {@code UpdateCarCommand} class implements the {@code Commandable} interface
 * to handle updating the details of an existing car. It interacts with the
 * {@code CarController} to apply the update and checks for permissions before performing the action.
 */
@AllArgsConstructor
public class UpdateCarCommand implements Commandable {
    private final CarController controller;
    private final String[] tokens;

    /**
     * Executes the command to update a car's details.
     * <p>
     * This method parses the command-line arguments to extract the car's ID,
     * condition, and price. It checks for required fields and permission
     * before updating the car. The updated car details are then sent to
     * the {@code CarController} for processing.
     * </p>
     *
     * @param state The current application state, which is used for logging actions
     *              and checking user permissions.
     * @throws Exception If any error occurs during the update process, including
     *                    invalid arguments or permission issues.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        Integer id = null;
        String condition = null;
        Long price = null;

        for (int i = 2; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "--id":
                    id = Integer.parseInt(tokens[++i]);
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

        if (id == null) {
            throw new IllegalArgumentException("Required field is missing for car update: --id");
        }

        if (state.getUser().getRole().toString().equals("CLIENT")) {
            throw new Exceptions("You have no permission to do this");
        }

        controller.update(new UpdateCarRequest(id, condition, price));
        state.getAudit().logAction(state.getUser().getLogin(), "Updated a car");
    }
}
