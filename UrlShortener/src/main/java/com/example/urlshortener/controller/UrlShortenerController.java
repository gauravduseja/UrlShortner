package com.example.urlshortener.controller;

import com.example.urlshortener.model.OriginalUrlRequestView;
import com.example.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/url/shortener")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody OriginalUrlRequestView requestView) throws Exception {
        String shortUrl = urlShortenerService.shortenUrl(requestView.getOriginalUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable("shortUrl") String shortUrl) {
        String originalUrl = urlShortenerService.getOriginalUrl("/" + shortUrl);

        if (originalUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND).location(java.net.URI.create(originalUrl)).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shortened URL not found");
        }
    }
}
