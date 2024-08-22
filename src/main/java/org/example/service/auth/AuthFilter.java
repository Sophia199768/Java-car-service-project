package org.example.service.auth;

import org.example.annotations.Auditable;
import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;

/**
 * Class for performing authorization checks based on user roles and request paths.
 * <p>
 * This class contains methods to check if a user has the necessary permissions
 * to perform actions on specific paths. The authorization logic is based on the
 * user's role and the HTTP method associated with the request path.
 * </p>
 */
@Auditable
public class AuthFilter {

    /**
     * Checks if the given user has permission to access a specific path.
     * <p>
     * The permission checks are based on the user's role and the HTTP method of the path.
     * </p>
     *
     * @param user the user whose permissions are to be checked
     * @param path the request path to be accessed
     * @throws UnauthorizedException if the user is null
     * @throws PermissionDeniedException if the user does not have permission to access the specified path
     */
    public void checkPermission(User user, String path) throws UnauthorizedException, PermissionDeniedException {

        if (user == null) throw new UnauthorizedException("Unauthorized");

        if (user.getRole().equals(Role.CLIENT) &&
                (path.startsWith("POST") || path.startsWith("DELETE") || path.startsWith("PUT")))
            throw new PermissionDeniedException("Permission denied");

        if (user.getRole().equals(Role.MANAGER) && path.startsWith("DELETE"))
            throw new PermissionDeniedException("Permission denied");
    }
}
