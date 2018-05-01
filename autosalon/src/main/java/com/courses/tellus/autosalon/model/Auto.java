package com.courses.tellus.autosalon.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Auto {
    @Id
    @Column(name = "auto_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Column(name = "manufact_year")
    private Integer manufactYear;
    @Column(name = "producer_country")
    private String producerCountry;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "autosalon_id")
    private Autosalon autosalon;

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

    public Autosalon getAutosalon() {
        return autosalon;
    }

    public void setAutosalon(Autosalon autosalon) {
        this.autosalon = autosalon;
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