package org.example.application.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.annotations.Loggable;
import org.example.core.responsesAndRequestes.user.*;
import org.example.service.Exception.Exceptions;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;
import org.example.service.auth.AuthManager;
import org.example.service.repository.UserRepository;
import org.example.service.service.UserService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling HTTP requests related to user management.
 * <p>
 * This servlet handles various user-related operations including reading,
 * creating, updating, and deleting users. The operations are available
 * through HTTP GET, POST, PUT, and DELETE requests respectively.
 * </p>
 */
@Loggable
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final UserService userService;

    /**
     * Default constructor initializes {@link ObjectMapper} and {@link UserService}.
     * Configures ObjectMapper to not fail on empty beans.
     */
    public UserServlet() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.userService = new UserService(new UserRepository());
    }

    /**
     * Constructor that allows injecting a custom {@link UserService}.
     *
     * @param userService the {@link UserService} to be used by this servlet
     */
    public UserServlet(UserService userService) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.userService = userService;
    }

    /**
     * Handles HTTP GET requests to retrieve a list of users.
     * <p>
     * The response contains a JSON list of {@link ShowUserResponse} objects.
     * Authorization and permission checks are performed before processing the request.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("GET /user");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            List<ShowUserResponse> users = userService.read();

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            byte[] bytes = objectMapper.writeValueAsBytes(users);
            resp.getOutputStream().write(bytes);

        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles HTTP POST requests to create a new user.
     * <p>
     * The request body should contain a JSON representation of {@link CreateUserRequest}.
     * Authorization and permission checks are performed before processing the request.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("POST /user");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            CreateUserRequest createUserRequest = objectMapper.readValue(req.getInputStream(), CreateUserRequest.class);
            userService.createUser(createUserRequest);
        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles HTTP PUT requests to update an existing user.
     * <p>
     * The request body should contain a JSON representation of {@link UpdateUserRequest}.
     * Authorization and permission checks are performed before processing the request.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("PUT /user");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            UpdateUserRequest updateUserRequest = objectMapper.readValue(req.getInputStream(), UpdateUserRequest.class);
            userService.updateUser(updateUserRequest);

            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles HTTP DELETE requests to delete an existing user.
     * <p>
     * The request should include the user ID as a query parameter.
     * Authorization and permission checks are performed before processing the request.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("DELETE /user");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            Integer userId = Integer.valueOf(req.getParameter("id"));
            userService.deleteUser(userId);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exceptions e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
