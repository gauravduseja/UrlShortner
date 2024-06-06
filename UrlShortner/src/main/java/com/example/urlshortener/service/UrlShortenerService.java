package com.example.urlshortener.service;

public interface UrlShortenerService {

    String shortenUrl(String originalUrl) throws Exception;

    String getOriginalUrl(String shortUrl);
}
