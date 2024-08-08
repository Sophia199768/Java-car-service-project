package org.example.controller;

import org.example.application.controller.CarController;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.Exception.Exceptions;
import org.example.service.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should successfully update car when all details are correct")
    void updateCar_shouldBeSuccessful_whenAllDetailsAreCorrect() throws Exceptions {
        UpdateCarRequest request = new UpdateCarRequest();
        carController.update(request);

        ArgumentCaptor<UpdateCarRequest> captor = ArgumentCaptor.forClass(UpdateCarRequest.class);
        verify(carService).updateCar(captor.capture());
        assertEquals(request, captor.getValue());
    }

    @Test
    @DisplayName("Should successfully create a new car when all details are correct")
    void createNewCar_shouldBeSuccessful_whenAllDetailsAreCorrect() {
        CreateCarRequest createCarRequest = new CreateCarRequest();
        carController.createNewCar(createCarRequest);

        ArgumentCaptor<CreateCarRequest> captor = ArgumentCaptor.forClass(CreateCarRequest.class);
        verify(carService).createCar(captor.capture());
        assertEquals(createCarRequest, captor.getValue());
    }

    @Test
    @DisplayName("Should return all cars when readCars is called")
    void readCars_shouldReturnAllCars_whenCalled() {
        List<ShowCarResponse> expectedCars = List.of(new ShowCarResponse());
        when(carService.read()).thenReturn(expectedCars);

        List<ShowCarResponse> actualCars = carController.readCars();
        assertEquals(expectedCars, actualCars);
        verify(carService).read();
    }

    @Test
    @DisplayName("Should return filtered cars when getFilterCars is called with valid filter")
    void getFilterCars_shouldReturnFilteredCars_whenFilterIsValid() {
        FilterCarRequest request = new FilterCarRequest();
        List<ShowCarResponse> expectedCars = List.of(new ShowCarResponse());
        when(carService.filter(request)).thenReturn(expectedCars);

        List<ShowCarResponse> actualCars = carController.getFilterCars(request);
        assertEquals(expectedCars, actualCars);

        verify(carService).filter(request);
    }
}
