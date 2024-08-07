package org.example.parser.command.car;

import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.car.UpdateCarCommand;
import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCarCommandTest {

    private CarController controller;
    private ApplicationState state;
    private AuditService audit;
    private User user;

    @BeforeEach
    void setUp() {
        controller = mock(CarController.class);
        state = mock(ApplicationState.class);
        user = mock(User.class);
        audit = mock(AuditService.class);

        when(state.getUser()).thenReturn(user);
        when(state.getAudit()).thenReturn(audit);
    }

    @Test
    @DisplayName("Should successfully update car when valid ID and fields are provided")
    void updateCar_shouldUpdateSuccessfully_whenValidFieldsProvided() throws Exception {
        String[] tokens = {"command", "update", "--id", "1", "--condition", "Used", "--price", "15000"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        command.execute(state);

        verify(controller).update(any(UpdateCarRequest.class));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when required field is missing")
    void updateCar_shouldThrowException_whenRequiredFieldIsMissing() {
        String[] tokens = {"command", "update", "--condition", "Used"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Required field is missing for car update: --id", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw Exceptions when user role is CLIENT and attempts to update car")
    void updateCar_shouldThrowException_whenUserRoleIsClient() {
        String[] tokens = {"command", "update", "--id", "1", "--condition", "Used", "--price", "15000"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.CLIENT);

        Exception exception = assertThrows(Exceptions.class, () -> command.execute(state));

        assertEquals("You have no permission to do this", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when unknown flag is provided")
    void updateCar_shouldThrowException_whenUnknownFlagIsProvided() {
        String[] tokens = {"command", "update", "--id", "1", "--unknownFlag", "value"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw NumberFormatException when price format is invalid")
    void updateCar_shouldThrowException_whenPriceFormatIsInvalid() {
        String[] tokens = {"command", "update", "--id", "1", "--price", "invalidPrice"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(NumberFormatException.class, () -> command.execute(state));

        assertTrue(exception.getMessage().contains("For input string: \"invalidPrice\""));
    }
}
