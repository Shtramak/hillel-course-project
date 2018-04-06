package com.courses.tellus.servlet.subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import com.courses.tellus.exception.jdbc.EntityIdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class SubjectEditServletMockTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    private SubjectEditServlet servlet;
    private Subject subject;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        servlet = new SubjectEditServlet();
        subject = new Subject(1L,"Math", " fdsd fsd", true,
                new GregorianCalendar(2000, 10, 15));
        MockitoAnnotations.initMocks(this);
        field = servlet.getClass().getDeclaredField("subjectDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        given(request.getParameter("subjectId")).willReturn("1");
        given(request.getParameter("name")).willReturn(subject.getName());
        given(request.getParameter("description")).willReturn(subject.getDescription());
        given(request.getParameter("activeRadios")).willReturn("Y");
        given(request.getParameter("day")).willReturn("15");
        given(request.getParameter("month")).willReturn("10");
        given(request.getParameter("year")).willReturn("2000");
        given(request.getServletContext()).willReturn(servletContext);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
    }

    @Test
    void doGetTestAndGetSubjectParamsWhenActive() throws Exception {
        field.set(servlet, subjectDao);
        when(request.getParameter("subjectId")).thenReturn("1");
        when(subjectDao.getById(anyLong())).thenReturn(subject);
        servlet.doGet(request, response);
        verify(subjectDao, atLeastOnce()).getById(anyLong());
    }

    @Test
    void doGetTestAndThrowException() throws Exception {
        field.set(servlet, subjectDao);
        when(request.getParameter("subjectId")).thenReturn("1");
        when(subjectDao.getById(anyLong())).thenThrow(EntityIdNotFoundException.class);
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute(anyString(), any());
    }

    @Test
    void doPostTestAndUpdateSubject() throws Exception {
        field.set(servlet, subjectDao);
        servlet.doPost(request, response);
        verify(subjectDao, atLeastOnce()).update(subject);
    }

    @Test
    void doPostTestAndThrowException() throws Exception {
        field.set(servlet, subjectDao);
        when(subjectDao.update(subject)).thenThrow(DatabaseConnectionException.class);
        servlet.doPost(request, response);
        verify(request, atLeastOnce()).setAttribute(anyString(), any());
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        SubjectDao testDao = new SubjectDao(factory);
        servlet.init();
    }
}