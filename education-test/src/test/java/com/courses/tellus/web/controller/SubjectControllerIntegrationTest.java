package com.courses.tellus.web.controller;

import javax.servlet.ServletContext;

import com.courses.tellus.config.spring.mvc.WebAppConfig;
import com.courses.tellus.entity.dto.SubjectDTO;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebAppConfig.class})
class SubjectControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void checkWebAppContextContainsSubjectController() {
        ServletContext servletContext = wac.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("subjectController"));
    }

    @Test
    void subjectListTestAndReturnsJspPageWithSubjects() throws Exception {
        mvc.perform(get("/subject")).andExpect(status().isOk());
    }

    @Test
    void subjectInsertEntityTestAndReturnJspCreatePage() throws Exception {
        mvc.perform(get("/subject/add")).andExpect(status().isOk());
    }

    @Test
    void subjectInsertEntityTestAndSendSubjectData() throws Exception {
        mvc.perform(post("/subject/add")
                .param("name", "Math")
                .param("description", "dfgdf")
                .param("valid", "Y")
                .param("dateOfCreation", "2000-05-12"))
                .andExpect(model().size(1));
    }

    @Test
    void subjectDeleteTestAndReloadPage() throws Exception {
        mvc.perform(get("/subject/delete/1")).andExpect(status().isFound());
    }

    @Test
    @Sql("classpath:initial/h2/table/spring/subject_test_table.sql")
    void subjectEditTestAndReturnJspEditPage() throws Exception {
        mvc.perform(get("/subject/edit/1")).andExpect(status().isOk());
    }

    @Test
    void subjectEditTestAndSendSubjectData() throws Exception {
        SubjectDTO subject = new SubjectDTO("1", "Math", "dfgdf", "Y",
                "2000-05-12");
        mvc.perform(post("/subject/edit")
                .param("subjectId", "1")
                .param("name", "Math")
                .param("description", "dfgdf")
                .param("valid", "Y")
                .param("dateOfCreation", "2000-05-12"))
                .andExpect(model().size(1));
    }
}