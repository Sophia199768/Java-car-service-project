package org.example.parser.command.car;

import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.car.DeleteCarCommand;
import org.example.core.model.user.User;
import org.example.service.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("execute should successfully delete car when valid id is provided")
    void deleteCarTest_shouldExecuteSuccessfully_whenValidIdIsProvided() throws Exception {
        String[] tokens = {"command", "delete", "--id", "1"};
        DeleteCarCommand command = new DeleteCarCommand(controller, tokens);

        command.execute(state);

        verify(controller).deleteCar(1);
    }

    @Test
    @DisplayName("execute should throw IllegalArgumentException when required field is missing")
    void testMissingRequiredField_shouldThrowIllegalArgumentException_whenRequiredFieldsAreMissing() {
        String[] tokens = {"command", "delete"};
        DeleteCarCommand command = new DeleteCarCommand(controller, tokens);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Required field is missing for car deletion: --id", exception.getMessage());
    }

    @Test
    @DisplayName("execute should throw IllegalArgumentException when unknown flag is provided")
    void testUnknownFlag_shouldThrowIllegalArgumentException_when_UnknownFlagIsProvided() {
        String[] tokens = {"command", "delete", "--unknownFlag", "value"};
        DeleteCarCommand command = new DeleteCarCommand(controller, tokens);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }
}
