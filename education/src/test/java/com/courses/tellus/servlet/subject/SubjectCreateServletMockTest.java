package com.courses.tellus.servlet.subject;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class SubjectCreateServletMockTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    private SubjectCreateServlet servlet;
    private Subject subject;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        subject = new Subject("Math", " fdsd fsd", true,
                new GregorianCalendar(2000, 10, 15));
        servlet = new SubjectCreateServlet();
        field = servlet.getClass().getDeclaredField("subjectDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
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
    void doPostTestAndInsertSubject() throws Exception {
        field.set(servlet ,subjectDao);
        servlet.doPost(request, response);
        verify(subjectDao, atLeastOnce()).insert(subject);
    }

    @Test
    void doGetAndReturnPage() throws Exception {
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).getServletContext();
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        SubjectDao testDao = new SubjectDao(factory);
        servlet.init();
    }
}
