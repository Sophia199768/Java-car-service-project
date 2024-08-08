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
        UserMapper mapper = new UserMapper();
        User newUser = User.builder()
                .id(1)
                .role(Role.ADMIN)
                .login("NewLogin")
                .password("NewPassword")
                .build();

        ShowUserResponse expectedResponse = new ShowUserResponse(1, Role.ADMIN, "NewLogin", null, null, null);
        ShowUserResponse actualResponse = mapper.toShowUserResponse(newUser);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should correctly map CreateUserRequest to User")
    void toUser_shouldReturnCorrectUser_whenCreateUserRequestIsProvided() {
        UserMapper mapper = new UserMapper();
        User expectedUser = User.builder()
                .id(1)
                .role(Role.ADMIN)
                .login("NewLogin")
                .password("NewPassword")
                .build();

        CreateUserRequest request = new CreateUserRequest(Role.ADMIN, "NewLogin", "NewPassword", null, null, null);
        User actualUser = mapper.toUser(request);
        actualUser.setId(1);

        assertEquals(expectedUser, actualUser);
    }
}
