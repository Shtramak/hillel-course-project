package com.courses.tellus.autosalon.servlets;

import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

import static org.mockito.Matchers.anyString;
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

    AutosalonServlet autosalonServlet;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        autosalonServlet = new AutosalonServlet();

        AutosalonHeandler autosalonHeandler = new AutosalonHeandler();
        Field daoField = autosalonHeandler.getClass().getDeclaredField("autosalonDao");
        daoField.setAccessible(true);
        daoField.set(autosalonHeandler, autosalonDao);

        Field factory = HeandlerFactory.class.getDeclaredField("handlers");
        factory.setAccessible(true);
        Map<String, InternalHeandler> handlerMap = Collections.singletonMap("autosalon", autosalonHeandler);
        factory.set(HeandlerFactory.class, handlerMap);

    }

    @Test
    public void testPost() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/createautosalon");
        when(request.getParameter("name")).thenReturn("Toyota");
        when(request.getParameter("address")).thenReturn("Japan");
        when(request.getParameter("telephone")).thenReturn("208990");
        when(request.getRequestDispatcher("/WEB-INF/jsp/createautosalon.jsp")).thenReturn(dispatcher);
        Autosalon autosalon = new Autosalon("Toyota", "Japan", "208990");
        autosalonDao.insert(autosalon);
        autosalonServlet.doPost(request, response);
        verify(autosalonDao, times(1)).insert(autosalon);
        //Assertions.assertEquals(response.getContentType(), "/createautosalon");
        verify(request, times(1)).setAttribute("autosalon", autosalon);
    }

    @Test
    public void testGetAllAutosalon() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/allAutosalon");
        when(request.getRequestDispatcher("/WEB-INF/jsp/allAutosalon.jsp")).thenReturn(dispatcher);
        autosalonDao.getAll();
        verify(autosalonDao, times(1)).getAll();
        autosalonServlet.doGet(request, response);
        //verify(request, times(1));
        //Assertions.assertEquals(response.getContentType(), "/allAutosalon");
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    public void testGetCreateAutosalon() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/createautosalon");
        when(request.getRequestDispatcher("/WEB-INF/jsp/createautosalon.jsp")).thenReturn(dispatcher);
        autosalonDao.getAll();
        verify(autosalonDao, times(1)).getAll();
        autosalonServlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }


}
