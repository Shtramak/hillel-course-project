package com.courses.tellus.web.servlet.university;

import com.courses.tellus.config.jdbc.ConnectionFactory;
import com.courses.tellus.persistence.dao.jdbc.UniversityDao;
import com.courses.tellus.entity.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CreateUniversityServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private UniversityDao universityDao;
    private CreateUniversityServlet servlet;
    private University university;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        university = new University("Bogomolca","near zoo","medical");
        servlet = new CreateUniversityServlet();
        field = servlet.getClass().getDeclaredField("universityDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        given(request.getParameter("nameOfUniversity")).willReturn(university.getNameOfUniversity());
        given(request.getParameter("address")).willReturn(university.getAddress());
        given(request.getParameter("specialization")).willReturn(university.getSpecialization());
        given(request.getServletContext()).willReturn(servletContext);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
    }

    @Test
    void doPostTestWhenInsertUniversity() throws Exception {
        field.set(servlet , universityDao);
        servlet.doPost(request, response);
        verify(universityDao, atLeastOnce()).insert(university);
    }

    @Test
    void doGetWhenReturnPage() throws Exception {
        servlet.doGet(request, response);
        verify(request, atLeastOnce()).getServletContext();
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factoryForTest = mock(ConnectionFactory.class);
        UniversityDao universityDao = new UniversityDao(factoryForTest);
        servlet.init();
    }
}