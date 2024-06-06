package com.example.urlshortener.service;

import com.example.urlshortener.entity.UrlEntity;
import com.example.urlshortener.exception.UrlDoesNotExistException;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private static final String SHORT_URL_PREFIX = "http://short.url/";
    @Value("${expiration.duration.minutes}")
    private int expirationDurationMinutes;
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ZooKeeperService zooKeeperService;

    @Override
    public String shortenUrl(String originalUrl) throws Exception {
        UrlEntity existingMapping = urlRepository.findByOriginalUrl(originalUrl);
        if (existingMapping != null && !ValidationUtil.isUrlExpired(existingMapping.getExpiryDate())) {
            return SHORT_URL_PREFIX + existingMapping.getShortUrl();
        }

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setOriginalUrl(originalUrl);
        urlEntity.setShortUrl(generateShortUrl());
        urlEntity.setCreatedAt(LocalDateTime.now());
        urlEntity.setExpiryDate(LocalDateTime.now().plusMinutes(expirationDurationMinutes));

        try {
            urlRepository.save(urlEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return SHORT_URL_PREFIX + urlEntity.getShortUrl();
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl);
        if (urlEntity == null) {
            throw new UrlDoesNotExistException("BAD_REQUEST",
                    "Original url with given short url :" + shortUrl + " does not exists",
                    HttpStatus.BAD_REQUEST);
        }
        return urlEntity.getOriginalUrl();
    }

    private String generateShortUrl() throws Exception {
        return zooKeeperService.generateUniqueId().replace("/", "");

        
    }
}
