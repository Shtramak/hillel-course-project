package com.courses.airport.essences;

import java.util.Date;

public class Ticket {

    private int ticketId;
    private String pasName;
    private String pasSurname;
    private String flightDate;
    private String plane;

    public Ticket(int ticketId,  String pasName, String pasSurname, String flightDate, String plane) {
        this.ticketId = ticketId;
        this.pasName = pasName;
        this.pasSurname = pasSurname;
        this.flightDate = flightDate;
        this.plane = plane;
    }

    public Ticket() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getPasName() {
        return pasName;
    }

    public void setPasName(String pasName) {
        this.pasName = pasName;
    }

    public String getPasSurname() {
        return pasSurname;
    }

    public void setPasSurname(String pasSurname) {
        this.pasSurname = pasSurname;
    }

    public String getFlightDate() { return flightDate; }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        plane = plane;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", pasName='" + pasName + '\'' +
                ", pasSurname='" + pasSurname + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", plane='" + plane + '\'' +
                '}';
    }
}
