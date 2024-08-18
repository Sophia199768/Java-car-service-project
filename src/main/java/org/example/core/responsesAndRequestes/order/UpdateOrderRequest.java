package org.example.core.responsesAndRequestes.order;


import java.util.Objects;

/**
 * The {@code UpdateOrderRequest} class represents a request to update an existing order's details.
 * It includes the order's identifier, status, and additional information.
 */

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateOrderRequest that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getInformation(), that.getInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getInformation());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
