package org.example.parser.command.car;

import org.example.ApplicationState;
import org.example.Exception.Exceptions;
import org.example.controller.CarController;
import org.example.model.user.Role;
import org.example.model.user.User;
import org.example.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
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
    void testMissingRequiredFields() {
        String[] tokens = {"command", "create", "--carBrand", "Model"};
        CreateCarCommand command = new CreateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Required fields are missing for car creation: --carBrand, --carModel, --releaseYear", exception.getMessage());
    }

    @Test
    void testClientRoleHasNotPermission() {
        String[] tokens = {"command", "create", "--carBrand", "Model", "--carModel", "Camry", "--releaseYear", "2020-01-01", "--condition", "New", "--price", "30000"};
        CreateCarCommand command = new CreateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.CLIENT);

        Exception exception = assertThrows(Exceptions.class, () -> command.execute(state));

        assertEquals("You have no permission to do this", exception.getMessage());
    }

    @Test
    void testExecuteUnknownFlag() {
        String[] tokens = {"command", "create", "--carBrand", "Model", "--unknownFlag", "value"};
        CreateCarCommand command = new CreateCarCommand(controller, tokens);

        when(user.getRole()).thenReturn(Role.ADMIN);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }


}
