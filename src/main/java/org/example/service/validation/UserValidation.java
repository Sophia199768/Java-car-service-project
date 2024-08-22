package org.example.service.validation;

import org.example.core.responsesAndRequestes.user.CreateUserRequest;

/**
 * The UserValidation class supports basic validation for the user email and telephone number.
 */
public class UserValidation {

    /**
     * Check the email and phone.
     *
     * @param request the CreateUserRequest object containing users phone and email.
     */
    public Boolean valid(CreateUserRequest request) {
        return request.getPhone()
                .matches("^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}$")
                && request.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }
}
