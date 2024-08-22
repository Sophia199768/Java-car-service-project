package org.example.core.responsesAndRequestes.order;


import java.util.Date;
import java.util.Objects;

/**
 * The {@code FilterOrderRequest} class represents a request to filter orders
 * based on specified criteria such as user ID, car ID, status, and date.
 */

public class FilterOrderRequest {
    /**
     * The unique identifier of the user whose orders are to be filtered.
     */
    private Integer userId;

    /**
     * The unique identifier of the car associated with the orders to be filtered.
     */
    private Integer carId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterOrderRequest that)) return false;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getCarId(), that.getCarId()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCarId(), getStatus(), getDate());
    }

    /**
     * The status of the orders to be filtered (e.g., pending, completed).
     */
    private String status;

    /**
     * The date of the orders to be filtered.
     */
    private Date date;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
