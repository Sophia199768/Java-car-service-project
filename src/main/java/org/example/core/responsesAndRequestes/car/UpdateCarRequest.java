package org.example.core.responsesAndRequestes.car;


import java.util.Objects;

/**
 * The {@code UpdateCarRequest} class represents a request to update an existing car's details.
 * It includes the car's identifier, condition, and price.
 */

public class UpdateCarRequest {
    /**
     * The unique identifier of the car to be updated.
     */
    private Integer id;

    /**
     * The new condition of the car (e.g., new, used).
     */
    private String condition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateCarRequest that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCondition(), that.getCondition()) && Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCondition(), getPrice());
    }

    /**
     * The new price of the car.
     */
    private Long price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
