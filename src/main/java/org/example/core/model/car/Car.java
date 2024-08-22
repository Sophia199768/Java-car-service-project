package org.example.core.model.car;


import java.util.Date;
import java.util.Objects;

/**
 * The Car class represents a car entity with attributes such as brand, model, release year, condition, and price.
 */

public class Car {
    /**
     * The unique identifier for the car.
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
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

    /**
     * The brand of the car (e.g., Toyota, Ford).
     */
    private String carBrand;

    /**
     * The model of the car (e.g., Corolla, Mustang).
     */
    private String carModel;

    /**
     * The release year of the car.
     */
    private Date releaseYear;

    /**
     * The condition of the car (e.g., new, used).
     */
    private String condition;

    /**
     * The price of the car.
     */
    private Long price;

    /**
     * Clone a Car entity to the same a Car object
     *
     * @return A Car object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(getId(), car.getId()) && Objects.equals(getCarBrand(), car.getCarBrand()) && Objects.equals(getCarModel(), car.getCarModel()) && Objects.equals(getReleaseYear(), car.getReleaseYear()) && Objects.equals(getCondition(), car.getCondition()) && Objects.equals(getPrice(), car.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCarBrand(), getCarModel(), getReleaseYear(), getCondition(), getPrice());
    }
}
