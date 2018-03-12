package com.courses.airport.model;

import java.time.LocalDate;

public class Airport {

    private long airportId;
    private String nameAirport;
    private LocalDate dateOfBirth;
    private String numberTerminal;
    private String telephone;

    public Airport() {
    }

    public Airport(final long airId, final String airName, final LocalDate date, final String termNum, final String tel) {
        this.airportId = airId;
        this.nameAirport = airName;
        this.dateOfBirth = date;
        this.numberTerminal = termNum;
        this.telephone = tel;
    }

    public long getAirportId() {
        return airportId;
    }

    public void setAirportId(long airportId) {
        this.airportId = airportId;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNumberTerminal() {
        return numberTerminal;
    }

    public void setNumberTerminal(String numberTerminal) {
        this.numberTerminal = numberTerminal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Airport airport = (Airport) object;
        return hasSameFields(airport);
    }

    private boolean hasSameFields(final Airport airport) {
        if (airportId != airport.airportId) {
            return false;
        }
        if (!nameAirport.equals(airport.nameAirport)) {
            return false;
        }
        if (!dateOfBirth.equals(airport.dateOfBirth)) {
            return false;
        }
        if (!numberTerminal.equals(airport.numberTerminal)) {
            return false;
        }
        return telephone.equals(airport.telephone);
    }

    @Override
    public int hashCode() {
        final int bits = 32;
        int result = (int) (airportId ^ (airportId >>> bits));
        final int primeNumber = 31;
        result = primeNumber * result + nameAirport.hashCode();
        result = primeNumber * result + dateOfBirth.hashCode();
        result = primeNumber * result + numberTerminal.hashCode();
        result = primeNumber * result + telephone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Airport{"
                + "airportId=" + airportId
                + ", nameAirport='" + nameAirport + '\''
                + ", dateOfBirth=" + dateOfBirth
                + ", numberTerminal='" + numberTerminal + '\''
                + ", telephone='" + telephone + '\''
                + '}';
    }
}
