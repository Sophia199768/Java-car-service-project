package org.example.parser.command.car;

import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.car.CreateCarCommand;
import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.service.Exception.Exceptions;
import org.example.service.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCarCommandTest {

    private CarController controller;
    private ApplicationState state;
    private User user;
    private AuditService audit;

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
    @DisplayName("execute should throw IllegalArgumentException when required fields are missing")
    void execute_shouldThrowException_whenRequiredFieldsAreMissing() {
        String[] tokens = {"command", "create", "--carBrand", "Model"};
        CreateCarCommand command = new CreateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Required fields are missing for car creation: --carBrand, --carModel, --releaseYear", exception.getMessage());
    }

    @Test
    @DisplayName("execute should throw Exceptions when user role is CLIENT and lacks permission")
    void execute_shouldThrowException_whenClientRoleLacksPermission() {
        String[] tokens = {"command", "create", "--carBrand", "Model", "--carModel", "Camry", "--releaseYear", "2020-01-01", "--condition", "New", "--price", "30000"};
        CreateCarCommand command = new CreateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.CLIENT);

        Exception exception = assertThrows(Exceptions.class, () -> command.execute(state));

        assertEquals("You have no permission to do this", exception.getMessage());
    }

    @Test
    @DisplayName("execute should throw IllegalArgumentException when an unknown flag is provided")
    void execute_shouldThrowException_whenUnknownFlagIsProvided() {
        String[] tokens = {"command", "create", "--carBrand", "Model", "--unknownFlag", "value"};
        CreateCarCommand command = new CreateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }
}
