package org.example.core.responsesAndRequestes.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code UpdateOrderRequest} class represents a request to update an existing order's details.
 * It includes the order's identifier, status, and additional information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
