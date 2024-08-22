package org.example.application.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.service.Exception.Exceptions;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;
import org.example.service.auth.AuthManager;
import org.example.service.repository.CarRepository;
import org.example.service.service.CarService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling HTTP requests to filter cars based on specific criteria.
 * <p>
 * This servlet processes POST requests to filter cars according to the parameters provided in
 * the {@link FilterCarRequest} object. The response contains a list of {@link ShowCarResponse}
 * objects representing the filtered cars.
 * </p>
 * <p>
 * The servlet utilizes {@link CarService} for business logic and performs authentication and permission checks
 * through {@link AuthManager}.
 * </p>
 */
@WebServlet("/car/filter")
public class CarServletFilter extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final CarService carService;

    /**
     * Default constructor initializes {@link ObjectMapper} and {@link CarService}.
     * Configures ObjectMapper to not fail on empty beans.
     */
    public CarServletFilter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.carService = new CarService(new CarRepository());
    }

    /**
     * Handles POST requests to filter cars.
     * <p>
     * The request body should contain a JSON representation of {@link FilterCarRequest}.
     * The response will be a JSON list of {@link ShowCarResponse} objects that match the filter criteria.
     * </p>
     * <p>
     * Authorization and permission checks are performed before processing the request.
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
            AuthManager.getInstance().checkPermission("POST /car/filter");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            FilterCarRequest filterCarRequest = objectMapper.readValue(req.getInputStream(), FilterCarRequest.class);
            List<ShowCarResponse> filteredCars = carService.filter(filterCarRequest);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            byte[] bytes = objectMapper.writeValueAsBytes(filteredCars);
            resp.getOutputStream().write(bytes);

        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
