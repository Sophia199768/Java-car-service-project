package org.example.service.mapper;

import org.example.core.order.Order;
import org.example.core.responsesAndRequestes.order.CreateOrderRequest;
import org.example.core.responsesAndRequestes.order.ShowOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between {@link Order} entities and their corresponding DTOs.
 * <p>
 * This interface uses MapStruct to generate implementations for mapping between:
 * <ul>
 *     <li>{@link Order} and {@link ShowOrderResponse}</li>
 *     <li>{@link CreateOrderRequest} and {@link Order}</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface provides a singleton instance via {@link #INSTANCE}.
 * </p>
 */
@Mapper
public interface OrderMapper {
    /**
     * Singleton instance of the {@link OrderMapper}.
     */
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * Converts an {@link Order} entity to a {@link ShowOrderResponse} DTO.
     *
     * @param order the {@link Order} entity to be converted
     * @return the corresponding {@link ShowOrderResponse} DTO
     */
    ShowOrderResponse toOrderResponse(Order order);

    /**
     * Converts a {@link CreateOrderRequest} DTO to an {@link Order} entity.
     *
     * @param createOrderRequest the {@link CreateOrderRequest} DTO to be converted
     * @return the corresponding {@link Order} entity
     */
    Order toOrder(CreateOrderRequest createOrderRequest);
}
