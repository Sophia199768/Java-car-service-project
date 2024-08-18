package org.example.core.responsesAndRequestes.order;

import java.util.Date;
import java.util.Objects;

/**
 * The {@code CreateOrderRequest} class represents a request to create a new order
 * with specified details such as user ID, car ID, additional information, date, and status.
 */

public class CreateOrderRequest {
    /**
     * The unique identifier of the user placing the order.
     */
    private Integer userId;

    /**
     * The unique identifier of the car being ordered.
     */
    private Integer carId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateOrderRequest that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getCarId(), that.getCarId()) && Objects.equals(getInformation(), that.getInformation()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCarId(), getInformation(), getDate(), getStatus());
    }

    /**
     * Additional information related to the order.
     */
    private String information;

    /**
     * The date when the order is created.
     */
    private Date date;

    /**
     * The status of the order (e.g., pending, completed).
     */
    private String status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
