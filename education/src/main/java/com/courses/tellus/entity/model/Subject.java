package com.courses.tellus.entity.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long subjectId;

    private String name;

    private String description;

    private boolean valid;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @OneToMany
    private Set<University> universities;

    @OneToMany
    private Set<Student> students;

    /**
     * Empty Object constructor.
     */
    public Subject() {
    }

    /**
     * Constructor for creating new Object (servlets and first version mvc).
     *
     * @param name title of the subject
     * @param description small description
     * @param valid deprecation mark
     * @param date of creation this object
     */
    public Subject(final String name, final String description, final boolean valid, final LocalDate date) {
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = date;
    }

    /**
     * Constructor for creating new Object (hibernate).
     *
     * @param name title of the subject
     * @param description small description
     * @param valid deprecation mark
     * @param dateOfCreation of creation this object
     * @param universities collection with universities where is been this subject.
     */
    public Subject(String name, String description, boolean valid, LocalDate dateOfCreation,
                   Set<University> universities) {
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = dateOfCreation;
        this.universities = universities;
    }

    /**
     * Constructor for creating new Object obtained from database (servlets and first version mvc).
     *
     * @param subjectId object database id
     * @param name title of the subject
     * @param description small description
     * @param valid deprecation mark
     * @param date of creation this object
     */
    public Subject(final Long subjectId, final String name, final String description, final boolean valid,
                   final LocalDate date) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
        this.valid = valid;
        this.dateOfCreation = date;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(final Long subjectId) {
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

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(final LocalDate date) {
        this.dateOfCreation = date;
    }

    public Set<University> getUniversities() {
        return universities;
    }

    public void setUniversities(Set<University> universities) {
        this.universities = universities;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "subjectId=" + subjectId
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", valid=" + valid
                + ", dateOfCreation=" + dateOfCreation
                + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Subject)) {
            return false;
        }
        final Subject subject = (Subject) obj;
        return isValid() == subject.isValid()
                && Objects.equals(getSubjectId(), subject.getSubjectId())
                && Objects.equals(getName(), subject.getName())
                && Objects.equals(getDescription(), subject.getDescription())
                && Objects.equals(getDateOfCreation(), subject.getDateOfCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubjectId(), getName(), getDescription(), isValid(), getDateOfCreation());
    }
}
