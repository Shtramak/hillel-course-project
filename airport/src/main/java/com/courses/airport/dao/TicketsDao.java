package com.courses.airport.dao;

import com.courses.airport.essences.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketsDao {

    private final transient Connection connection;

    public TicketsDao(final Connection connection) {
        this.connection = connection;
    }

    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> listOfTickets = new ArrayList<Ticket>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT*FROM airport_tickets")){
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    listOfTickets.add(getTicketFromResultSet(resultSet));
                }
            }
        }
        return listOfTickets;
    }

    public Ticket getTicketById(Integer entityId) throws SQLException {
        Ticket universityById = new Ticket();
        try (PreparedStatement ps = connection.prepareStatement("SELECT*FROM airport_tickets WHERE id=?")) {
            ps.setInt(1, entityId);
            ps.executeQuery();
            try (ResultSet resultSet = ps.executeQuery()) {
                universityById = getTicketFromResultSet(resultSet);
            }
        }
        return universityById;
    }

    public void update(Ticket ticket) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("UPDATE airport_tickets SET" + " pass_Name = ? , pass_Surname =?, flight_date =?, plane_code =? WHERE id= ?")) {

            preparedStatement.setString(1, ticket.getPasName());
            preparedStatement.setString(2, ticket.getPasSurname());
            preparedStatement.setString(3, ticket.getFlightDate());
            preparedStatement.setString(4,ticket.getPlane());
            preparedStatement.setInt(5,ticket.getTicketId());
            preparedStatement.executeUpdate();

        }
    }

    public void delete(int ticketId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM airport_tickets WHERE id=?")){
            preparedStatement .setInt(1,ticketId);
            preparedStatement .executeUpdate();
        }
    }

    public void create(Ticket ticket) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement ("INSERT INTO airport_tickets(pass_Name, pass_Surname,flight_date, plane_code ) VALUES (?,?,?,?,?)")) {
            preparedStatement.setString(1, ticket.getPasName());
            preparedStatement.setString(2, ticket.getPasSurname());
            preparedStatement.setString(3, ticket.getFlightDate());
            preparedStatement.setString(4, ticket.getPlane());
            preparedStatement.executeUpdate();
        }
    }

    private Ticket getTicketFromResultSet(ResultSet resultSet) throws SQLException {
        final Ticket ticket= new Ticket();
        ticket.setTicketId(resultSet.getInt("ticket_Id"));
        ticket.setPasName(resultSet.getString("pass_Name"));
        ticket.setPasSurname(resultSet.getString("pass_Surname"));
        ticket.setFlightDate(resultSet.getString("flight_date"));
        ticket.setPlane(resultSet.getString("plane_code"));
        return ticket;
    }

}
