package com.courses.airport;

import com.courses.airport.dao.TicketDao;
import com.courses.airport.essences.Ticket;

public class MainTester {
    public static void main(String[] args) {
        TicketDao ticketDao = new TicketDao();
        Ticket ticket = ticketDao.getTicketById(1);
        System.out.println(ticket);
    }
}
