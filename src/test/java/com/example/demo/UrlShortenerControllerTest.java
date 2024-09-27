package com.example.demo;

import com.example.demo.controller.UrlShortenerController;
import com.example.demo.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlShortenerController.class)
class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @Test
    public void testShortenUrl() throws Exception {
        String originalUrl = "https://example.com";
        String shortenedUrl = "abc123";

        when(urlShortenerService.shortenUrl(originalUrl)).thenReturn(shortenedUrl);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/url/shorten")
                        .content(originalUrl)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(shortenedUrl));  // Expect the correct shortened URL in the response body
    }



    @Test
    void testGetTopDomains() throws Exception {
        String responseJson = "{\"udemy.com\": 6, \"youtube.com\": 4, \"example.com\": 2}";

        when(urlShortenerService.getTopDomains()).thenReturn(Map.of("udemy.com", 6, "youtube.com", 4, "example.com", 2));

        mockMvc.perform(get("/api/v1/url/top-domains"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }
}
