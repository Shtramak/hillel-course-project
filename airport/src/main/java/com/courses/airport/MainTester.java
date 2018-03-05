package com.courses.airport;

import com.courses.airport.dao.TicketDao;
import com.courses.airport.essences.Ticket;

public class MainTester {
    public static void main(String[] args) {
        TicketDao ticketDao = new TicketDao();
        ticketDao.createTable();

        Ticket ticket = new Ticket(02, "Igor", "Repnii", "2018-01-02", "AH-1");

        ticketDao.createTicket(ticket);

//        ticketDao.dropTable();


    }
}
