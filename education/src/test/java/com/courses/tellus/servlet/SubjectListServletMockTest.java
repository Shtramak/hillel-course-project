package com.courses.tellus.servlet;

import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.spy;

class SubjectListServletMockTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao subjectDao;
    @InjectMocks private SubjectListServlet servlet;
    private Subject subject = new Subject(1L,"Math", " fdsd fsd", true,
            new GregorianCalendar(2000, 10, 15));

    @BeforeEach
    void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        given(request.getServletContext()).willReturn(servletContext);
    }

    @Test
    void doPostTestAndReturnList() throws Exception {
        List<Subject> list = new ArrayList<>();
        List<Subject> spyList = spy(list);
        spyList.add(subject);

        willReturn(spyList).given(subjectDao).getAll();
        given(spyList.size()).willReturn(1);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
        servlet.doGet(request, response);
    }
}