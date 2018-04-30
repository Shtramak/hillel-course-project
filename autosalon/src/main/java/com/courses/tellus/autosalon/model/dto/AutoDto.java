package com.courses.tellus.autosalon.model.dto;

import java.util.Objects;

import com.courses.tellus.autosalon.model.Auto;

public class AutoDto {

    private Long id;
    private String brand;
    private String model;
    private String manufactYear;
    private String producerCountry;
    private String price;

    public AutoDto() {
    }

    public AutoDto(Long id, String brand, String model, String manufactYear, String producerCountry, String price) {
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

    public String getManufactYear() {
        return manufactYear;
    }

    public void setManufactYear(String manufactYear) {
        this.manufactYear = manufactYear;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
