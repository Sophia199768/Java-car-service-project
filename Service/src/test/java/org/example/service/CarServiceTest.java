package org.example.service;

import org.example.Exception.Exceptions;
import org.example.mapper.CarMapper;
import org.example.model.car.Car;
import org.example.repository.*;
import org.example.responsesAndRequestes.car.CreateCarRequest;
import org.example.responsesAndRequestes.car.FilterCarRequest;
import org.example.responsesAndRequestes.car.ShowCarResponse;
import org.example.responsesAndRequestes.car.UpdateCarRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCar() {
        CreateCarRequest createCarRequest = new CreateCarRequest();
        Car car = new Car();
        when(carMapper.toCar(createCarRequest)).thenReturn(car);
        doNothing().when(carRepository).create(car);

        carService.createCar(createCarRequest);

        verify(carRepository, times(1)).create(car);
    }

    @Test
    void changeUser() throws Exceptions {
        UpdateCarRequest request = new UpdateCarRequest();
        request.setId(1);
        Car car = new Car();
        car.setId(1);

        when(carRepository.read()).thenReturn(List.of(car));
        doNothing().when(carRepository).update(car);

        carService.updateCar(request);

        verify(carRepository, times(1)).update(car);
    }


    @Test
    void deleteCar() throws Exceptions {
        CreateCarRequest createCarRequest = new CreateCarRequest();
        Car car = new Car();
        car.setId(1);
        when(carRepository.read()).thenReturn(List.of(car));
        when(carMapper.toCar(createCarRequest)).thenReturn(car);
        doNothing().when(carRepository).delete(car);

        carService.deleteCar(1);

        verify(carRepository, times(1)).delete(car);
    }

    @Test
    void read() {
        Car car = new Car();
        ShowCarResponse showCarResponse = new ShowCarResponse();
        when(carRepository.read()).thenReturn(Collections.singletonList(car));
        when(carMapper.toCarResponse(car)).thenReturn(showCarResponse);

        List<ShowCarResponse> showCarRespons = carService.read();

        assertEquals(1, showCarRespons.size());
        assertEquals(showCarResponse, showCarRespons.get(0));
        verify(carRepository, times(1)).read();
    }

    @Test
    void filter() {
        Car car = new Car("Toyota", "Camry", null,"New", 30000L);
        ShowCarResponse showCarResponse = new ShowCarResponse();
        when(carRepository.read()).thenReturn(List.of(car));
        when(carMapper.toCarResponse(car)).thenReturn(showCarResponse);

        List<ShowCarResponse> showCarRespons = carService.filter(new FilterCarRequest("Toyota", "Camry", null, "New", 30000L));

        assertEquals(1, showCarRespons.size());
        verify(carRepository, times(1)).read();
    }
}
