package main.java.com.courses.tellus.airoport;

public class Airport {

    private Integer airportId;
    private int numberTerminal;
    private String nameAirport;

    public Airport() {
    }

    public Airport(Integer airportId, int numberTerminal, String nameAirport) {
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

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public void setNumberTerminal(int numberTerminal) {
        this.numberTerminal = numberTerminal;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }
}
