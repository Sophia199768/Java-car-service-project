package org.example.controllers;

import org.example.application.controllers.UserController;
import org.example.core.responsesAndRequestes.user.CreateUserRequest;
import org.example.core.responsesAndRequestes.user.FilterUserRequest;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;
import org.example.core.responsesAndRequestes.user.UpdateUserRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Register user - should register a user successfully when valid request is provided")
    void registerUser() throws Exceptions {
        CreateUserRequest request = new CreateUserRequest();
        // Call the method
        userController.registerUser(request);
        // Verify the service method was called
        verify(userService).createUser(request);
    }

    @Test
    @DisplayName("Update user phone number - should update user phone number successfully when valid request is provided")
    void updateUserPhoneNumber() throws Exceptions {
        UpdateUserRequest request = new UpdateUserRequest();
        // Call the method
        userController.updateUser(request);
        // Verify the service method was called
        verify(userService).updateUser(request);
    }


    @Test
    @DisplayName("Delete user - should delete the user successfully when valid id is provided")
    void deleteUser() throws Exceptions {
        Integer id = 1;
        // Call the method
        userController.deleteUser(id);
        // Verify the service method was called
        verify(userService).deleteUser(id);
    }

    @Test
    @DisplayName("Filter users - should return a list of users when valid filter request is provided")
    void filterUsers() throws Exceptions {
        FilterUserRequest request = new FilterUserRequest();
        List<ShowUserResponse> expectedResponse = List.of(new ShowUserResponse());
        when(userService.filter(any(FilterUserRequest.class))).thenReturn(expectedResponse);
        // Call the method
        List<ShowUserResponse> response = userController.filterUsers(request).getBody();
        // Assert the response
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Read users - should return a list of all users")
    void read() throws Exceptions {
        List<ShowUserResponse> expectedResponse = List.of(new ShowUserResponse());
        when(userService.read()).thenReturn(expectedResponse);
        // Call the method
        List<ShowUserResponse> response = userController.read().getBody();
        // Assert the response
        assertEquals(expectedResponse, response);
    }
}
