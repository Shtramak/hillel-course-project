package com.courses.tellus.entity;

import java.util.Date;

public class Subject {

    private int id;
    private String name;
    private String description;
    private boolean valid;
    private Date dateOfCreation;

    public Subject() {
    }

    public Subject(String name, String description, boolean valid, Date dateOfCreation) {
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
    }

    public Subject(int id, String name, String description, boolean valid, Date dateOfCreation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", valid=" + valid
                + ", dateOfCreation=" + dateOfCreation
                + '}';
    }
}
