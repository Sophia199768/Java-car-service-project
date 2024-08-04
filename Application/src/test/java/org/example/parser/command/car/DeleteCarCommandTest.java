package org.example.parser.command.car;

import org.example.ApplicationState;
import org.example.controller.CarController;
import org.example.model.user.User;
import org.example.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCarCommandTest {

    private CarController controller;
    private ApplicationState state;
    private User user;
    private AuditService audit = new AuditService();

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
        String[] tokens = {"command", "delete", "--id", "1"};
        DeleteCarCommand command = new DeleteCarCommand(controller, tokens);

        command.execute(state);

        verify(controller).deleteCar(1);
    }

    @Test
    void testMissingRequiredField() {
        String[] tokens = {"command", "delete"};
        DeleteCarCommand command = new DeleteCarCommand(controller, tokens);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Required field is missing for car deletion: --id", exception.getMessage());
    }

    @Test
    void testUnknownFlag() {
        String[] tokens = {"command", "delete", "--unknownFlag", "value"};
        DeleteCarCommand command = new DeleteCarCommand(controller, tokens);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }
}
