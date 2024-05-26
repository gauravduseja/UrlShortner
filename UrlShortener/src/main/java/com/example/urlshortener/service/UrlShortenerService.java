package com.example.urlshortener.service;

import com.example.urlshortener.entity.UrlMapping;

public interface UrlShortenerService {

    String shortenUrl(String originalUrl) throws Exception;

    String getOriginalUrl(String shortUrl);
}
