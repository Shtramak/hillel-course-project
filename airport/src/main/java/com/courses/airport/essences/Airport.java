package com.courses.airport.essences;

public class Airport {

    private Integer airportId;
    private int numberTerminal;
    private String nameAirport;

    public Airport() {
    }

    public Airport(final Integer airportId, final int numberTerminal, final String nameAirport) {
        this.airportId = airportId;
        this.numberTerminal = numberTerminal;
        this.nameAirport = nameAirport;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public int getNumberTerminal() {
        return numberTerminal;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setAirportId(final Integer airportId) {
        this.airportId = airportId;
    }

    public void setNumberTerminal(final int numberTerminal) {
        this.numberTerminal = numberTerminal;
    }

    public void setNameAirport(final String nameAirport) {
        this.nameAirport = nameAirport;
    }

    @Override
    public String toString() {
        return "Airport{"
                + "airportId=" + airportId
                + ", numberTerminal=" + numberTerminal
                + ", nameAirport='" + nameAirport + '\''
                + '}';
    }
}
