package com.courses.tellus.entity;

import java.util.Date;

public class Subject {

    private transient int subjectId;
    private String name;
    private String description;
    private boolean valid;
    private Date dateOfCreation;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(final int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean valid) {
        this.valid = valid;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(final Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
