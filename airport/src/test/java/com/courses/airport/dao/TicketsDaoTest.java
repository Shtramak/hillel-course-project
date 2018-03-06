package com.courses.airport.dao;

import com.courses.airport.connection.ConnectionFactory;
import com.courses.airport.essences.Ticket;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TicketsDaoTest {

    private Connection connection;
    private TicketsDao ticketsDao;

    @Before
    public void setUp() throws Exception {
        connection = (Connection) ConnectionFactory.getInstance();
        RunScript.execute(ConnectionFactory.getInstance().getConnection(), new FileReader("src/test/resources/db-creation.sql"));
        ticketsDao = new TicketsDao(connection);
    }

    @Test
    void getAllTickets() {
    }

    @Test
    void getTicketById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void create() {

    }
}