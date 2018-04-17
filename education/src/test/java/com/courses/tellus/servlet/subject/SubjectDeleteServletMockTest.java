package com.courses.tellus.servlet.subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Field;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class SubjectDeleteServletMockTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    private SubjectDeleteServlet servlet;
    private Subject subject;
    private Field field;

    @BeforeEach
    void initMockito() throws Exception {
        subject = new Subject("Math", " fdsd fsd", true, "2000-10-15");
        servlet = new SubjectDeleteServlet();
        field = servlet.getClass().getDeclaredField("subjectDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    void successFullDeleteOperation() throws Exception {
        field.set(servlet ,subjectDao);
        doReturn("1").when(request).getParameter("subjectId");
        given(subjectDao.delete(anyLong())).willReturn(1);
        servlet.doGet(request, response);

        verify(subjectDao, atLeastOnce()).delete(anyLong());
        verify(request, atLeast(1)).getServletContext();
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        SubjectDao testDao = new SubjectDao(factory);
        servlet.init();
    }
}
