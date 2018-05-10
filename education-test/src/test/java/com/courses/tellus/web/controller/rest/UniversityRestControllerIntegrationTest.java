package com.courses.tellus.web.controller.rest;

import javax.servlet.ServletContext;

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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, RepoConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UniversityRestControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeAll
    void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void checkWebAppContextContainsUniversityController() {
        ServletContext servletContext = wac.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(wac.getBean("universityRestController"));
    }

    @Test
    void universityListTestWhenReturnsJsonList() throws Exception {
        mvc.perform(get("/rest/university"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void universityGetByIdTestWhenReturnJsonEntity() throws Exception {
        mvc.perform(get("/rest/university/4"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void universityGetByIdTestWhenReturnBadRequest() throws Exception {
        mvc.perform(get("/rest/university/15"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void universityInsertTestWhenSendJsonData() throws Exception {
        mvc.perform(post("/rest/university")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"nameOfUniversity\": \"testN\",\n" +
                        "  \"address\": \"testA\",\n" +
                        "  \"specialization\": \"testS\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void universityInsertTestWhenReturnBadRequest() throws Exception {
        mvc.perform(post("/rest/university")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void universityInsertTestWhenReturnUnsupportedMediaType() throws Exception {
        mvc.perform(post("/rest/university"))
                .andExpect(status().isUnsupportedMediaType())
                .andDo(print());
    }

    @Test
    void universityDeleteTestWhenReturnOkStatus() throws Exception {
        mvc.perform(delete("/rest/university/2"))
                .andExpect(status().isOk());
    }

    @Test
    void universityDeleteTestWhenReturnBadRequest() throws Exception {
        mvc.perform(delete("/rest/university/20"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void universityUpdateTestWhenSendJsonData() throws Exception {
        mvc.perform(put("/rest/university/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"nameOfUniversity\": \"testNU\",\n" +
                        "  \"address\": \"testAU\",\n" +
                        "  \"specialization\": \"testSU\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void universityUpdateTestWhenReturnBadRequest() throws Exception {
        mvc.perform(post("/rest/university")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void universityUpdateTestWhenReturnUnsupportedMediaType() throws Exception {
        mvc.perform(post("/rest/university"))
                .andExpect(status().isUnsupportedMediaType())
                .andDo(print());
    }
}
