package org.example.application.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.responsesAndRequestes.order.FilterOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.service.Exception.Exceptions;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;
import org.example.service.auth.AuthManager;
import org.example.service.repository.OrderRepository;
import org.example.service.service.OrderService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling HTTP POST requests to filter orders based on specific criteria.
 * <p>
 * This servlet processes POST requests to the "/order/filter" endpoint, allowing clients to
 * filter orders using various criteria specified in the {@link FilterOrderRequest}.
 * </p>
 */
@WebServlet("/order/filter")
public class OrderServletFilter extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    /**
     * Default constructor initializes {@link ObjectMapper} and {@link OrderService}.
     * Configures ObjectMapper to not fail on empty beans.
     */
    public OrderServletFilter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.orderService = new OrderService(new OrderRepository());
    }

    /**
     * Handles POST requests to filter orders based on specific criteria.
     * <p>
     * The request body should contain a JSON representation of {@link FilterOrderRequest}.
     * The response will contain a JSON list of {@link ShowOrderResponse} objects representing
     * the filtered orders. Authorization and permission checks are performed before processing the request.
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
            AuthManager.getInstance().checkPermission("POST /order/filter");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            FilterOrderRequest filterOrderRequest = objectMapper.readValue(req.getInputStream(), FilterOrderRequest.class);
            List<ShowOrderResponse> filteredOrders = orderService.filter(filterOrderRequest);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            byte[] bytes = objectMapper.writeValueAsBytes(filteredOrders);
            resp.getOutputStream().write(bytes);

        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
