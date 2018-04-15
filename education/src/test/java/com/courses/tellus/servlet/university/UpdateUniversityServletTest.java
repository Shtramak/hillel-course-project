package com.courses.tellus.servlet.university;

import com.courses.tellus.connection.jdbc.ConnectionFactory;
import com.courses.tellus.dao.jdbc.UniversityDao;
import com.courses.tellus.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class UpdateUniversityServletTest {
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private UniversityDao universityDao;
    private UpdateUniversityServlet servlet;
    private University university;
    private Field field;

    @BeforeEach
    void initMocks() throws Exception {
        university = new University(1L,"Bogomolca","near zoo","medical");
        servlet = new UpdateUniversityServlet();
        field = servlet.getClass().getDeclaredField("universityDao");
        field.setAccessible(true);
        MockitoAnnotations.initMocks(this);
        given(request.getParameter("uniId")).willReturn("1");
        given(request.getParameter("nameOfUniversity")).willReturn(university.getNameOfUniversity());
        given(request.getParameter("address")).willReturn(university.getAddress());
        given(request.getParameter("specialization")).willReturn(university.getSpecialization());
        given(request.getServletContext()).willReturn(servletContext);
        given(servletContext.getRequestDispatcher(anyString())).willReturn(dispatcher);
    }
    @Test
    void doGetTestWhenGetUniversitiesParameters() throws Exception {
        field.set(servlet, universityDao);
        when(request.getParameter("uniId")).thenReturn("1");
        when(universityDao.getById(anyLong())).thenReturn(Optional.of(university));
        servlet.doGet(request, response);
        verify(universityDao, atLeastOnce()).getById(anyLong());
    }

    @Test
    void doPostTestWhenUniversityUpdated() throws Exception {
        field.set(servlet, universityDao);
        servlet.doPost(request, response);
        verify(universityDao, atLeastOnce()).update(university);
    }

    @Test
    void initMethodTest() throws Exception {
        ConnectionFactory factoryForTest = mock(ConnectionFactory.class);
        UniversityDao universityDao = new UniversityDao(factoryForTest);
        servlet.init();
    }
}
