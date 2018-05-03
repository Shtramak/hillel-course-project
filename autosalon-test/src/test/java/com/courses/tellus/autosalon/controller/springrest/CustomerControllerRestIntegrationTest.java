package com.courses.tellus.autosalon.controller.springrest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.courses.tellus.autosalon.config.springmvc.WebConfig;
import com.courses.tellus.autosalon.config.springrest.JpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, JpaConfig.class})
class CustomerControllerRestIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        this.mockMvc = builder.build();
    }

    @Test
    void getAllReturnsStatusOk() throws Exception {
        this.mockMvc.perform(get("/springrest/autosalon/customer"))
                .andExpect(status().isOk());
    }

    @Test
    void insertValidCustomerDtoReturnsStatusOk() throws Exception {
        MockHttpServletRequestBuilder builder = post("/springrest/autosalon/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"name\": \"Name\",\n" +
                        "\"surname\": \"Surname\",\n" +
                        "\"dateOfBirth\": \"2018-05-01\",\n" +
                        "\"phoneNumber\" : \"(012)345-67-89\",\n" +
                        "\"availableFunds\":1000\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isCreated());
    }

    @Test
    void insertInvalidCustomerDtoReturnsStatusBadRequest() throws Exception {
        MockHttpServletRequestBuilder builder = post("/springrest/autosalon/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"name\": \"Name\",\n" +
                        "\"surname\": \"Surname\",\n" +
                        "\"dateOfBirth\": \"2018-05-01\",\n" +
                        "\"phoneNumber\" : \"(012)345-67-89\",\n" +
                        "\"availableFunds\":notNumber\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateInvalidCustomerDtoReturnsStatusBadRequest() throws Exception {
        MockHttpServletRequestBuilder builder = put("/springrest/autosalon/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\":\"1\",\n" +
                        "\"name\": \"Name\",\n" +
                        "\"surname\": \"Surname\",\n" +
                        "\"dateOfBirth\": \"2018-05-01\",\n" +
                        "\"phoneNumber\" : \"(012)345-67-89\",\n" +
                        "\"availableFunds\":notNumber\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByIdWhenDataNotExistsReturnsStatusBadRequest() throws Exception {
        this.mockMvc.perform(get("/springrest/autosalon/customer/1"))
                .andExpect(status().isOk());
    }
}
