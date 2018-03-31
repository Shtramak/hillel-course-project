package com.courses.tellus.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.GregorianCalendar;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class SubjectCreateServletMockTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    private SubjectCreateServlet servlet;
    private Subject subject = new Subject("Math", " fdsd fsd", true,
            new GregorianCalendar(2000, 10, 15));

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new SubjectCreateServlet();
        given(request.getServletContext()).willReturn(servletContext);
    }

    @Test
    void doPostTestAndInsertSubject() throws Exception {
        given(request.getParameter("name")).willReturn(subject.getName());
        given(request.getParameter("description")).willReturn(subject.getDescription());
        given(request.getParameter("activeRadios")).willReturn("Y");
        given(request.getParameter("day")).willReturn("1");
        given(request.getParameter("month")).willReturn("2");
        given(request.getParameter("year")).willReturn("3");
        given(subjectDao.insert(subject)).willReturn(1);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
        servlet.doPost(request, response);
        //verify(subjectDao, atLeastOnce()).insert(subject);
    }

    @Test
    void doPostTestAndThrowsException() throws Exception {
        given(request.getParameter("name")).willReturn(subject.getName());
        given(request.getParameter("description")).willReturn(subject.getDescription());
        given(request.getParameter("activeRadios")).willReturn("Y");
        given(request.getParameter("day")).willReturn("1");
        given(request.getParameter("month")).willReturn("2");
        given(request.getParameter("year")).willReturn("3");
        when(subjectDao.insert(subject)).thenThrow(new DatabaseConnectionException());
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
        //verify(request, atLeastOnce()).getServletContext();
    }

    @Test
    void doGetAndReturnPage() throws Exception {
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
        servlet.doGet(request, response);
    }
}
