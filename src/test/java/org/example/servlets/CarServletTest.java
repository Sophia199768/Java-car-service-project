package org.example.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.application.servlets.CarServlet;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.CarService;
import org.example.service.validation.CarValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CarServletTest {

    @Mock
    private CarService carService;

    @Mock
    private CarValidation carValidation;

    @InjectMocks
    private CarServlet carServlet;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should successfully create a new car when all details are correct")
    void createNewCar_shouldBeSuccessful_whenAllDetailsAreCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CreateCarRequest createCarRequest = new CreateCarRequest();

        String json = objectMapper.writeValueAsString(createCarRequest);
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
        when(carValidation.valid(createCarRequest)).thenReturn(true);

        carServlet.doPost(request, response);

        verify(carService).createCar(createCarRequest);

        verify(response).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    @DisplayName("Should successfully retrieve a list of cars")
    void retrieveCars_shouldBeSuccessful() throws ServletException, IOException, Exceptions {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        List<ShowCarResponse> cars = Arrays.asList(new ShowCarResponse(), new ShowCarResponse());
        when(carService.read()).thenReturn(cars);
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

        carServlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).setContentType("application/json");
        String expectedJson = objectMapper.writeValueAsString(cars);
        assertEquals(expectedJson, outputStream.toString());
    }

    @Test
    @DisplayName("Should successfully update a car when all details are correct")
    void updateCar_shouldBeSuccessful_whenAllDetailsAreCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateCarRequest updateCarRequest = new UpdateCarRequest();

        String json = objectMapper.writeValueAsString(updateCarRequest);
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

        carServlet.doPut(request, response);

        verify(carService).updateCar(updateCarRequest);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    @DisplayName("Should return bad request status when an exception is thrown during update")
    void updateCar_shouldReturnBadRequest_whenExceptionIsThrown() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        UpdateCarRequest updateCarRequest = new UpdateCarRequest();

        String json = objectMapper.writeValueAsString(updateCarRequest);
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

        doThrow(new Exceptions("Error")).when(carService).updateCar(updateCarRequest);

        carServlet.doPut(request, response);

        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Error");
    }


    @Test
    @DisplayName("Should successfully delete a car when the ID is correct")
    void deleteCar_shouldBeSuccessful_whenIdIsCorrect() throws Exceptions, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        carServlet.doDelete(request, response);

        verify(carService).deleteCar(1);

        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
