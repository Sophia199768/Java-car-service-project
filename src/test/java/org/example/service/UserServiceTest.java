package org.example.service;

import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.*;
import org.example.service.Exception.Exceptions;
import org.example.service.mapper.UserMapper;
import org.example.service.repository.UserRepository;
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
    @DisplayName("createUser should create user when login is new")
    void createUser_shouldCreateUser_whenLoginIsNew() throws Exceptions {
        CreateUserRequest request = new CreateUserRequest();
        request.setLogin("newUser");
        User user = new User();
        when(userRepository.read()).thenReturn(Collections.emptyList());
        when(userMapper.toUser(request)).thenReturn(user);


        userService.createUser(request);

        verify(userRepository, times(1)).create(user);
    }

    @Test
    @DisplayName("createUser should throw exception when login is already used")
    void createUser_shouldThrowException_whenLoginIsUsed() throws Exceptions {
        CreateUserRequest request = new CreateUserRequest();
        request.setLogin("existingUser");
        User existingUser = new User();
        existingUser.setLogin("existingUser");
        when(userRepository.read()).thenReturn(Collections.singletonList(existingUser));

        assertThrows(Exceptions.class, () -> userService.createUser(request));
    }

    @Test
    @DisplayName("logIn should return user when login exists")
    void logIn_shouldReturnUser_whenLoginExists() throws Exceptions {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setLogin("existingUser");
        User existingUser = new User();
        existingUser.setLogin("existingUser");
        when(userRepository.read()).thenReturn(Collections.singletonList(existingUser));

        User result = userService.logIn(logInRequest);

        assertNotNull(result);
    }

    @Test
    @DisplayName("logIn should throw exception when login is invalid")
    void logIn_shouldThrowException_whenLoginIsInvalid() throws Exceptions {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setLogin("nonExistentUser");
        when(userRepository.read()).thenReturn(Collections.emptyList());

        assertThrows(Exceptions.class, () -> userService.logIn(logInRequest));
    }

    @Test
    @DisplayName("updateUser should update user role when user exists")
    void updateUser_shouldUpdateRole_whenUserExists() throws Exceptions {
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
    @DisplayName("read should return list of users")
    void read_shouldReturnListOfUsers() throws Exceptions {
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
    @DisplayName("deleteUser should delete user when user exists")
    void deleteUser_shouldDeleteUser_whenUserExists() throws Exceptions {
        User user = new User();
        user.setId(1);
        when(userRepository.read()).thenReturn(List.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(1);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    @DisplayName("filter should return list of users based on filter criteria")
    void filter_shouldReturnUsersBasedOnCriteria() throws Exceptions {
        User user = User.builder()
                .role(Role.CLIENT)
                .login("userLogin")
                .name("name")
                .cars(null)
                .build();
        ShowUserResponse showUserResponse = new ShowUserResponse();
        when(userRepository.read()).thenReturn(Collections.singletonList(user));
        when(userMapper.toShowUserResponse(user)).thenReturn(showUserResponse);

        List<ShowUserResponse> showUserRespons = userService.filter(new FilterUserRequest("name", null, null));

        assertEquals(1, showUserRespons.size());
        assertEquals(showUserResponse, showUserRespons.get(0));
        verify(userRepository, times(1)).read();
    }
}
