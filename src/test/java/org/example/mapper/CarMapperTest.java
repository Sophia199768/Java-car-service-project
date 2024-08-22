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
        Car newCar = new Car();
        newCar.setId(1);
        newCar.setCarBrand("NewBrand");
        newCar.setCarModel("NewModel");
        newCar.setReleaseYear(new Date(1));
        newCar.setPrice(20000L);
        newCar.setCondition("New");

        ShowCarResponse expectedResponse = new ShowCarResponse();
        expectedResponse.setId(1);
        expectedResponse.setCarBrand("NewBrand");
        expectedResponse.setCarModel("NewModel");
        expectedResponse.setReleaseYear(new Date(1));
        expectedResponse.setCondition("New");
        expectedResponse.setPrice(20000L);

        ShowCarResponse actualResponse = CarMapper.INSTANCE.toCarResponse(newCar);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Should correctly map CreateCarRequest to Car")
    void toCar_shouldReturnCorrectCar_whenCreateCarRequestIsProvided() {
        Car expectedCar = new Car();
        expectedCar.setId(1);
        expectedCar.setCarBrand("NewBrand");
        expectedCar.setCarModel("NewModel");
        expectedCar.setReleaseYear(new Date(1));
        expectedCar.setPrice(20000L);
        expectedCar.setCondition("New");

        CreateCarRequest request = new CreateCarRequest();
        request.setCarBrand("NewBrand");
        request.setCarModel("NewModel");
        request.setReleaseYear(new Date(1));
        request.setCondition("New");
        request.setPrice(20000L);

        Car actualCar = CarMapper.INSTANCE.toCar(request);
        actualCar.setId(1);

        assertEquals(expectedCar, actualCar);
    }
}
