package com.example.demo.controller;

import com.example.demo.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/url")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    // Shorten the URL
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
        String shortenedUrl = urlShortenerService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortenedUrl);  // Return the shortened URL as a response body
    }


    @GetMapping("/redirect/{shortUrl}")
    public String redirectUrl(@PathVariable("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);

        return "redirect:" + originalUrl;
    }




    // Get top 3 domains shortened the most
    @GetMapping("/top-domains")
    public Map<String, Integer> getTopDomains() {
        return urlShortenerService.getTopDomains();
    }
}
