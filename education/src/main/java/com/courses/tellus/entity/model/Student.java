package com.courses.tellus.entity.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "student_card_number")
    private String studentCardNumber;

    private String address;

    @ManyToMany
    @JoinTable(
            name = "university_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "uni_id")
    )
    private Set<University> universities;

    @ManyToMany
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

    /**
     * Empty Object constructor.
     */
    public Student() {
    }

    /**
     * Constructor for creating new Object.
     *
     * @param firstName student name
     * @param lastName student surname
     * @param studentCardNumber persona id of student card
     * @param address living address
     */
    public Student(final String firstName, final String lastName, final String studentCardNumber,
                   final String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCardNumber = studentCardNumber;
        this.address = address;
    }

    /**
     * Constructor for creating new Object obtained from database.
     *
     * @param studentId student id in database
     * @param firstName student name
     * @param lastName student surname
     * @param studentCardNumber persona id of student card
     * @param address living address
     */
    public Student(final long studentId, final String firstName, final String lastName, final String studentCardNumber,
                   final String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCardNumber = studentCardNumber;
        this.address = address;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(final long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getStudentCardNumber() {
        return studentCardNumber;
    }

    public void setStudentCardNumber(final String studentCardNumber) {
        this.studentCardNumber = studentCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Set<University> getUniversities() {
        return universities;
    }

    public void setUniversities(Set<University> universities) {
        this.universities = universities;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId=" + studentId
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", studentCardNumber='" + studentCardNumber + '\''
                + ", address='" + address + '\''
                + ", universities=" + universities
                + ", subjects=" + subjects
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student student = (Student) obj;
        return getStudentId() == student.getStudentId()
                && Objects.equals(getFirstName(), student.getFirstName())
                && Objects.equals(getLastName(), student.getLastName())
                && Objects.equals(getStudentCardNumber(), student.getStudentCardNumber())
                && Objects.equals(getAddress(), student.getAddress())
                && Objects.equals(getUniversities(), student.getUniversities())
                && Objects.equals(getSubjects(), student.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getFirstName(), getLastName(), getStudentCardNumber(), getAddress(),
                getUniversities(), getSubjects());
    }
}