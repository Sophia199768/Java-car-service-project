package org.example.controller;

import org.example.Exception.Exceptions;
import org.example.responsesAndRequestes.car.*;
import org.example.service.CarService;
import org.junit.jupiter.api.BeforeEach;
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
    void update() throws Exceptions {
        UpdateCarRequest request = new UpdateCarRequest();
        carController.update(request);

        ArgumentCaptor<UpdateCarRequest> captor = ArgumentCaptor.forClass(UpdateCarRequest.class);
        verify(carService).updateCar(captor.capture());
        assertEquals(request, captor.getValue());
    }


    @Test
    void createNewCar() {
        CreateCarRequest createCarRequest = new CreateCarRequest();
        carController.createNewCar(createCarRequest);

        ArgumentCaptor<CreateCarRequest> captor = ArgumentCaptor.forClass(CreateCarRequest.class);
        verify(carService).createCar(captor.capture());
        assertEquals(createCarRequest, captor.getValue());
    }

    @Test
    void readCars() {
        List<ShowCarResponse> expectedCars = List.of(new ShowCarResponse());
        when(carService.read()).thenReturn(expectedCars);

        List<ShowCarResponse> actualCars = carController.readCars();
        assertEquals(expectedCars, actualCars);
        verify(carService).read();
    }

    @Test
    void getFilterCars() {
        FilterCarRequest request = new FilterCarRequest();
        List<ShowCarResponse> expectedCars = List.of(new ShowCarResponse());
        when(carService.filter(request)).thenReturn(expectedCars);

        List<ShowCarResponse> actualCars = carController.getFilterCars(request);
        assertEquals(expectedCars, actualCars);

        verify(carService).filter(request);
    }
}
