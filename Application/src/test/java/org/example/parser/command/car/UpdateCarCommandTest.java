package org.example.parser.command.car;

import org.example.ApplicationState;
import org.example.Exception.Exceptions;
import org.example.controller.CarController;
import org.example.model.user.Role;
import org.example.model.user.User;
import org.example.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCarCommandTest {

    private CarController controller;
    private ApplicationState state;
    private AuditService audit = new AuditService();
    private User user;

    @BeforeEach
    void setUp() {
        controller = mock(CarController.class);
        state = mock(ApplicationState.class);
        user = mock(User.class);

        when(state.getUser()).thenReturn(user);
        when(state.getAudit()).thenReturn(audit);
    }

    @Test
    void testSuccess() throws Exception {
        String[] tokens = {"command", "update", "--id", "1", "--condition", "Used", "--price", "15000"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        command.execute(state);
        verify(controller).update(any(UpdateCarRequest.class));

    }

    @Test
    void testMissingRequiredField() {
        String[] tokens = {"command", "update", "--condition", "Used"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Required field is missing for car update: --id", exception.getMessage());
    }

    @Test
    void testClientRoleHasNoPermission() {
        String[] tokens = {"command", "update", "--id", "1", "--condition", "Used", "--price", "15000"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.CLIENT);

        Exception exception = assertThrows(Exceptions.class, () -> command.execute(state));

        assertEquals("You have no permission to do this", exception.getMessage());
    }

    @Test
    void testExecuteUnknownFlag() {
        String[] tokens = {"command", "update", "--id", "1", "--unknownFlag", "value"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }

    @Test
    void testInvalidPriceFormat() {
        String[] tokens = {"command", "update", "--id", "1", "--price", "invalidPrice"};
        UpdateCarCommand command = new UpdateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(NumberFormatException.class, () -> command.execute(state));

        assertTrue(exception.getMessage().contains("For input string: \"invalidPrice\""));
    }
}
