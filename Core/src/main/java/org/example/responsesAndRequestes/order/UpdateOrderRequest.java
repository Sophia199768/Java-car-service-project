package org.example.responsesAndRequestes.order;

import lombok.*;

/**
 * The {@code UpdateOrderRequest} class represents a request to update an existing order's details.
 * It includes the order's identifier, status, and additional information.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateOrderRequest {
    /**
     * The unique identifier of the order to be updated.
     */
    private Integer id;

    /**
     * The new status of the order (e.g., pending, completed).
     */
    private String status;

    /**
     * Additional information to update for the order.
     */
    private String information;
}
