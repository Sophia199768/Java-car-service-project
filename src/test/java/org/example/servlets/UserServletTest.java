package org.example.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.application.servlets.UserServlet;
import org.example.core.model.user.Role;
import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.order.UpdateOrderRequest;
import org.example.core.responsesAndRequestes.user.CreateUserRequest;
import org.example.core.responsesAndRequestes.user.LogInRequest;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;
import org.example.core.responsesAndRequestes.user.UpdateUserRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.UserService;
import org.example.service.validation.UserValidation;
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

class UserServletTest {

    @Mock
    private UserService userService;
    @Mock
    private UserValidation userValidation;

    @InjectMocks
    private UserServlet userServlet;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }


    @Test
    @DisplayName("Should successfully retrieve a list of users")
    void retrieveUser_shouldBeSuccessful() throws ServletException, IOException, Exceptions {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        List<ShowUserResponse> users = Arrays.asList(new ShowUserResponse(), new ShowUserResponse());
        when(userService.read()).thenReturn(users);
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

        userServlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).setContentType("application/json");
        String expectedJson = objectMapper.writeValueAsString(users);
        assertEquals(expectedJson, outputStream.toString());
    }

    @Test
    @DisplayName("Should successfully update a user when all details are correct")
    void updateUser_shouldBeSuccessful_whenAllDetailsAreCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateUserRequest updateUserRequest = new  UpdateUserRequest();

        String json = objectMapper.writeValueAsString(updateUserRequest);
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

        userServlet.doPut(request, response);

        verify(userService).updateUser(updateUserRequest);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Should return bad request status when an exception is thrown during update")
    void updateUser_shouldReturnBadRequest_whenExceptionIsThrown() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateUserRequest updateUserRequest = new  UpdateUserRequest();

        String json = objectMapper.writeValueAsString(updateUserRequest);
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

        doThrow(new Exceptions("Error")).when(userService).updateUser(updateUserRequest);

        userServlet.doPut(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Error");
    }

    @Test
    @DisplayName("Should successfully delete an user when the ID is correct")
    void deleteUser_shouldBeSuccessful_whenIdIsCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        userServlet.doDelete(request, response);

        verify(userService).deleteUser(1);

        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
