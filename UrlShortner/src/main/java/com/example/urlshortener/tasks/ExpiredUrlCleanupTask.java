package com.example.urlshortener.tasks;

import com.example.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpiredUrlCleanupTask {

    @Autowired
    private UrlRepository urlRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredUrls() {
        urlRepository.deleteByExpiryDateBefore(LocalDateTime.now());
    }
}
