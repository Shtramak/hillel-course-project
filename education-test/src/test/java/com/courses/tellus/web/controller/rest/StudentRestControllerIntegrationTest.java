package com.courses.tellus.web.controller.rest;

import com.courses.tellus.config.spring.mvc.WebAppConfig;
import com.courses.tellus.config.spring.rest.RepoConfig;
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

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, RepoConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentRestControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeAll
    void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void checkWebAppContextContainsStudentController() {
        ServletContext servletContext = wac.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("studentRestController"));
    }

    @Test
    void studentListTestWhenReturnsJsonList() throws Exception {
        mvc.perform(get("/rest/student"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void studentGetByIdTestWhenReturnJsonEntity() throws Exception {
        mvc.perform(get("/rest/student/4"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void studentGetByIdTestWhenReturnBadRequest() throws Exception {
        mvc.perform(get("/rest/student/15"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void studentInsertTestWhenSendJsonData() throws Exception {
        mvc.perform(post("/rest/student")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"firstName\": \"testIn\",\n" +
                        "  \"lastName\": \"testIn\",\n" +
                        "  \"studentCardNumber\": \"testIn\",\n" +
                        "  \"address\": \"testIn\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void studentInsertTestWhenReturnBadRequest() throws Exception {
        mvc.perform(post("/rest/student")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void studentInsertTestWhenReturnUnsupportedMediaType() throws Exception {
        mvc.perform(post("/rest/student"))
                .andExpect(status().isUnsupportedMediaType())
                .andDo(print());
    }

    @Test
    void studentDeleteTestWhenReturnOkStatus() throws Exception {
        mvc.perform(delete("/rest/student/2"))
                .andExpect(status().isOk());
    }

    @Test
    void studentDeleteTestWhenReturnBadRequest() throws Exception {
        mvc.perform(delete("/rest/student/20"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void studentUpdateTestWhenSendJsonData() throws Exception {
        mvc.perform(put("/rest/student/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"firstName\": \"testIn\",\n" +
                        "  \"lastName\": \"testIn\",\n" +
                        "  \"studentCardNumber\": \"testIn\",\n" +
                        "  \"address\": \"testIn\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void studentUpdateTestWhenReturnBadRequest() throws Exception {
        mvc.perform(post("/rest/student")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void studentUpdateTestWhenReturnUnsupportedMediaType() throws Exception {
        mvc.perform(post("/rest/student"))
                .andExpect(status().isUnsupportedMediaType())
                .andDo(print());
    }
}
