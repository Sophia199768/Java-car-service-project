package org.example.mapper;

import org.example.model.car.Car;
import org.example.responsesAndRequestes.car.CreateCarRequest;
import org.example.responsesAndRequestes.car.ShowCarResponse;

/**
 * The CarMapper class is responsible for mapping between Car entities and their corresponding request and response objects
 */
public class CarMapper {

    /**
     * Converts a Car entity to a CarResponse object
     *
     * @param car The Car entity to be converted
     * @return A CarResponse object containing the data from the Car entity
     */
    public ShowCarResponse toCarResponse(Car car) {
        return new ShowCarResponse(car.getId(), car.getCarBrand(), car.getCarModel(), car.getReleaseYear(), car.getCondition(), car.getPrice());
    }

    /**
     * Converts a CarRequest object to a Car entity
     *
     * @param createCarRequest The CarRequest object to be converted
     * @return A Car entity containing the data from the CarRequest object
     */
    public Car toCar(CreateCarRequest createCarRequest) {
        return new Car(createCarRequest.getCarBrand(),
                createCarRequest.getCarModel(),
                createCarRequest.getReleaseYear(),
                createCarRequest.getCondition(),
                createCarRequest.getPrice());
    }
}
