package com.courses.tellus.autosalon.dao.servlets;

import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import com.courses.tellus.autosalon.servlets.AutosalonHeandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutosalonServletMockTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    AutosalonDao autosalonDao;

    AutosalonHeandler autosalonHeandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        autosalonHeandler = new AutosalonHeandler();
    }

    @Test
    public void testPost() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1L");
        when(request.getParameter("name")).thenReturn("BMW");
        when(request.getParameter("address")).thenReturn("Germany");
        when(request.getParameter("telephone")).thenReturn("40682016");
        Autosalon autosalon = new Autosalon(1L, "BMW", "Germany", "40682016");
        autosalonDao.insert(autosalon);
        verify(autosalonDao, times(1)).insert(autosalon);
        autosalonHeandler.post(request, response);
        verify(response).sendRedirect(request.getContextPath() + "allAutosalon");
    }

    @Test
    public void testGetAllAutosalon() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/autosalon/allAutosalon");
        when(request.getRequestDispatcher("/WEB-INF/views/allAutosalon.jsp")).thenReturn(dispatcher);
        autosalonDao.getAll();
        verify(autosalonDao, times(1)).getAll();
        autosalonHeandler.get(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testGetCreateAutosalon() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/autosalon/createautosalon");
        when(request.getRequestDispatcher("/WEB-INF/views/createautosalon.jsp")).thenReturn(dispatcher);
        autosalonDao.getAll();
        verify(autosalonDao, times(1)).getAll();
        autosalonHeandler.get(request, response);
        verify(dispatcher).forward(request, response);
    }


}
