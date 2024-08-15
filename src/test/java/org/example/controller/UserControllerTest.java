package org.example.controller;

import org.example.application.controller.UserController;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.*;
import org.example.service.Exception.Exceptions;
import org.example.service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Should register new user when all details are correct")
    void registration_shouldRegisterNewUser_whenAllDetailsAreCorrect() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        doNothing().when(userService).createUser(request);

        userController.registration(request);
        verify(userService, times(1)).createUser(request);
    }

    @Test
    @DisplayName("Should log in user and return user object when credentials are correct")
    void enter_shouldReturnUser_whenCredentialsAreCorrect() throws Exception {
        LogInRequest logInRequest = new LogInRequest();
        User expectedResponse = User.builder().build();
        when(userService.logIn(logInRequest)).thenReturn(expectedResponse);
        User actualResponse = userController.logIn(logInRequest);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).logIn(logInRequest);
    }

    @Test
    @DisplayName("Should update user details when request is valid")
    void update_shouldUpdateUser_whenRequestIsValid() throws Exceptions {
        UpdateUserRequest request = new UpdateUserRequest();
        doNothing().when(userService).updateUser(request);

        userController.update(request);
        verify(userService, times(1)).updateUser(request);
    }

    @Test
    @DisplayName("Should return all users when requested")
    void readAllUsers_shouldReturnAllUsers_whenRequested() throws Exceptions {
        List<ShowUserResponse> expectedResponse = Collections.singletonList(new ShowUserResponse());
        when(userService.read()).thenReturn(expectedResponse);

        List<ShowUserResponse> actualResponse = userController.readAllUsers();

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).read();
    }

    @Test
    @DisplayName("Should return filtered users when filter criteria are provided")
    void getFilterUsers_shouldReturnFilteredUsers_whenFilterCriteriaAreProvided() throws Exceptions {

        FilterUserRequest request = new FilterUserRequest();
        List<ShowUserResponse> expectedResponse = Collections.singletonList(new ShowUserResponse());
        when(userService.filter(request)).thenReturn(expectedResponse);

        List<ShowUserResponse> actualResponse = userController.getFilterUsers(request);

        assertEquals(expectedResponse, actualResponse);
        verify(userService, times(1)).filter(request);
    }
}
