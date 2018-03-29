package com.courses.tellus.servlet;

import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

class SubjectListServletMockTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher rd;
    @Mock private SubjectDao subjectDao;
    private Map attributes;
    private Subject subject = new Subject(1L,"Math", " fdsd fsd", true,
            new GregorianCalendar(2000, 10, 15));
    private String path = "/WEB-INF/jsp/subject_list.jsp";

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.initMocks(this);
        attributes = new HashMap();
    }

    @Test
    void doPostTestAndReturnList() throws Exception {

        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject);
        List<Subject> spyList = spy(subjectList);
        Optional<List<Subject>> opt = Optional.of(spyList);
        Optional<List<Subject>> spyOpt = spy(opt);

        doReturn(true).when(spyOpt).isPresent();
        //doReturn(spyOpt).when(subjectDao).getAll();
        //when(subjectDao.getAll()).thenReturn(spyOpt);
        //when(spyOpt.isPresent()).thenReturn(true);
        //doReturn(subjectList).when(opt).get();
//        doAnswer((invocation) -> {
//            String key = (String) invocation.getArguments()[0];
//            Object value = invocation.getArguments()[1];
//            attributes.put(key, value);
//            return null;
//        }).when(request).setAttribute(anyString(), any());
        when(request.getRequestDispatcher(anyString())).thenReturn(rd);

        new SubjectListServlet().doGet(request, response);
        verify(rd, atLeastOnce()).forward(request, response);
    }
}