package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.*;
import java.util.stream.Collectors;
import com.example.demo.repository.UrlRepository;
import com.example.demo.exception.UrlAlreadyExistsException;
import com.example.demo.exception.HashGenerationException;

@Service
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Generate a shortened URL using Base64 encoding
    public String shortenUrl(String originalUrl) {
        // Check if the URL is already shortened
        String shortenedUrl = urlRepository.getUrlMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(originalUrl))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (shortenedUrl != null) {
            // URL already shortened, increment the count and return the existing shortened URL
            urlRepository.incrementDomainCount(originalUrl);
            return shortenedUrl;
        }

        // Generate a new shortened URL using MD5 hashing and Base64 encoding
         shortenedUrl = generateShortUrl(originalUrl);

        // Ensure that the generated shortened URL is unique
        while (urlRepository.getUrlMap().containsKey(shortenedUrl)) {
            shortenedUrl = generateShortUrl(originalUrl + UUID.randomUUID().toString());
        }

        urlRepository.saveUrlMapping(originalUrl, shortenedUrl);

        return shortenedUrl;
    }

    private String generateShortUrl(String originalUrl) {
        try {
            // Create an MD5 hash of the URL
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(originalUrl.getBytes());

            // Encode the hash into Base64 and take the first 6 characters for the shortened URL
            return Base64.getUrlEncoder().encodeToString(hashBytes).substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            throw new HashGenerationException("Error generating hash for URL", e);
        }
    }

    // Redirect to the original URL
    public String getOriginalUrl(String shortenedUrl) {
        return urlRepository.getOriginalUrl(shortenedUrl);
    }

    // Get top 3 domains shortened the most
    public Map<String, Integer> getTopDomains() {
        return urlRepository.getDomainCount().entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
