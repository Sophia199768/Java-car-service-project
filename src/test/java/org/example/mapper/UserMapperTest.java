package org.example.mapper;

import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.CreateUserRequest;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;
import org.example.service.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    @DisplayName("Should correctly map User to ShowUserResponse")
    void toShowUserResponse_shouldReturnCorrectShowUserResponse_whenUserIsProvided() {
        User newUser = new User();
        newUser.setId(1);
        newUser.setRole(Role.ADMIN);
        newUser.setLogin("NewLogin");
        newUser.setPassword("NewPassword");

        ShowUserResponse expectedResponse = new ShowUserResponse();
        expectedResponse.setId(1);
        expectedResponse.setRole(Role.ADMIN);
        expectedResponse.setLogin("NewLogin");

        ShowUserResponse actualResponse = UserMapper.INSTANCE.toShowUserResponse(newUser);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should correctly map CreateUserRequest to User")
    void toUser_shouldReturnCorrectUser_whenCreateUserRequestIsProvided() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setRole(Role.ADMIN);
        expectedUser.setLogin("NewLogin");
        expectedUser.setPassword("NewPassword");


        CreateUserRequest request = new CreateUserRequest();
        request.setRole(Role.ADMIN);
        request.setLogin("NewLogin");
        request.setPassword("NewPassword");

        User actualUser = UserMapper.INSTANCE.toUser(request);
        actualUser.setId(1);

        assertEquals(expectedUser, actualUser);
    }
}
