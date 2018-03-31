package com.courses.tellus.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.GregorianCalendar;
import java.util.Optional;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class SubjectEditServletMockTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    private SubjectEditServlet servlet;
    private Subject subject = new Subject(1L,"Math", " fdsd fsd", true,
            new GregorianCalendar(2000, 10, 15));

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new SubjectEditServlet();
        given(request.getServletContext()).willReturn(servletContext);
    }

    @Test
    void doGetAndGetSubjectParams() throws Exception {
        Subject spy = spy(subject);
        when(request.getParameter("subjectId")).thenReturn("1");
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
        servlet.doGet(request, response);
    }

    @Test
    void doPostTestAndUpdateSubject() throws Exception {
        given(request.getParameter("subjectId")).willReturn("1");
        given(request.getParameter("name")).willReturn(subject.getName());
        given(request.getParameter("description")).willReturn(subject.getDescription());
        given(request.getParameter("activeRadios")).willReturn("Y");
        given(request.getParameter("day")).willReturn("1");
        given(request.getParameter("month")).willReturn("2");
        given(request.getParameter("year")).willReturn("3");
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
        servlet.doPost(request, response);
    }
}