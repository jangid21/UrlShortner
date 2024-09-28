URL Shortener Service
This project implements a simple URL shortener service using Java Spring Boot. The service accepts a URL via a REST API and returns a shortened version of the URL. The original URL can be retrieved using the shortened one, and the top 3 most-shortened domain names are tracked.

Features
URL Shortening:

Accepts a URL and returns a shortened version.
If the same URL is provided again, the service returns the same shortened URL.
Redirection:

Given a shortened URL, the service redirects the user to the original URL.
Metrics API:

Provides the top 3 domains that have been shortened the most.
API Endpoints
1. Shorten URL
   POST /api/v1/url/shorten

Request Body:

json
Copy code
{
"url": "https://example.com"
}
Response:

json
Copy code
{
"shortenedUrl": "http://localhost:8080/{shortenedUrl}"
}

2. Redirect to Original URL
   GET /api/v1/url/redirect/{shortenedUrl}

Description: Redirects to the original URL.
3. Get Top 3 Shortened Domains
   GET /api/v1/url/top-domains

Response:
json
Copy code
{
"domains": [
{"domain": "example.com", "count": 10},
{"domain": "another.com", "count": 7},
{"domain": "third.com", "count": 5}
]
}
Running the Application with Docker
Build Docker Image:

bash
Copy code
docker build -t url-shortener .
Run the Docker Container:

bash
Copy code
docker run -p 8080:8080 url-shortener
Example Usage
1. Shorten a URL:
   bash
   Copy code
   curl -X POST http://localhost:8080/api/v1/url/shorten -d "https://example.com"
2. Redirect to Original URL:
   bash
   Copy code
   curl http://localhost:8080/api/v1/url/redirect/{shortenedUrl}
3. Get Top 3 Shortened Domains:
   bash
   Copy code
   curl http://localhost:8080/api/v1/url/top-domains