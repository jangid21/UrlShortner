package com.example.demo;

import com.example.demo.repository.UrlRepository;
import com.example.demo.service.UrlShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

class UrlShortenerServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShortenUrl_existingUrl() {
        String originalUrl = "https://example.com";
        String shortenedUrl = "abc123";

        Map<String, String> urlMap = new HashMap<>();
        urlMap.put(shortenedUrl, originalUrl);
        when(urlRepository.getUrlMap()).thenReturn(urlMap);

        String result = urlShortenerService.shortenUrl(originalUrl);
        assertEquals(shortenedUrl, result);
    }

    @Test
    void testShortenUrl_newUrl() {
        String originalUrl = "https://example.com";
        String shortenedUrl = "abc123";

        when(urlRepository.getUrlMap()).thenReturn(new HashMap<>());
        when(urlRepository.saveUrlMapping(anyString(), anyString())).thenReturn(shortenedUrl);

        String result = urlShortenerService.shortenUrl(originalUrl);
        assertNotNull(result);
    }

    @Test
    void testGetOriginalUrl() {
        String originalUrl = "https://example.com";
        String shortenedUrl = "abc123";

        when(urlRepository.getOriginalUrl(shortenedUrl)).thenReturn(originalUrl);

        String result = urlShortenerService.getOriginalUrl(shortenedUrl);
        assertEquals(originalUrl, result);
    }

    @Test
    void testGetTopDomains() {
        Map<String, Integer> domainCount = new HashMap<>();
        domainCount.put("example.com", 5);
        domainCount.put("udemy.com", 6);
        domainCount.put("youtube.com", 4);

        when(urlRepository.getDomainCount()).thenReturn(domainCount);

        Map<String, Integer> result = urlShortenerService.getTopDomains();
        assertEquals(3, result.size());
        assertEquals(6, result.get("udemy.com"));
    }
}
