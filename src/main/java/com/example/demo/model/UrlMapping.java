package com.example.demo.model;

public class UrlMapping {
    private String originalUrl;
    private String shortenedUrl;

    public UrlMapping(String originalUrl, String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }
}
