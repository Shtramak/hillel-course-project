package com.courses.tellus.autosalon.controller.springrest;

import com.courses.tellus.autosalon.config.springmvc.WebConfig;
import com.courses.tellus.autosalon.config.springrest.JpaConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, JpaConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutosalonIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
        this.mockMvc = builder.build();
    }

    @Test
    public void testCreateAutoSalon() throws Exception {
        MockHttpServletRequestBuilder builder = post("/springrest/autosalon/salon")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"id\" : 1,\n" +
                        "\"name\": \"BMW\",\n" +
                        "\"address\": \"X6\",\n" +
                        "\"telephone\": \"2017\"\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateAutoSalon() throws Exception {
        MockHttpServletRequestBuilder builder = put("/springrest/autosalon/salon")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"id\" : 1,\n" +
                        "\"name\": \"BMW\",\n" +
                        "\"address\": \"X7\",\n" +
                        "\"telephone\": \"2018\"\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetAutosalonById() throws Exception {
        this.mockMvc.perform(get("/springrest/autosalon/salon/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteAutoSalon() throws Exception {
        this.mockMvc.perform(delete("/springrest/autosalon/salon/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}

