package com.courses.airport.dao;

import com.courses.airport.mysqlconnection.MainConnection;
import com.courses.airport.essences.Ticket;
import java.sql.*;

public class TicketDao {

    public void createTicket (Ticket ticket) {
        String createRawQuerry = "INSERT INTO `airport_tickets`.`abracadabra` VALUES ("
                + ticket.getTicketId()          + ", '"
                + ticket.getPasName()           + "', '"
                + ticket.getPasSurname()        + "', '"
                + ticket.getFlightDate()        + "', '"
                + ticket.getPlane()             + "');";
        updateTable(createRawQuerry);
    }

    public Ticket getTicketById(int ticketId) {
        Ticket ticket = null;
        Statement st = null;
        ResultSet resultSet = null;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.connect();
        String readQuerry = "SELECT * FROM airport_tickets.abracadabra WHERE ticket_id = " + ticketId + ";";

        try {
            st = con.createStatement();
            resultSet = st.executeQuery(readQuerry );
            resultSet.next();
            ticket = new Ticket(
                    ticketId,
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mainConnection.disconnect();
        }

        return ticket;
    }

    public void updateTicket (int id, Ticket ticket) {
        String updateQuerry = "" +
                "UPDATE airport_tickets.abracadabra " +
                "SET " +
                "pass_Name = '"     + ticket.getPasName()           + "', " +
                "pass_Surname = '"  + ticket.getPasSurname()        + "', " +
                "flight_date = '"   + ticket.getFlightDate()        + "', " +
                "plane_code = '"    + ticket.getPlane()             + "' " +
                "WHERE " +
                "ticket_id = " + id + ";";
        updateTable(updateQuerry);
    }

    public void deleteTicket (int id) {
        String deleteQuerry = "DELETE FROM airport_tickets.abracadabra WHERE ticket_id =" + id + ";";
        updateTable(deleteQuerry);
    }

    private void updateTable (String querry) { // Creates connection and updates table with querries: "create", "update", "delete"
        PreparedStatement st = null;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.connect();

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
            mainConnection.disconnect();
        }
    }

    public int getLastId() throws SQLException {   // Method, that detects and returnes the last Id in the table
        int lastIndex = 0;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `airport_tickets`.`abracadabra`");
        rs.last();
        lastIndex = rs.getInt(1);
        mainConnection.disconnect();

        return lastIndex;
    }

    public void createTable() {
        String sql = "CREATE TABLE `airport_tickets`.`abracadabra` (\n" +
                "  `ticket_id` INT(11) NOT NULL,\n" +
                "  `pass_Name` VARCHAR(45) NULL,\n" +
                "  `pass_Surname` VARCHAR(45) NULL,\n" +
                "  `flight_date` VARCHAR(45) NULL,\n" +
                "  `plane_code` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`ticket_id`));";
        Statement st = null;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.connect();
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            mainConnection.disconnect();
        }

    }

    public void dropTable() {
        String sql = "DROP TABLE `airport_tickets`.`abracadabra`;";
        Statement st = null;
        MainConnection mainConnection = new MainConnection();
        Connection con = mainConnection.connect();
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            mainConnection.disconnect();
        }

    }
}
