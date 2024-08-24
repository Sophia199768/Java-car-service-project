package org.example.controllers;

import org.example.application.controllers.CarController;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    public CarControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create car - should create a car successfully when valid request is provided")
    void createCar() throws Exceptions {
        CreateCarRequest request = new CreateCarRequest();
        // Call the method
        carController.createCar(request);
        // Verify the service method was called
        verify(carService).createCar(request);
    }

    @Test
    @DisplayName("Update car condition - should update car condition successfully when valid request is provided")
    void updateCarCondition() throws Exceptions {
        UpdateCarRequest request = new UpdateCarRequest();
        // Call the method
        carController.updateCar(request);
        // Verify the service method was called
        verify(carService).updateCar(request);
    }

    @Test
    @DisplayName("Delete car - should delete the car successfully when valid id is provided")
    void deleteCar() throws Exceptions {
        Integer id = 1;
        // Call the method
        carController.deleteCar(id);
        // Verify the service method was called
        verify(carService).deleteCar(id);
    }

    @Test
    @DisplayName("Filter cars - should return a list of cars when valid filter request is provided")
    void filterCars() throws Exceptions {
        FilterCarRequest request = new FilterCarRequest();
        List<ShowCarResponse> expectedResponse = List.of(new ShowCarResponse());
        when(carService.filter(any(FilterCarRequest.class))).thenReturn(expectedResponse);
        // Call the method
        List<ShowCarResponse> response = carController.filterCars(request).getBody();
        // Assert the response
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Read cars - should return a list of all cars")
    void read() throws Exceptions {
        List<ShowCarResponse> expectedResponse = List.of(new ShowCarResponse());
        when(carService.read()).thenReturn(expectedResponse);
        // Call the method
        List<ShowCarResponse> response = carController.read().getBody();
        // Assert the response
        assertEquals(expectedResponse, response);
    }
}
