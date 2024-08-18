package org.example.application.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.annotations.Loggable;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;
import org.example.service.auth.AuthManager;
import org.example.service.repository.CarRepository;
import org.example.service.service.CarService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling HTTP requests related to cars. This servlet supports
 * CRUD operations for cars, including creating, reading, updating, and deleting car records.
 * <p>
 * The servlet uses {@link CarService} to perform business logic and interacts with the client via JSON.
 * Authentication and permission checks are handled through {@link AuthManager}.
 * </p>
 */
@Loggable
@WebServlet("/car")
public class CarServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final CarService carService;

    /**
     * Default constructor initializes {@link ObjectMapper} and {@link CarService}.
     * Configures ObjectMapper to not fail on empty beans.
     */
    public CarServlet() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.carService = new CarService(new CarRepository());
    }

    /**
     * Constructor that allows injecting a custom {@link CarService}.
     *
     * @param carService the {@link CarService} instance to use
     */
    public CarServlet(CarService carService) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.carService = carService;
    }

    /**
     * Handles GET requests to retrieve all cars.
     * <p>
     * Checks for user authorization and permission before fetching the car data.
     * Responds with a JSON list of {@link ShowCarResponse} objects representing the cars.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("GET /car");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            List<ShowCarResponse> cars = carService.read();

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            byte[] bytes = objectMapper.writeValueAsBytes(cars);
            resp.getOutputStream().write(bytes);

        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles POST requests to create a new car.
     * <p>
     * Checks for user authorization and permission before processing the creation of the car.
     * The request body should contain a JSON representation of {@link CreateCarRequest}.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the POST request
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("POST /car");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            CreateCarRequest createCarRequest = objectMapper.readValue(req.getInputStream(), CreateCarRequest.class);
            carService.createCar(createCarRequest);

            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles PUT requests to update an existing car.
     * <p>
     * Checks for user authorization and permission before processing the update of the car.
     * The request body should contain a JSON representation of {@link UpdateCarRequest}.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the PUT could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the PUT request
     */
    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("PUT /car");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            UpdateCarRequest updateCarRequest = objectMapper.readValue(req.getInputStream(), UpdateCarRequest.class);
            carService.updateCar(updateCarRequest);

            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles DELETE requests to delete an existing car.
     * <p>
     * Checks for user authorization and permission before processing the deletion of the car.
     * The car ID should be provided as a query parameter.
     * </p>
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the DELETE could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the DELETE request
     */
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String token = req.getHeader("Authorization");
            if (token == null) throw new UnauthorizedException("Unauthorized");
            token = token.substring(7);
            AuthManager.getInstance().logIn(token);
            AuthManager.getInstance().checkPermission("DELETE /car");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            Integer carId = Integer.valueOf(req.getParameter("id"));
            carService.deleteCar(carId);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exceptions e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
