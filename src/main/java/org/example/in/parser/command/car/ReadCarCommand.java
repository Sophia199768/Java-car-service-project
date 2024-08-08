package org.example.in.parser.command.car;

import lombok.AllArgsConstructor;
import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.Commandable;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;


import java.util.List;

/**
 * The {@code ReadCarCommand} class implements the {@code Commandable} interface
 * to handle retrieving and displaying a list of all cars. It interacts with the
 * {@code CarController} to fetch the car data and then prints each car's details.
 */
@AllArgsConstructor
public class ReadCarCommand implements Commandable {
    private final CarController controller;

    /**
     * Executes the command to retrieve and display all cars.
     *
     * @param state The current application state, which is used for logging actions.
     * @throws Exception If any error occurs during the retrieval of car data.
     */
    @Override
    public void execute(ApplicationState state) throws Exception {
        List<ShowCarResponse> cars = controller.readCars();
        for (ShowCarResponse car : cars) {
            System.out.println(car);
        }
    }
}
