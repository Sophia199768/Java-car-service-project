package org.example.parser.command.car;

import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.car.ReadCarCommand;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ReadCarCommandTest {

    private CarController controller;
    private ApplicationState state;

    @BeforeEach
    void setUp() {
        controller = mock(CarController.class);
        state = mock(ApplicationState.class);
    }

    @Test
    @DisplayName("Should successfully read cars and return results")
    void readTest_shouldSuccessfullyReadCars() throws Exception {
        ReadCarCommand command = new ReadCarCommand(controller);

        List<ShowCarResponse> expectedCars = Collections.singletonList(new ShowCarResponse());
        when(controller.readCars()).thenReturn(expectedCars);

        command.execute(state);

        verify(controller).readCars();
    }
}
