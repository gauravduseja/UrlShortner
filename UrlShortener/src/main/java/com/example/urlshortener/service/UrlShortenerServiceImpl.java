package com.example.urlshortener.service;

import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.repository.UrlMappingRepository;
import com.example.urlshortener.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    private ZooKeeperService zooKeeperService;

    @Override
    public String shortenUrl(String originalUrl) throws Exception {
        UrlMapping existingMapping = urlMappingRepository.findByOriginalUrl(originalUrl);
        if (existingMapping != null && !ValidationUtil.isUrlExpired(existingMapping.getExpiryDate())) {
            return existingMapping.getShortUrl();
        }

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(generateShortUrl());
        urlMapping.setCreatedAt(LocalDateTime.now());
        urlMapping.setExpiryDate(LocalDateTime.now().plusMinutes(10));

        try {
            urlMappingRepository.save(urlMapping);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "http://short.url" + urlMapping.getShortUrl();
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        return (urlMapping != null) ? urlMapping.getOriginalUrl() : null;
    }

    private String generateShortUrl() throws Exception {
        return zooKeeperService.generateUniqueId();
    }
}
