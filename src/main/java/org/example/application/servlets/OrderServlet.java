package org.example.application.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.Exception.PermissionDeniedException;
import org.example.service.Exception.UnauthorizedException;
import org.example.service.auth.AuthManager;
import org.example.service.repository.OrderRepository;
import org.example.service.service.OrderService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling HTTP requests related to orders.
 * <p>
 * This servlet processes various HTTP methods such as GET, POST, PUT, and DELETE
 * for managing orders. The servlet uses {@link OrderService} to interact with order data
 * and performs authentication and permission checks using {@link AuthManager}.
 * </p>
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    /**
     * Default constructor initializes {@link ObjectMapper} and {@link OrderService}.
     * Configures ObjectMapper to not fail on empty beans.
     */
    public OrderServlet() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.orderService = new OrderService(new OrderRepository());
    }

    /**
     * Constructor with dependency injection for {@link OrderService}.
     *
     * @param orderService the service layer used to manage orders
     */
    public OrderServlet(OrderService orderService) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.orderService = orderService;
    }

    /**
     * Handles GET requests to retrieve a list of orders.
     * <p>
     * The response will contain a JSON list of {@link ShowOrderResponse} objects representing all orders.
     * Authorization and permission checks are performed before processing the request.
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
            AuthManager.getInstance().checkPermission("GET /order");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            List<ShowOrderResponse> orders = orderService.read();

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            byte[] bytes = objectMapper.writeValueAsBytes(orders);
            resp.getOutputStream().write(bytes);

        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles POST requests to create a new order.
     * <p>
     * The request body should contain a JSON representation of {@link CreateOrderRequest}.
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
            AuthManager.getInstance().checkPermission("POST /order");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            CreateOrderRequest createOrderRequest = objectMapper.readValue(req.getInputStream(), CreateOrderRequest.class);
            orderService.createOrder(createOrderRequest);

            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles PUT requests to update an existing order.
     * <p>
     * The request body should contain a JSON representation of {@link UpdateOrderRequest}.
     * Authorization and permission checks are performed before processing the request.
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
            AuthManager.getInstance().checkPermission("PUT /order");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            UpdateOrderRequest updateOrderRequest = objectMapper.readValue(req.getInputStream(), UpdateOrderRequest.class);
            orderService.updateOrder(updateOrderRequest);

            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exceptions | IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles DELETE requests to cancel an existing order.
     * <p>
     * The order ID should be provided as a request parameter. Authorization and permission checks are
     * performed before processing the request.
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
            AuthManager.getInstance().checkPermission("DELETE /order");
        } catch (UnauthorizedException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (PermissionDeniedException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }

        try {
            Integer orderId = Integer.valueOf(req.getParameter("id"));
            orderService.cancelOrder(orderId);

            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exceptions e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
