package com.courses.airport.dao;

import com.courses.airport.essences.Ticket;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

class TicketDaoIntTest {
    private TicketDao ticketDao = new TicketDao();
    private Ticket ticket = new Ticket(1, "Igor", "Repnii", "2018-01-02", "AH-1");

    @BeforeEach
    public void createTable() {
        ticketDao.dropTable();
        ticketDao.createTable();
    }

    @Test
    public void testingDriverManager () {
        boolean thrown = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find mysql-connector in current project...");
            thrown = true;
        } finally {
            assertFalse(thrown);
        }
    }

    @Test
    void createTicket() throws SQLException {
        ticketDao.createTicket(ticket);
        Assertions.assertTrue(ticketDao.getLastId() == 1);
    }

    @Test
    void getTicketById() {
        ticketDao.createTicket(ticket);
        Assertions.assertTrue(ticket.toString().equals(ticketDao.getTicketById(ticket.getTicketId()).toString()));
    }

    @Test
    void updateTicket() {
        ticketDao.createTicket(ticket);
        int id = ticket.getTicketId();
        String updatedName = "Avraam";
        ticket.setPasName(updatedName);
        ticketDao.updateTicket(id, ticket);
        Assertions.assertTrue(ticketDao.getTicketById(id).getPasName().equals(updatedName));
    }

    @Test
    void deleteTicket() throws SQLException {
        Ticket newTicket = new Ticket(2, "Name", "Surname", "2018-03-01", "AH-1");
        ticketDao.createTicket(ticket);
        ticketDao.createTicket(newTicket);
        ticketDao.deleteTicket(newTicket.getTicketId());
        Assertions.assertTrue(ticketDao.getLastId() == ticket.getTicketId());

    }
}