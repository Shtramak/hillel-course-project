package com.courses.tellus.autosalon.model;

import java.math.BigDecimal;

public class Auto {

    private Long id;
    private String brand;
    private String model;
    private Integer manufactYear;
    private String producerCountry;
    private BigDecimal price;

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
}
