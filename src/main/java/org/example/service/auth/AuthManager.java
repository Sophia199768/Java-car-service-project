package org.example.service.auth;

import org.example.core.responsesAndRequestes.user.LogInRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;
import org.example.service.repository.UserRepository;
import org.example.service.service.UserService;

import java.util.Base64;

/**
 * Singleton class responsible for managing authentication and authorization.
 * <p>
 * The class provides methods for user login and permission checks. It utilizes
 * the {@link UserService} for user authentication and {@link AuthFilter} for
 * permission checks based on user roles and request paths.
 * </p>
 */
public class AuthManager {
    private static AuthManager INSTANCE;
    private final UserService service;
    private final AuthFilter filter;

    /**
     * Private constructor for singleton pattern. Initializes the {@link UserService}
     * and {@link AuthFilter} instances.
     */
    private AuthManager() {
        this.service = new UserService(new UserRepository());
        this.filter = new AuthFilter();
    }

    /**
     * Logs in a user by decoding the provided token and authenticating the user.
     * <p>
     * The token is expected to be in Base64-encoded format, with the format
     * "login:password". The credentials are extracted and used to create a
     * {@link LogInRequest}, which is then used to authenticate the user. On successful
     * authentication, the user is set in the {@link AuthContext}.
     * </p>
     *
     * @param token the Base64-encoded token containing the login and password
     * @throws UnauthorizedException if the authentication fails
     */
    public void logIn(String token) throws UnauthorizedException {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedToken = new String(decodedBytes);
        String[] credentials = decodedToken.split(":");

        LogInRequest request = new LogInRequest();
        request.setLogin(credentials[0]);
        request.setPassword(credentials[1]);

        try {
            AuthContext.getInstance().setUser(service.logIn(request));
        } catch (Exceptions e) {
            throw new UnauthorizedException("Unauthorized");
        }
    }

    /**
     * Checks if the current user has permission to access a specified path.
     * <p>
     * The permission is checked using the {@link AuthFilter} based on the user's role
     * and the request path.
     * </p>
     *
     * @param path the request path to be accessed
     * @throws UnauthorizedException if the user is not logged in
     * @throws PermissionDeniedException if the user does not have permission to access the specified path
     */
    public void checkPermission(String path) throws UnauthorizedException, PermissionDeniedException {
        filter.checkPermission(AuthContext.getInstance().getUser(), path);
    }

    /**
     * Gets the singleton instance of {@link AuthManager}.
     *
     * @return the singleton instance of {@link AuthManager}
     */
    public static AuthManager getInstance() {
        if (INSTANCE == null) INSTANCE = new AuthManager();
        return INSTANCE;
    }
}
