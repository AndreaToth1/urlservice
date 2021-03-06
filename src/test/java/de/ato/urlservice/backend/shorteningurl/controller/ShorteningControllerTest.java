package de.ato.urlservice.backend.shorteningurl.controller;

import de.ato.urlservice.backend.BackendApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ShorteningControllerTest {


    private final static String URI = "/shortening";

    @Autowired
    private MockMvc mvc;

    @Test
    void shortenUrl() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(URI)
                        .param("url", "https://www.google.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortenUrl").exists())
                .andReturn();
    }

    @Test
    void shortenUrlInvalid() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(URI)
                        .param("url", "https://www.googl[e.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void redirectUrl() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(URI)
                        .param("shorturl", "https://www.goog.le/-1632447086")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

}