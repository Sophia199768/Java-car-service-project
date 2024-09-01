package org.example.core.model.car;


import java.time.LocalDate;


/**
 * The Car class represents a car entity with attributes such as brand, model, release year, condition, and price.
 */
public class Car {
    /**
     * The unique identifier for the car.
     */
    private Integer id;

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
    private LocalDate releaseYear;

    /**
     * The condition of the car (e.g., new, used).
     */
    private String condition;

    /**
     * The price of the car.
     */
    private Long price;

    public Car(Integer id, String carBrand, String carModel, LocalDate releaseYear, String condition, Long price) {
        this.id = id;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.releaseYear = releaseYear;
        this.condition = condition;
        this.price = price;
    }

    public Car() {
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public LocalDate getReleaseYear() {
        return this.releaseYear;
    }

    public String getCondition() {
        return this.condition;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Car)) return false;
        final Car other = (Car) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$carBrand = this.getCarBrand();
        final Object other$carBrand = other.getCarBrand();
        if (this$carBrand == null ? other$carBrand != null : !this$carBrand.equals(other$carBrand)) return false;
        final Object this$carModel = this.getCarModel();
        final Object other$carModel = other.getCarModel();
        if (this$carModel == null ? other$carModel != null : !this$carModel.equals(other$carModel)) return false;
        final Object this$releaseYear = this.getReleaseYear();
        final Object other$releaseYear = other.getReleaseYear();
        if (this$releaseYear == null ? other$releaseYear != null : !this$releaseYear.equals(other$releaseYear))
            return false;
        final Object this$condition = this.getCondition();
        final Object other$condition = other.getCondition();
        if (this$condition == null ? other$condition != null : !this$condition.equals(other$condition)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Car;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $carBrand = this.getCarBrand();
        result = result * PRIME + ($carBrand == null ? 43 : $carBrand.hashCode());
        final Object $carModel = this.getCarModel();
        result = result * PRIME + ($carModel == null ? 43 : $carModel.hashCode());
        final Object $releaseYear = this.getReleaseYear();
        result = result * PRIME + ($releaseYear == null ? 43 : $releaseYear.hashCode());
        final Object $condition = this.getCondition();
        result = result * PRIME + ($condition == null ? 43 : $condition.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        return result;
    }

    public String toString() {
        return "Car(id=" + this.getId() + ", carBrand=" + this.getCarBrand() + ", carModel=" + this.getCarModel() + ", releaseYear=" + this.getReleaseYear() + ", condition=" + this.getCondition() + ", price=" + this.getPrice() + ")";
    }

    public static class CarBuilder {
        private Integer id;
        private String carBrand;
        private String carModel;
        private LocalDate releaseYear;
        private String condition;
        private Long price;

        CarBuilder() {
        }

        public CarBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public CarBuilder carBrand(String carBrand) {
            this.carBrand = carBrand;
            return this;
        }

        public CarBuilder carModel(String carModel) {
            this.carModel = carModel;
            return this;
        }

        public CarBuilder releaseYear(LocalDate releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public CarBuilder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public CarBuilder price(Long price) {
            this.price = price;
            return this;
        }

        public Car build() {
            return new Car(this.id, this.carBrand, this.carModel, this.releaseYear, this.condition, this.price);
        }

        public String toString() {
            return "Car.CarBuilder(id=" + this.id + ", carBrand=" + this.carBrand + ", carModel=" + this.carModel + ", releaseYear=" + this.releaseYear + ", condition=" + this.condition + ", price=" + this.price + ")";
        }
    }
}
