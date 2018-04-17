package com.courses.tellus.servlet.university;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.model.jdbc.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class GetAllUniversitiesServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private UniversityDao universityDao;
    private GetAllUniversitiesServlet servlet;
    private University university;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        university = new University("Bogomolca","near zoo","medical");
        servlet = new GetAllUniversitiesServlet();
        field = servlet.getClass().getDeclaredField("universityDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        given(request.getServletContext()).willReturn(servletContext);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
    }

    @Test
    void doPostTest() throws Exception {
        field.set(servlet ,universityDao);
        servlet.doPost(request, response);
        verify(universityDao, atLeastOnce()).getAll();
    }

    @Test
    void doGetTestWhenReturnListOfUniversities() throws Exception {
        field.set(servlet ,universityDao);
        List<University> spyList = spy(new ArrayList<>());
        spyList.add(university);
        willReturn(spyList).given(universityDao).getAll();
        willDoNothing().given(request).setAttribute("universityList", spyList);
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute(eq("universityList"), any(List.class));
    }

    @Test
    void doGetTestWhenDataBaseIsEmpty() throws Exception {
        field.set(servlet ,universityDao);
        List<University> spyList = spy(new ArrayList<>());
        willReturn(spyList).given(universityDao).getAll();
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).setAttribute(eq("dbIsEmpty"), any(String.class));
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factoryForTest = mock(ConnectionFactory.class);
        UniversityDao universityDao = new UniversityDao(factoryForTest);
        servlet.init();
    }
}
