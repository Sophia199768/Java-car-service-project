package org.example.service.mapper;

import org.example.core.model.car.Car;
import org.example.core.responsesAndRequestes.car.CreateCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between {@link Car} entities and their corresponding DTOs.
 * <p>
 * This interface uses MapStruct to generate implementations for mapping between:
 * <ul>
 *     <li>{@link Car} and {@link ShowCarResponse}</li>
 *     <li>{@link CreateCarRequest} and {@link Car}</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface provides a singleton instance via {@link #INSTANCE}.
 * </p>
 */
@Mapper
public interface CarMapper {
    /**
     * Singleton instance of the {@link CarMapper}.
     */
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    /**
     * Converts a {@link Car} entity to a {@link ShowCarResponse} DTO.
     *
     * @param car the {@link Car} entity to be converted
     * @return the corresponding {@link ShowCarResponse} DTO
     */
    ShowCarResponse toCarResponse(Car car);

    /**
     * Converts a {@link CreateCarRequest} DTO to a {@link Car} entity.
     *
     * @param carRequest the {@link CreateCarRequest} DTO to be converted
     * @return the corresponding {@link Car} entity
     */
    Car toCar(CreateCarRequest carRequest);


}
