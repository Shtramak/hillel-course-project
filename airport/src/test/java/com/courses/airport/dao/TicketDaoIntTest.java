package com.courses.airport.dao;

import com.courses.airport.mysqlconnection.MainConnection;
import com.sun.tools.javac.Main;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class TicketDAOTest {
    private TicketDao ticketDao;

    @Before
    public void createTable() {
        MainConnection mainConnection = new MainConnection();
        Connection connection = (Connection) new MainConnection();
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
    void createTicket() {
        ticketDao = new TicketDao();

    }

    @Test
    void getTicketById() {
    }

    @Test
    void updateTicket() {
    }

    @Test
    void deleteTicket() {
    }

    @Test
    void getLastId() {
    }
}