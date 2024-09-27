package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlRepository {
    private Map<String, String> urlMap = new HashMap<>();
    private Map<String, Integer> domainCount = new HashMap<>();

    public String saveUrlMapping(String originalUrl, String shortenedUrl) {
        urlMap.put(shortenedUrl, originalUrl);
        incrementDomainCount(originalUrl);
        return shortenedUrl;
    }

    public String getOriginalUrl(String shortenedUrl) {
        return urlMap.get(shortenedUrl);
    }

    public void incrementDomainCount(String originalUrl) {
        String domain = getDomain(originalUrl);

        if (domainCount.containsKey(domain)) {
            domainCount.put(domain, domainCount.get(domain) + 1);
        }
        else{
            domainCount.put(domain, 1);
        }

    }

    public Map<String, Integer> getDomainCount() {
        return domainCount;
    }

    private String getDomain(String url) {
        return url.split("/")[2];  // Simple logic to extract the domain
    }

    public Map<String, String> getUrlMap() {
        return urlMap;
    }
}
