package org.example.mapper;

import org.example.core.model.car.Car;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.example.service.mapper.CarMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    @DisplayName("Should correctly map Car to ShowCarResponse")
    void toCarResponse_shouldReturnCorrectShowCarResponse_whenCarIsProvided() {
        CarMapper mapper = new CarMapper();
        Car newCar = Car.builder()
                .id(1)
                .carBrand("NewBrand")
                .carModel("NewModel")
                .releaseYear(new Date(1))
                .price(20000L)
                .condition("New")
                .build();

        ShowCarResponse expectedResponse = new ShowCarResponse(1, "NewBrand", "NewModel", new Date(1), "New", 20000L);
        ShowCarResponse actualResponse = mapper.toCarResponse(newCar);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should correctly map CreateCarRequest to Car")
    void toCar_shouldReturnCorrectCar_whenCreateCarRequestIsProvided() {
        CarMapper mapper = new CarMapper();
        Car expectedCar = Car.builder()
                .id(1)
                .carBrand("NewBrand")
                .carModel("NewModel")
                .releaseYear(new Date(1))
                .price(20000L)
                .condition("New")
                .build();

        CreateCarRequest request = new CreateCarRequest("NewBrand", "NewModel", new Date(1), "New", 20000L);
        Car actualCar = mapper.toCar(request);
        actualCar.setId(1);

        assertEquals(expectedCar, actualCar);
    }
}
