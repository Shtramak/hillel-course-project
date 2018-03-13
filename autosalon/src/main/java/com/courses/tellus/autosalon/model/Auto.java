package com.courses.tellus.autosalon.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Auto {

    private Long id;
    private String brand;
    private String model;
    private Integer manufactYear;
    private String producerCountry;
    private BigDecimal price;

    public Auto() {
    }

    public Auto(Long id, String brand, String model, Integer manufactYear, String producerCountry, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.manufactYear = manufactYear;
        this.producerCountry = producerCountry;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getManufactYear() {
        return manufactYear;
    }

    public void setManufactYear(Integer manufactYear) {
        this.manufactYear = manufactYear;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Auto)) {
            return false;
        }
        Auto auto = (Auto) o;
        return Objects.equals(getId(), auto.getId())
                && Objects.equals(getBrand(), auto.getBrand())
                && Objects.equals(getModel(), auto.getModel())
                && Objects.equals(getManufactYear(), auto.getManufactYear())
                && Objects.equals(getProducerCountry(), auto.getProducerCountry())
                && Objects.equals(getPrice(), auto.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getManufactYear(), getProducerCountry(), getPrice());
    }
}