package org.example.parser.command.car;

import lombok.AllArgsConstructor;
import org.example.ApplicationState;
import org.example.controller.CarController;
import org.example.parser.command.Commandable;

/**
 * The {@code DeleteCarCommand} class implements the {@code Commandable} interface
 * to handle the deletion of a car. It parses command-line arguments to retrieve the car ID,
 * validates the input, and then uses the {@code CarController} to delete the specified car.
 */
@AllArgsConstructor
public class DeleteCarCommand implements Commandable {
    private final CarController controller;
    private final String[] tokens;

    /**
     * Executes the command to delete a car based on the provided tokens.
     *
     * @param state The current application state, used for authorization and logging actions.
     * @throws Exception If any error occurs during the execution of the command, such as
     *                   invalid arguments or missing required fields.
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
            throw new IllegalArgumentException("Required field is missing for car deletion: --id");
        }

        controller.deleteCar(id);

        state.getAudit().logAction(state.getUser().getLogin(), "Deleted a car");
    }
}
