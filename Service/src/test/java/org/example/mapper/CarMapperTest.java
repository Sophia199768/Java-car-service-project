package org.example.mapper;

import org.example.model.car.Car;
import org.example.responsesAndRequestes.car.CreateCarRequest;
import org.example.responsesAndRequestes.car.ShowCarResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    void toCarResponse() {
        CarMapper mapper = new CarMapper();
        Car newCar = new Car("NewBrand", "NewModel", new Date(1), "New", 20000L);
        newCar.setId(1);

        ShowCarResponse response = new ShowCarResponse(1, "NewBrand", "NewModel",  new Date(1), "New", 20000L);
        ShowCarResponse showCarResponse = mapper.toCarResponse(newCar);

        assertEquals(showCarResponse, response);
    }

    @Test
    void toCar() {
        CarMapper mapper = new CarMapper();
        Car newCar = new Car("NewBrand", "NewModel", new Date(1), "New", 20000L);
        newCar.setId(1);

        CreateCarRequest request = new CreateCarRequest("NewBrand", "NewModel",  new Date(1), "New", 20000L);
        Car car = mapper.toCar(request);
        car.setId(1);

        assertEquals(newCar, car);
    }
}