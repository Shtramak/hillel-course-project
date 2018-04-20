package com.courses.tellus.persistence.dao.spring;

import com.courses.tellus.airport.dao.spring.jdbc.TicketsDao;
import com.courses.tellus.airport.model.Ticket;
import com.courses.tellus.persistence.dao.datasource.TestDatasource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDatasource.class, TicketsDao.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = "classpath:spring/ticket-table-create.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:spring/ticket-table-delete.sql")
})

public class TicketDaoIntegrarionTests {

    @Autowired
    private TicketsDao ticketsDao;
    private Ticket ticket = new Ticket(1, "Igor", "Fedotov", LocalDate.of(2018, 1, 1), "Keln");

    @Test
    void getAllWhenReturnsTicketsList() {
        assertEquals(1, ticketsDao.getAll().size());
    }

    @Test
    void getAllWhenReturnsZero() {
        ticketsDao.delete(1L);
        assertEquals(0, ticketsDao.getAll().size());
    }

    @Test
    void testGetByIdWhenReturnsTicket() {
        assertEquals(ticket, ticketsDao.getById(1L).get());
    }

    @Sql("classpath:spring/ticket-table-delete.sql")
    @Test
    void getByIdWhenTableNotExists() {
        assertEquals(Optional.empty(), ticketsDao.getById(10L));
    }

    @Test
    void testInsertWhenUpdatedSuccessfully() {
        assertEquals(Integer.valueOf(1), ticketsDao.insert(ticket));
    }

    @Test
    void testUpdateWhenUpdatedSuccessfully() {
        ticket.setName("Avraam");
        assertEquals(Integer.valueOf(1), ticketsDao.update(ticket));
    }

    @Test
    void testDeleteWhenUpdatedSuccessfully() {
        assertEquals(Integer.valueOf(1), ticketsDao.delete(1L));
    }





}
