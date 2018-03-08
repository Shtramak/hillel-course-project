package com.courses.airport.essences;

import java.time.LocalDate;

public class Ticket {

    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfFlight;
    private String destCity;

    public Ticket() {
    }

    public Ticket(long id, String name, String surname, LocalDate dateOfFlight, String destCity) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfFlight = dateOfFlight;
        this.destCity = destCity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        if (id != ticket.id) {
            return false;
        }
        if (!name.equals(ticket.name)) {
            return false;
        }
        if (!surname.equals(ticket.surname)) {
            return false;
        }
        return dateOfFlight.equals(ticket.dateOfFlight);
    }

    @Override
    public int hashCode() {
        final int bits = 32;
        int result = (int) (id ^ (id >>> bits));
        final int primeNumber = 31;
        result = primeNumber * result + name.hashCode();
        result = primeNumber * result + surname.hashCode();
        result = primeNumber * result + dateOfFlight.hashCode();
        result = primeNumber * result + destCity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", dateOfFlight=" + dateOfFlight
                + ", destinationCity=" + destCity
                + '}';
    }
}
