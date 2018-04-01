package com.courses.tellus.autosalon.dao.servlets;

import com.courses.tellus.autosalon.config.springjdbc.JdbcTemplatesConfig;
import com.courses.tellus.autosalon.servlets.AllAutosalonServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {JdbcTemplatesConfig.class})
@ExtendWith(SpringExtension.class)
public class AutosalonServletMockTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private AllAutosalonServlet servlet;

    @BeforeEach
    @Sql("classpath:test.sql")
    void setUp() {
        MockitoAnnotations.initMocks(this);
        servlet = new AllAutosalonServlet();
    }

    @Test
    void doGetaddPath() throws Exception {
        when(request.getPathInfo()).thenReturn("/allAutosalon");
        when(request.getRequestDispatcher("/WEB-INF/jsp/allAutosalon.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

//    @Test
//    void doGetListPath() throws Exception {
//        when(request.getPathInfo()).thenReturn("/list");
//        when(request.getRequestDispatcher("/WEB-INF/jsp/listCustomers.jsp")).thenReturn(dispatcher);
//        ArgumentCaptor<List> customersCaptor = ArgumentCaptor.forClass(List.class);
//        servlet.doGet(request, response);
//        verify(request).setAttribute(anyString(), customersCaptor.capture());
//        List<Customer> customers = Collections.singletonList(EXISTED_CUSTOMER);
//        assertEquals(customersCaptor.getValue(), customers);
//        verify(dispatcher).forward(request, response);
//    }

}
