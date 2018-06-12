package com.courses.tellus.web.controller.rest;

import javax.servlet.ServletContext;

import com.courses.tellus.config.spring.mvc.WebAppConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubjectRestControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeAll
    void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void checkWebAppContextContainsSubjectController() {
        ServletContext servletContext = wac.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("subjectRestController"));
    }

    @Test
    void subjectListTestAndReturnsJsonList() throws Exception {
        mvc.perform(get("/rest/subject"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void subjectGetByIdTestAndReturnJsonEntity() throws Exception {
        mvc.perform(get("/rest/subject/4"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void subjectGetByIdTestAndReturnBadRequest() throws Exception {
        mvc.perform(get("/rest/subject/15"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void subjectInsertEntityTestAndSendJsonData() throws Exception {
        mvc.perform(post("/rest/subject")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": \"testIn\",\n" +
                        "  \"description\": \"testIn\",\n" +
                        "  \"valid\": true,\n" +
                        "  \"dateOfCreation\": \"2000-05-12\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void subjectInsertEntityTestAndReturnBadRequest() throws Exception {
        mvc.perform(post("/rest/subject")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void subjectInsertEntityTestAndReturnUnsupportedMediaType() throws Exception {
        mvc.perform(post("/rest/subject"))
                .andExpect(status().isUnsupportedMediaType())
                .andDo(print());
    }

    @Test
    void subjectDeleteTestAndReturnOkStatus() throws Exception {
        mvc.perform(delete("/rest/subject/2"))
                .andExpect(status().isOk());
    }

    @Test
    void subjectDeleteTestAndReturnBadRequest() throws Exception {
        mvc.perform(delete("/rest/subject/20"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void subjectEditTestAndSendJsonData() throws Exception {
        mvc.perform(put("/rest/subject/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": \"testUp\",\n" +
                        "  \"description\": \"testUp\",\n" +
                        "  \"valid\": true,\n" +
                        "  \"dateOfCreation\": \"2000-05-12\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void subjectEditTestAndAndReturnBadRequest() throws Exception {
        mvc.perform(post("/rest/subject")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void subjectEditTestAndAndReturnUnsupportedMediaType() throws Exception {
        mvc.perform(post("/rest/subject"))
                .andExpect(status().isUnsupportedMediaType())
                .andDo(print());
    }
}
