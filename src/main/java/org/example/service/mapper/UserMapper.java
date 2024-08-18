package org.example.service.mapper;

import org.example.core.model.user.User;
import org.example.core.responsesAndRequestes.user.CreateUserRequest;
import org.example.core.responsesAndRequestes.user.ShowUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between {@link User} entities and their corresponding DTOs.
 * <p>
 * This interface uses MapStruct to generate implementations for mapping between:
 * <ul>
 *     <li>{@link User} and {@link ShowUserResponse}</li>
 *     <li>{@link CreateUserRequest} and {@link User}</li>
 * </ul>
 * </p>
 *
 * <p>
 * This interface provides a singleton instance via {@link #INSTANCE}.
 * </p>
 */
@Mapper
public interface UserMapper {
    /**
     * Singleton instance of the {@link UserMapper}.
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Converts a {@link User} entity to a {@link ShowUserResponse} DTO.
     *
     * @param user the {@link User} entity to be converted
     * @return the corresponding {@link ShowUserResponse} DTO
     */
    ShowUserResponse toShowUserResponse(User user);

    /**
     * Converts a {@link CreateUserRequest} DTO to a {@link User} entity.
     *
     * @param createUserRequest the {@link CreateUserRequest} DTO to be converted
     * @return the corresponding {@link User} entity
     */
    User toUser(CreateUserRequest createUserRequest);
}
