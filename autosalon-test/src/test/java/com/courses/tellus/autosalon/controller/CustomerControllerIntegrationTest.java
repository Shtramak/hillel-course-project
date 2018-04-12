package com.courses.tellus.autosalon.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import com.courses.tellus.autosalon.config.springmvc.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@Sql("classpath:test.sql")
public class CustomerControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @Test
    void checkWebAppContextContainsCustomerController() {
        ServletContext servletContext = wac.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("customerController"));
    }

    @Test
    void customerListReturnsListCustomersJspPage() throws Exception {
        this.mockMvc
                .perform(get("/autosalon/customer/list"))
                .andDo(print())
                .andExpect(view().name("listCustomers"));
    }

    @Sql("classpath:test.sql")
    @Test
    void customerByIdWhenCustomerExistsReturnsCustomerByIdJspPage() throws Exception {
        this.mockMvc
                .perform(get("/autosalon/customer/1"))
                .andDo(print())
                .andExpect(view().name("customerById"));
    }

    @Test
    void customerByIdWhenCustomerNotExistsReturnscustomerNotFoundJspPage() throws Exception {
        this.mockMvc
                .perform(get("/autosalon/customer/123"))
                .andDo(print())
                .andExpect(view().name("customerNotFound"));
    }

    @Test
    void deleteByIdWhenCustomerExistsReturnsCustomerByIdJspPage() throws Exception {
        this.mockMvc
                .perform(get("/autosalon/customer/delete/1"))
                .andDo(print())
                .andExpect(view().name("customerDeletedById"));
    }

    @Test
    void deleteByIdWhenCustomerNotExistsReturnscustomerNotFoundJspPage() throws Exception {
        this.mockMvc
                .perform(get("/autosalon/customer/delete/123"))
                .andDo(print())
                .andExpect(view().name("customerNotFound"));
    }

    @Test
    void addCustomerPageReturnsAddCustomerJspPage() throws Exception {
        this.mockMvc
                .perform(get("/autosalon/customer/add"))
                .andDo(print())
                .andExpect(view().name("addCustomer"));
    }

    @Test
    void addCustomerToBdReturnsSuccessfulAddJspPage() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/autosalon/customer/add")
                .param("id", "2")
                .param("name", "John")
                .param("surname", "Rambo")
                .param("phone", "0123456")
                .param("birthday", "2018-04-12")
                .param("funds", "1000000");
        this.mockMvc.perform(builder)
                .andExpect(view().name("successfulAdd"));
    }
}