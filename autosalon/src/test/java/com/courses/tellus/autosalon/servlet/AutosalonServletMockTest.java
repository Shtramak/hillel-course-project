package com.courses.tellus.autosalon.servlet;

import com.courses.tellus.autosalon.dao.springjdbc.AutosalonDao;
import com.courses.tellus.autosalon.model.Autosalon;
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
import java.util.Collections;
import java.util.Map;

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

    MainServlet autosalonServlet;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        autosalonServlet = new MainServlet();

        AutosalonHandler autosalonHandler = new AutosalonHandler();
        Field daoField = autosalonHandler.getClass().getDeclaredField("autosalonDao");
        daoField.setAccessible(true);
        daoField.set(autosalonHandler, autosalonDao);

        Field factory = HandlerFactory.class.getDeclaredField("handlerMap");
        factory.setAccessible(true);
        Map<String, InternalHandler> handlerMap = Collections.singletonMap("autosalon", autosalonHandler);
        factory.set(HandlerFactory.class, handlerMap);
    }

    @Test
    public void testPost() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/autosalon/createautosalon");
        when(request.getParameter("name")).thenReturn("Toyota");
        when(request.getParameter("address")).thenReturn("Japan");
        when(request.getParameter("telephone")).thenReturn("208990");
        when(request.getRequestDispatcher("/WEB-INF/jsp/createautosalon.jsp")).thenReturn(dispatcher);
        Autosalon autosalon = new Autosalon("Toyota", "Japan", "208990");
        autosalonDao.insert(autosalon);
        autosalonServlet.doPost(request, response);
        verify(autosalonDao, times(1)).insert(autosalon);
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    public void testGetAllAutosalon() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/autosalon/allAutosalon");
        when(request.getRequestDispatcher("/WEB-INF/jsp/allAutosalon.jsp")).thenReturn(dispatcher);
        autosalonDao.getAll();
        verify(autosalonDao, times(1)).getAll();
        autosalonServlet.doGet(request, response);
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    public void testGetCreateAutosalon() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/autosalon/createautosalon");
        when(request.getRequestDispatcher("/WEB-INF/jsp/createautosalon.jsp")).thenReturn(dispatcher);
        autosalonDao.getAll();
        verify(autosalonDao, times(1)).getAll();
        autosalonServlet.doGet(request, response);
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    void getWhenPathIsNotValid() throws Exception {
        when(request.getPathInfo()).thenReturn("/not-valid-path");
        autosalonServlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    void getWhenHandlerNotExistsInHandlerFactory() throws Exception {
        when(request.getPathInfo()).thenReturn("/Not-Valid-Path");
        autosalonServlet.doGet(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Test
    void postWhenHandlerNotExistsInHandlerFactory() throws Exception {
        when(request.getPathInfo()).thenReturn("/Not-Valid-Path");
        autosalonServlet.doPost(request, response);
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}