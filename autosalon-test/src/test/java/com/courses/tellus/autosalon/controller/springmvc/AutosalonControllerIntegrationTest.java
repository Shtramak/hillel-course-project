package com.courses.tellus.autosalon.controller.springmvc;

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
public class AutosalonControllerIntegrationTest {

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
    public void testListAutosalon() throws Exception {
        this.mockMvc.perform(get("/springmvc/autosalon/autosalon/allAutosalon"))
                .andExpect(status().isOk())
                .andExpect(view().name("allAutosalon"));
    }

    @Test
    public void testCreateAutosalonGetMethod() throws Exception {
        this.mockMvc.perform(get("/springmvc/autosalon/autosalon/createautosalon"))
                .andExpect(status().isOk())
                .andExpect(view().name("createautosalon"));
    }

    @Test
    public void testCreateAutosalonPostMethod() throws Exception {
        MockHttpServletRequestBuilder builder = post("/springmvc/autosalon/autosalon/createautosalon")
                .param("id", "6")
                .param("name", "Toyota")
                .param("address", "Japan")
                .param("telephone", "2018");
        this.mockMvc.perform(builder)
                .andExpect(view().name("redirect:createautosalon"));
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/springmvc/autosalon/autosalon/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
