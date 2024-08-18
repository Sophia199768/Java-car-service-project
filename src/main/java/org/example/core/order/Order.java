package org.example.core.order;


import java.util.Date;
import java.util.Objects;

/**
 * Represents an order in the system with details such as date, user, car, status, and additional information.
 */

public class Order {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getDate(), order.getDate()) && Objects.equals(getUserId(), order.getUserId()) && Objects.equals(getCarId(), order.getCarId()) && Objects.equals(getStatus(), order.getStatus()) && Objects.equals(getInformation(), order.getInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getUserId(), getCarId(), getStatus(), getInformation());
    }

    /**
     * Unique identifier for the order.
     */
    private Integer id;

    /**
     * Date when the order was placed.
     */
    private Date date;

    /**
     * Identifier of the user who placed the order.
     */
    private Integer userId;

    /**
     * Identifier of the car associated with the order.
     */
    private Integer carId;

    /**
     * Status of the order (e.g., pending, completed).
     */
    private String status;

    /**
     * Additional information related to the order.
     */
    private String information;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
