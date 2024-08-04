package org.example.parser.command.car;

import org.example.ApplicationState;
import org.example.controller.CarController;
import org.example.responsesAndRequestes.car.ShowCarResponse;
import org.junit.jupiter.api.BeforeEach;
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
    void testSuccess() throws Exception {
        ReadCarCommand command = new ReadCarCommand(controller);

        List<ShowCarResponse> expectedCars = Collections.singletonList(new ShowCarResponse());
        when(controller.readCars()).thenReturn(expectedCars);

        command.execute(state);

        verify(controller).readCars();
    }
}
