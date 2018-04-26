package com.courses.tellus.web.servlet.subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Optional;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.persistence.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
                LocalDate.of(2000,10, 15));
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
        when(subjectDao.getById(anyLong())).thenReturn(Optional.of(subject));
        servlet.doGet(request, response);
        verify(subjectDao, atLeastOnce()).getById(anyLong());
    }

    @Test
    void doPostTestAndUpdateSubject() throws Exception {
        field.set(servlet, subjectDao);
        servlet.doPost(request, response);
        verify(subjectDao, atLeastOnce()).update(subject);
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        SubjectDao testDao = new SubjectDao(factory);
        servlet.init();
    }
}