package com.courses.tellus.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.tellus.dao.jdbc.SubjectDao;
import com.courses.tellus.exception.jdbc.DatabaseConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class SubjectDeleteServletMockTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher dispatcher;
    @Mock private ServletContext servletContext;
    @Mock private SubjectDao basicDao;
    @InjectMocks private SubjectDeleteServlet servlet;

    @BeforeEach
    void initMockito() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    void successFullDeleteOperation() throws Exception {
        doReturn("1").when(request).getParameter("subjectId");
        when(basicDao.delete(anyLong())).thenReturn(1);
        servlet.doGet(request, response);

        verify(basicDao, atLeastOnce()).delete(anyLong());
        verify(request, atLeast(1)).getServletContext();
    }

    @Test
    void failedDeleteOperation() throws Exception {
        doReturn("1").when(request).getParameter("subjectId");
        when(basicDao.delete(anyLong())).thenThrow(new DatabaseConnectionException());
        servlet.doGet(request, response);

        verify(request,atLeastOnce()).setAttribute(anyString(), any());
        verify(request, atLeast(1)).getServletContext();

    }
}
