package org.example.responsesAndRequestes.car;

import lombok.*;

/**
 * The {@code UpdateCarRequest} class represents a request to update an existing car's details.
 * It includes the car's identifier, condition, and price.
 */
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
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
