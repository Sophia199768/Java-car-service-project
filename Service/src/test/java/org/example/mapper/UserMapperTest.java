package org.example.mapper;

import org.example.model.user.Role;
import org.example.model.user.User;
import org.example.responsesAndRequestes.user.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void toUserResponse() {
        UserMapper mapper = new UserMapper();
        User newUser = new User(Role.ADMIN, "NewLogin", "NewPassword", null, null, null);
        newUser.setId(1);

        ShowUserResponse response = new ShowUserResponse(1, Role.ADMIN, "NewLogin", null, null, null);
        ShowUserResponse showUserResponse = mapper.toShowUserResponse(newUser);

        assertEquals(showUserResponse, response);
    }

    @Test
    void toUser() {
        UserMapper mapper = new UserMapper();

        User newUser = new User(Role.ADMIN, "NewLogin", "NewPassword", null, null, null);
        newUser.setId(1);

        CreateUserRequest request = new CreateUserRequest(Role.ADMIN, "NewLogin", "NewPassword", null, null, null);

        User user = mapper.toUser(request);
        user.setId(1);

        assertEquals(user, newUser);
    }
}