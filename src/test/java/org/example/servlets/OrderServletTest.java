package org.example.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.application.servlets.OrderServlet;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class OrderServletTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderServlet orderServlet;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should successfully create a new order when all details are correct")
    void createNewOrder_shouldBeSuccessful_whenAllDetailsAreCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();

        String json = objectMapper.writeValueAsString(createOrderRequest);
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        ServletInputStream servletInputStream = mock(ServletInputStream.class);
        when(servletInputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            int offset = invocation.getArgument(1);
            int length = invocation.getArgument(2);
            int bytesRead = inputStream.read(buffer, offset, length);
            return bytesRead;
        });

        when(request.getInputStream()).thenReturn(servletInputStream);

        orderServlet.doPost(request, response);

        verify(orderService).createOrder(createOrderRequest);

        verify(response).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    @DisplayName("Should successfully retrieve a list of orders")
    void retrieveOrders_shouldBeSuccessful() throws ServletException, IOException, Exceptions {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        List<ShowOrderResponse> orders = Arrays.asList(new ShowOrderResponse(), new ShowOrderResponse());
        when(orderService.read()).thenReturn(orders);
        when(response.getWriter()).thenReturn(writer);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ServletOutputStream servletOutputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() { return true; }

            @Override
            public void setWriteListener(WriteListener writeListener) {}

            @Override
            public void write(int b) throws IOException {
                outputStream.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                outputStream.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                outputStream.write(b, off, len);
            }
        };

        when(response.getOutputStream()).thenReturn(servletOutputStream);

        orderServlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).setContentType("application/json");
        String expectedJson = objectMapper.writeValueAsString(orders);
        assertEquals(expectedJson, outputStream.toString());
    }


    @Test
    @DisplayName("Should successfully update a order when all details are correct")
    void updateOrder_shouldBeSuccessful_whenAllDetailsAreCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest();

        String json = objectMapper.writeValueAsString(updateOrderRequest);
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        ServletInputStream servletInputStream = mock(ServletInputStream.class);
        when(servletInputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            int offset = invocation.getArgument(1);
            int length = invocation.getArgument(2);
            int bytesRead = inputStream.read(buffer, offset, length);
            return bytesRead;
        });

        when(request.getInputStream()).thenReturn(servletInputStream);

        orderServlet.doPut(request, response);

        verify(orderService).updateOrder(updateOrderRequest);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Should return bad request status when an exception is thrown during update")
    void updateOrder_shouldReturnBadRequest_whenExceptionIsThrown() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest();

        String json = objectMapper.writeValueAsString(updateOrderRequest);
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        ServletInputStream servletInputStream = mock(ServletInputStream.class);
        when(servletInputStream.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            int offset = invocation.getArgument(1);
            int length = invocation.getArgument(2);
            int bytesRead = inputStream.read(buffer, offset, length);
            return bytesRead;
        });

        when(request.getInputStream()).thenReturn(servletInputStream);

        doThrow(new Exceptions("Error")).when(orderService).updateOrder(updateOrderRequest);

        orderServlet.doPut(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Error");
    }

    @Test
    @DisplayName("Should successfully delete a order when the ID is correct")
    void deleteOrder_shouldBeSuccessful_whenIdIsCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        orderServlet.doDelete(request, response);

        verify(orderService).cancelOrder(1);

        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
