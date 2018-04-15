package com.courses.tellus.autosalon.servlet;


import com.courses.tellus.autosalon.dao.springjdbc.AutoDao;
import com.courses.tellus.autosalon.model.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AutoServletMockTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    AutoDao autoDao;

    MainServlet autoServlet;

    @BeforeEach
    public void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        autoServlet = new MainServlet();

        AutoHeandler autoHeandler = new AutoHeandler();
        Field daoField = autoHeandler.getClass().getDeclaredField("autoDao");
        daoField.setAccessible(true);
        daoField.set(autoHeandler, autoDao);

        Field factory = HandlerFactory.class.getDeclaredField("handlerMap");
        factory.setAccessible(true);
        Map<String, InternalHandler> handlerMap = Collections.singletonMap("auto", autoHeandler);
        factory.set(HandlerFactory.class, handlerMap);
    }

    @Test
    public void testPost() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/auto/createAuto");
        when(request.getParameter("id")).thenReturn("1L");
        when(request.getParameter("brand")).thenReturn("BMW");
        when(request.getParameter("model")).thenReturn("X1");
        when(request.getParameter("manufactYear")).thenReturn("2016");
        when(request.getParameter("producerCountry")).thenReturn("Germany");
        when(request.getParameter("price")).thenReturn("20000");
        Auto auto = new Auto(1L, "BMW", "X1", 2016, "Germany", new BigDecimal(20000));
        autoDao.insert(auto);
        verify(autoDao, times(1)).insert(auto);
        autoServlet.doPost(request, response);
        verify(response).sendRedirect(request.getContextPath() + "listAuto");
    }

    @Test
    public void testGetListAuto() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/auto/listAuto");
        when(request.getRequestDispatcher("/WEB-INF/jsp/listAuto.jsp")).thenReturn(dispatcher);
        autoDao.getAll();
        verify(autoDao, times(1)).getAll();
        autoServlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testGetCreateAuto() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/auto/createAuto");
        when(request.getRequestDispatcher("/WEB-INF/jsp/createAuto.jsp")).thenReturn(dispatcher);
        autoDao.getAll();
        verify(autoDao, times(1)).getAll();
        autoServlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void getWhenPathIsNotValid() throws Exception {
        when(request.getPathInfo()).thenReturn("/not-valid-path");
        autoServlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    void getWhenHandlerNotExistsInHandlerFactory() throws Exception {
        when(request.getPathInfo()).thenReturn("/Not-Valid-Path");
        autoServlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    void postWhenHandlerNotExistsInHandlerFactory() throws Exception {
        when(request.getPathInfo()).thenReturn("/Not-Valid-Path");
        autoServlet.doPost(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}