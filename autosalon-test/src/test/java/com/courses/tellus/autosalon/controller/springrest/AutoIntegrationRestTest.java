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
import org.springframework.test.context.jdbc.Sql;
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
public class AutoIntegrationRestTest {

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
    public void testListAuto() throws Exception {
        this.mockMvc.perform(get("/springrest/autosalon/auto"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testCreateAuto() throws Exception {
        MockHttpServletRequestBuilder builder = post("/springrest/autosalon/auto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"brand\": \"BMW\",\n" +
                        "\"model\": \"X6\",\n" +
                        "\"manufactYear\": 2017,\n" +
                        "\"producerCountry\" : \"Germany\",\n" +
                        "\"price\":201888\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateAuto() throws Exception {
        MockHttpServletRequestBuilder builder = put("/springrest/autosalon/auto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"id\":\"1\",\n" +
                        "\"brand\": \"BMW\",\n" +
                        "\"model\": \"X8\",\n" +
                        "\"manufactYear\": \"2017\",\n" +
                        "\"producerCountry\" : \"Germany\",\n" +
                        "\"price\":\"201888\"\n" +
                        "}");
        this.mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetAutoById() throws Exception {
        this.mockMvc.perform(get("/springrest/autosalon/auto/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteAutoById() throws Exception {
        this.mockMvc.perform(delete("/springrest/autosalon/auto/1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
