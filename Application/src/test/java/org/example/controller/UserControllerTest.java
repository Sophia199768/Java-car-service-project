package org.example.controller;

import org.example.Exception.Exceptions;
import org.example.model.user.User;
import org.example.responsesAndRequestes.user.*;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registration() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        doNothing().when(userService).createUser(request);

        userController.registration(request);

        verify(userService, times(1)).createUser(request);
    }

    @Test
    void enter() throws Exception {
        LogInRequest logInRequest = new LogInRequest();
        User expectedResponse = new User();
        when(userService.logIn(logInRequest)).thenReturn(expectedResponse);

        User actualResponse = userController.logIn(logInRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).logIn(logInRequest);
    }

    @Test
    void update() throws Exceptions {
        UpdateUserRequest request = new UpdateUserRequest();
        doNothing().when(userService).updateUser(request);

        userController.update(request);

        verify(userService, times(1)).updateUser(request);
    }

    @Test
    void readAllUsers() {
        List<ShowUserResponse> expectedResponse = Collections.singletonList(new ShowUserResponse());
        when(userService.read()).thenReturn(expectedResponse);

        List<ShowUserResponse> actualResponse = userController.readAllUsers();

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).read();
    }

    @Test
    void getFilterUsers() {
        FilterUserRequest request = new FilterUserRequest();
        List<ShowUserResponse> expectedResponse = Collections.singletonList(new ShowUserResponse());
        when(userService.filter(request)).thenReturn(expectedResponse);

        List<ShowUserResponse> actualResponse = userController.getFilterUsers(request);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).filter(request);
    }
}
