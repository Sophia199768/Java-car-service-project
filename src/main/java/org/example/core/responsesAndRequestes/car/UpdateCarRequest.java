package org.example.core.responsesAndRequestes.car;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The {@code UpdateCarRequest} class represents a request to update an existing car's details.
 * It includes the car's identifier, condition, and price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCarRequest {
    /**
     * The unique identifier of the car to be updated.
     */
    private Integer id;

    /**
     * The new condition of the car (e.g., new, used).
     */
    private String condition;

    /**
     * The new price of the car.
     */
    private Long price;
}
