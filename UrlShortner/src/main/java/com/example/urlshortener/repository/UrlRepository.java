package com.example.urlshortener.repository;

import com.example.urlshortener.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    UrlEntity findByOriginalUrl(String originalUrl);
    UrlEntity findByShortUrl(String shortUrl);
    void deleteByExpiryDateBefore(LocalDateTime expiryDate);
}
