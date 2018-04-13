package com.courses.tellus.controller;

import com.courses.tellus.config.spring.mvc.WebConfig;
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

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@Sql("classpath:initial/h2/table/spring/univer_test_table.sql")
public class UniversityControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext);
        this.mockMvc = builder.build();
    }

    @Test
    void checkWebAppContextWithUniversityController() {
        ServletContext servletContext = webContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webContext.getBean("universityController"));
    }

    @Test
    void testGetAllUniversitiesWhenReturnListWithUniversities() throws Exception {
        this.mockMvc
                .perform(get("/getAllUniversities"))
                .andDo(print())
                .andExpect(view().name("listOfUniversities"));
    }


    @Test
    void deleteByIdWhenDeletingIsSuccess() throws Exception {
        this.mockMvc
                .perform(get("/deleteUniversity/1"))
                .andDo(print())
                .andExpect(view().name("redirect:/getAllUniversities"));
    }

    @Test
    void addUniversityMethodGet() throws Exception {
        this.mockMvc
                .perform(get("/addUniversity"))
                .andDo(print())
                .andExpect(view().name("addUniversity"));
    }

    @Test
    void addUniversityMethodPost() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/addUniversity")
                .param("uniId", "1")
                .param("nameOfUniversity", "KpiTest")
                .param("address", "pr.peremohy TEst")
                .param("specialization", "TechnicalTest");
        this.mockMvc.perform(builder)
                .andExpect(view().name("redirect:/getAllUniversities"));
    }
}
