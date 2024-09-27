# URL Shortener Service

This project implements a simple URL shortener service using **Java Spring Boot**. The service accepts a URL via a REST API and returns a shortened version of the URL. The original URL can be retrieved using the shortened one, and the top 3 most-shortened domain names are tracked.

## Features

1. **URL Shortening**:
    - Accepts a URL and returns a shortened version.
    - If the same URL is provided again, the service returns the same shortened URL.

2. **Redirection**:
    - Given a shortened URL, the service redirects the user to the original URL.

3. **Metrics API**:
    - Provides the top 3 domains that have been shortened the most.

## Endpoints

### 1. Shorten URL
**POST** `/api/v1/url/shorten`

Request Body:
```json
"https://example.com"


#### 2. Redirect to Original URL
GET /api/v1/url/redirect/{shortenedUrl}

#### 2. Get Top 3 Shortened Domains
GET /api/v1/url/top-domains


# Building and Running Docker Image
docker build -t url-shortener .

docker run -p 8080:8080 url-shortener

# Building and Running Docker Image
curl -X POST http://localhost:8080/api/v1/url/shorten -d "https://example.com"

# Redirect URL:
curl http://localhost:8080/api/v1/url/redirect/{shortenedUrl}

# Redirect URL:
curl http://localhost:8080/api/v1/url/top-domains
# UrlShortner
