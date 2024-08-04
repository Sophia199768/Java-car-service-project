package org.example.controller;

import org.example.model.user.User;
import org.example.responsesAndRequestes.order.ShowOrderResponse;

import java.util.List;

/**
 * The {@code AuthController} class provides methods to check user permissions and secure order access based on user roles.
 */
public class AuthController {

    /**
     * Checks if the given user has permission to execute the specified command.
     *
     * @param user the user whose permissions are to be checked
     * @param command the command to check against the user's permissions
     * @return {@code true} if the user has permission to execute the command, {@code false} otherwise
     */
    public Boolean checkPermission(User user, String command) {
        if (user.getRole().toString().equals("ADMIN")) return true;

        if (user.getRole().toString().equals("MANAGER") && command.equals("change_role")) return false;

        if (user.getRole().toString().equals("CLIENT") && command.equals("delete")) return false;

        if (user.getRole().toString().equals("CLIENT") && command.equals("cancel")) return false;

        if (user.getRole().toString().equals("CLIENT") && command.equals("create")) return false;

        if (user.getRole().toString().equals("CLIENT") && command.equals("update")) return false;

        return true;
    }

    /**
     * Secures the list of orders by filtering them based on the user's role.
     * If the user is a client, only the orders associated with the user are returned.
     *
     * @param user the user requesting the orders
     * @param orders the list of orders to be filtered
     * @return the filtered list of orders
     */
    public List<ShowOrderResponse> secureOrder(User user, List<ShowOrderResponse> orders) {
        if (user.getRole().toString().equals("CLIENT")) {
            return orders.stream().filter(it -> it.getUserId().equals(user.getId())).toList();
        }

        return orders;
    }
}
