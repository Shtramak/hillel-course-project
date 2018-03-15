package com.courses.airport.model;

import java.time.LocalDate;

public class Ticket {

    private Long ticketId;
    private String name;
    private String surname;
    private LocalDate dateFlight;
    private String destCity;

    public Ticket() {
    }

    public Ticket(final long ticketId, final String name, final String sName, final LocalDate dateFlight, final String dest) {
        this.ticketId = ticketId;
        this.name = name;
        this.surname = sName;
        this.dateFlight = dateFlight;
        this.destCity = dest;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(final long ticketId) {
        this.ticketId = ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String sName) {
        this.surname = sName;
    }

    public LocalDate getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(final LocalDate dateFlight) {
        this.dateFlight = dateFlight;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(final String dest) {
        this.destCity = dest;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Ticket ticket = (Ticket) object;
        return hasSameFields(ticket);
    }

    private boolean hasSameFields(final Ticket ticket) {
        if (ticketId != ticket.ticketId) {
            return false;
        }
        if (!name.equals(ticket.name)) {
            return false;
        }
        if (!surname.equals(ticket.surname)) {
            return false;
        }
        return dateFlight.equals(ticket.dateFlight);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{"
                + "ticketId=" + ticketId
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", dateOfFlight=" + dateFlight
                + ", destinationCity=" + destCity
                + '}';
    }
}
