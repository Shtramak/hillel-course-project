package com.courses.airport.dao;

import com.courses.airport.mysqlconnection.MainConnection;
import com.courses.airport.essences.Ticket;
import java.sql.*;

public class TicketDao {

    public String printHello() {
        return "Hello";
    }

    //Create ticket method
    public void createTicket (Ticket ticket) {
        String createRawQuerry = "INSERT INTO airport_tickets.tickets VALUES ("
                + ticket.getTicketId()          + ", '"
                + ticket.getTicketCode()        + "', '"
                + ticket.getPasName()           + "', '"
                + ticket.getPasSurname()        + "', '"
                + ticket.getFlightDate()        + "', '"
                + ticket.getDispachPoint()      + "', '"
                + ticket.getDestinationPoint()  + "', '"
                + ticket.getPlane()             + "');";
        updateTable(createRawQuerry);
    }

    // Read ticket №id from table
    public Ticket getTicketById(int ticketId) {
        Ticket ticket = null;
        Statement st = null;
        ResultSet resultSet = null;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.getConnection();
        String readQuerry = "SELECT * FROM airport_tickets.tickets WHERE ticket_id = " + ticketId + ";";

        try {
            st = con.createStatement();
            resultSet = st.executeQuery(readQuerry );
            resultSet.next();
            ticket = new Ticket(                // Creating ticket object. Filling constructor with data from Db
                    ticketId,
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mainConnection.closeConnection();
        }

        return ticket;
    }

    // Updating record in 'tickets' table
    public void updateTicket (int id, Ticket ticket) {
        String updateQuerry = "" +
                "UPDATE airport_tickets.tickets " +
                "SET " +
                "ticket_code = '"   + ticket.getTicketCode()        + "', " +
                "pass_Name = '"     + ticket.getPasName()           + "', " +
                "pass_Surname = '"  + ticket.getPasSurname()        + "', " +
                "flight_date = '"   + ticket.getFlightDate()        + "', " +
                "dispach_point = '" + ticket.getDispachPoint()      + "', " +
                "dest_point = '"    + ticket.getDestinationPoint()  + "', " +
                "plane_code = '"    + ticket.getPlane()             + "' " +
                "WHERE " +
                "ticket_id = " + id + ";";
        updateTable(updateQuerry);
    }

    // Deleting record №id from table
    public void deleteTicket (int id) {
        String deleteQuerry = "DELETE FROM  airport_tickets.tickets WHERE ticket_id =" + id + ";";
        updateTable(deleteQuerry);
    }

    private void updateTable (String querry) { // Creates connection and updates table with querries: "create", "update", "delete"
        PreparedStatement st = null;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.getConnection();

        try {
            st = con.prepareStatement(querry);
            int count = st.executeUpdate(querry);
            System.out.println("Success! " + count + " rows affected");
            con.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Wrong data inserted");
        } catch (SQLException e) {
            System.out.println("Error while transfering querry");
            e.printStackTrace();
        } finally {
            mainConnection.closeConnection();
        }
    }

    public int getLastId() {   // Method, that detects and returnes the last Id in the table
        int lastIndex = 0;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.getConnection();
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM airport_tickets.tickets");
            rs.last();
            lastIndex = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error while detecting last index");
            e.printStackTrace();
        } finally {
            mainConnection.closeConnection();
        }
        return lastIndex;
    }
}
