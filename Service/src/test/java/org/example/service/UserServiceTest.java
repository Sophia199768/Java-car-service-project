package org.example.service;

import org.example.Exception.Exceptions;
import org.example.mapper.UserMapper;
import org.example.model.user.Role;
import org.example.model.user.User;
import org.example.repository.UserRepository;
import org.example.responsesAndRequestes.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() throws Exceptions {
        CreateUserRequest request = new CreateUserRequest();
        request.setLogin("newUser");
        User user = new User();
        when(userRepository.read()).thenReturn(Collections.emptyList());
        when(userMapper.toUser(request)).thenReturn(user);
        doNothing().when(userRepository).create(user);

        userService.createUser(request);

        verify(userRepository, times(1)).create(user);
    }

    @Test
    void createUser_throwsException_whenLoginIsUsed() {
        CreateUserRequest request = new CreateUserRequest();
        request.setLogin("existingUser");
        User existingUser = new User();
        existingUser.setLogin("existingUser");
        when(userRepository.read()).thenReturn(Collections.singletonList(existingUser));

        assertThrows(Exceptions.class, () -> userService.createUser(request));
    }

    @Test
    void entrance() throws Exceptions {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setLogin("existingUser");
        User existingUser = new User();
        existingUser.setLogin("existingUser");
        when(userRepository.read()).thenReturn(Collections.singletonList(existingUser));

        User result = userService.logIn(logInRequest);

        assertNotNull(result);
    }

    @Test
    void entrance_throwsException_whenLoginIsInvalid() {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setLogin("nonExistentUser");
        when(userRepository.read()).thenReturn(Collections.emptyList());

        assertThrows(Exceptions.class, () -> userService.logIn(logInRequest));
    }

    @Test
    void changeRole() throws Exceptions {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(1);
        User user = new User();
        user.setId(1);

        when(userRepository.read()).thenReturn(List.of(user));
        doNothing().when(userRepository).update(user);

        userService.updateUser(request);

        verify(userRepository, times(1)).update(user);
    }

    @Test
    void read() {
        User user = new User();
        ShowUserResponse showUserResponse = new ShowUserResponse();
        when(userRepository.read()).thenReturn(Collections.singletonList(user));
        when(userMapper.toShowUserResponse(user)).thenReturn(showUserResponse);

        List<ShowUserResponse> showUserRespons = userService.read();

        assertEquals(1, showUserRespons.size());
        assertEquals(showUserResponse, showUserRespons.get(0));
        verify(userRepository, times(1)).read();
    }

    @Test
    void deleteUser() throws Exceptions {
        User user = new User();
        user.setId(1);
        when(userRepository.read()).thenReturn(List.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void filter() {
        User user = new User(Role.CLIENT, "userLogin", null, "name", null, null);
        ShowUserResponse showUserResponse = new ShowUserResponse();
        when(userRepository.read()).thenReturn(Collections.singletonList(user));
        when(userMapper.toShowUserResponse(user)).thenReturn(showUserResponse);

        List<ShowUserResponse> showUserRespons = userService.filter(new FilterUserRequest("name",null,null));

        assertEquals(1, showUserRespons.size());
        assertEquals(showUserResponse, showUserRespons.get(0));
        verify(userRepository, times(1)).read();
    }
}
