package com.courses.tellus.servlet.subject;

import java.lang.reflect.Field;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.*;

class SubjectListServletMockTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    private SubjectListServlet servlet;
    private Subject subject;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        servlet = new SubjectListServlet();
        subject = new Subject(1L,"Math", " fdsd fsd", true, "2000-10-15");
        MockitoAnnotations.initMocks(this);
        field = servlet.getClass().getDeclaredField("subjectDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        given(request.getServletContext()).willReturn(servletContext);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
    }

    @Test
    void doPostTest() throws Exception {
        field.set(servlet ,subjectDao);
        servlet.doPost(request, response);
        verify(subjectDao, atLeastOnce()).getAll();
    }

    @Test
    void doGetTestAndReturnList() throws Exception {
        field.set(servlet ,subjectDao);
        List<Subject> spyList = spy(new ArrayList<>());
        spyList.add(subject);
        willReturn(spyList).given(subjectDao).getAll();
        willDoNothing().given(request).setAttribute("subjectList", spyList);
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute(eq("subjectList"), any(List.class));
    }

    @Test
    void doGetTestAndReturnError() throws Exception {
        field.set(servlet ,subjectDao);
        List<Subject> spyList = spy(new ArrayList<>());
        willReturn(spyList).given(subjectDao).getAll();
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute(eq("emptydb"), any(String.class));
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        SubjectDao testDao = new SubjectDao(factory);
        servlet.init();
    }
}