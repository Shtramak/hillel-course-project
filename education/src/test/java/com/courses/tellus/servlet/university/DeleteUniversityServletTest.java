package com.courses.tellus.servlet.university;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class DeleteUniversityServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private UniversityDao universityDao;
    private DeleteUniversityServlet servlet;
    private University university;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        university = new University("Bogomolca","near zoo","medical");
        servlet = new DeleteUniversityServlet();
        field = servlet.getClass().getDeclaredField("universityDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        given(request.getServletContext()).willReturn(servletContext);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
    }
    @Test
    void deleteTest() throws Exception {
        field.set(servlet ,universityDao);
        doReturn("1").when(request).getParameter("uniId");
        given(universityDao.delete(anyLong())).willReturn(1);
        servlet.doGet(request, response);

        verify(universityDao, atLeastOnce()).delete(anyLong());
        verify(request, atLeast(1)).getServletContext();
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factoryForTest = mock(ConnectionFactory.class);
        UniversityDao universityDao = new UniversityDao(factoryForTest);
        servlet.init();
    }
}
