package org.example.service;

import org.example.core.model.car.Car;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.core.responsesAndRequestes.car.UpdateCarRequest;
import org.example.service.exception.Exceptions;
import org.example.service.mapper.CarMapper;
import org.example.service.repository.CarRepository;
import org.example.service.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createCar should create car when request is valid")
    void createCar_shouldCreateCar_whenRequestIsValid() throws Exceptions {
        CreateCarRequest createCarRequest = new CreateCarRequest();
        Car car = new Car();

        carService.createCar(createCarRequest);

        verify(carRepository, times(1)).create(car);
    }

    @Test
    @DisplayName("updateCar should update car when car exists")
    void updateCar_shouldUpdateCar_whenCarExists() throws Exceptions {
        UpdateCarRequest request = new UpdateCarRequest();
        request.setId(1);
        Car car = new Car();
        car.setId(1);

        when(carRepository.read()).thenReturn(List.of(car));

        carService.updateCar(request);

        verify(carRepository, times(1)).update(car);
    }

    @Test
    @DisplayName("deleteCar should delete car when car exists")
    void deleteCar_shouldDeleteCar_whenCarExists() throws Exceptions {
        Car car = new Car();
        car.setId(1);
        when(carRepository.read()).thenReturn(List.of(car));
        doNothing().when(carRepository).delete(car);

        carService.deleteCar(1);

        verify(carRepository, times(1)).delete(car);
    }

    @Test
    @DisplayName("read should return list of cars")
    void read_shouldReturnListOfCars() throws Exceptions {
        Car car = new Car();
        ShowCarResponse showCarResponse = new ShowCarResponse();
        when(carRepository.read()).thenReturn(Collections.singletonList(car));

        List<ShowCarResponse> showCarResponses = carService.read();

        assertEquals(1, showCarResponses.size());
        assertEquals(showCarResponse, showCarResponses.get(0));
        verify(carRepository, times(1)).read();
    }

    @Test
    @DisplayName("filter should return list of cars based on filter criteria")
    void filter_shouldReturnCarsBasedOnCriteria() throws Exceptions {
        Car car = new Car();
        car.setId(1);
        car.setCarBrand("Toyota");
        car.setCarModel("Camry");
        car.setPrice(30000L);
        car.setCondition("New");

        FilterCarRequest request = new FilterCarRequest();
        request.setCarBrand("Toyota");
        request.setCarModel("Camry");
        request.setCondition("New");
        request.setPrice(30000L);

        ShowCarResponse showCarResponse = CarMapper.INSTANCE.toCarResponse(car);

        when(carRepository.read()).thenReturn(List.of(car));

        List<ShowCarResponse> showCarResponses = carService.filter(request);

        assertEquals(1, showCarResponses.size());
        assertEquals(showCarResponse, showCarResponses.get(0));
        verify(carRepository, times(1)).read();
    }
}
