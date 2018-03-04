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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Auto)) {
            return false;
        }
        Auto auto = (Auto) o;
        if (id != auto.id) {
            return false;
        }
        if (!getBrand().equals(auto.getBrand())) {
            return false;
        }
        if (!getModel().equals(auto.getModel())) {
            return false;
        }
        if (!getManufactYear().equals(auto.getManufactYear())) {
            return false;
        }
        if (!getProducerCountry().equals(auto.getProducerCountry())) {
            return false;
        }
        return getPrice().equals(auto.getPrice());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getBrand().hashCode();
        result = prime * result + getModel().hashCode();
        result = prime * result + getManufactYear().hashCode();
        result = prime * result + getProducerCountry().hashCode();
        result = prime * result + getPrice().hashCode();
        return result;
    }
}
