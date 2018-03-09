package com.courses.airport.essences;

import java.time.LocalDate;

public class Airport {

    private long airportId;
    private String nameAirport;
    private LocalDate dateOfBirth;
    private String numberTerminal;
    private String Telephone;


    public Airport(long airportId, String nameAirport, LocalDate dateOfBirth, String numberTerminal, String telephone) {
        this.airportId = airportId;
        this.nameAirport = nameAirport;
        this.dateOfBirth = dateOfBirth;
        this.numberTerminal = numberTerminal;
        Telephone = telephone;
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
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", nameAirport='" + nameAirport + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", numberTerminal='" + numberTerminal + '\'' +
                ", Telephone='" + Telephone + '\'' +
                '}';
    }
}
