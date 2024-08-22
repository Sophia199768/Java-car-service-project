package org.example.core.responsesAndRequestes.order;


import java.util.Objects;

/**
 * The {@code ShowOrderResponse} class represents the response containing details of an order
 * that is displayed to the user, including its identifier, user ID, car ID, and additional information.
 */

public class ShowOrderResponse {
    /**
     * The unique identifier of the order.
     */
    private Integer id;

    /**
     * The unique identifier of the user who placed the order.
     */
    private Integer userId;

    /**
     * The unique identifier of the car associated with the order.
     */
    private Integer carId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowOrderResponse that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getCarId(), that.getCarId()) && Objects.equals(getInformation(), that.getInformation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getCarId(), getInformation());
    }

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
}
