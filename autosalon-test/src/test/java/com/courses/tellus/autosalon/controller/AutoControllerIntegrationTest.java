package com.courses.tellus.autosalon.controller;

import com.courses.tellus.autosalon.config.springmvc.WebConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutoControllerIntegrationTest {

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
        this.mockMvc.perform(get("/autosalon/auto/listAuto"))
                .andExpect(status().isOk())
                .andExpect(view().name("listAuto"));
    }

    @Test
    public void testCreateAutoGetMethod() throws Exception {
        this.mockMvc.perform(get("/autosalon/auto/createAuto"))
                .andExpect(status().isOk())
                .andExpect(view().name("createAuto"));
    }

    @Test
    public void testCreateAutoPostMethod() throws Exception {
        MockHttpServletRequestBuilder builder = post("/autosalon/auto/createAuto")
                .param("id", "6")
                .param("brand", "Toyota")
                .param("model", "Camry")
                .param("manufactYear", "2018")
                .param("producerCountry", "Japan")
                .param("price", "300000");
        this.mockMvc.perform(builder)
                .andExpect(view().name("redirect:listAuto"));
    }

    @Test
    public void testGetAutoById() throws Exception {
        this.mockMvc.perform(get("/autosalon/auto/idAuto/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("showAuto"));
    }

    @Test
    public void testUpdateAutoGetMethod() throws Exception {
        this.mockMvc.perform(get("/autosalon/auto/update/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("editAuto"));
    }

    @Test
    public void testUpdateAutoPostMethod() throws Exception {
        MockHttpServletRequestBuilder builder = post("/autosalon/auto/updateAuto")
                .param("id", "6")
                .param("brand", "Toyota")
                .param("model", "Camry")
                .param("manufactYear", "2018")
                .param("producerCountry", "Japan")
                .param("price", "300000");
        this.mockMvc.perform(builder)
                .andExpect(view().name("redirect:listAuto"));
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/autosalon/auto/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}