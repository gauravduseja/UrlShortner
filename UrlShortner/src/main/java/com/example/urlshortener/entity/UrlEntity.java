package com.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_mapping", indexes = {@Index(name = "idx_shortUrl", columnList = "shortUrl")})
public class UrlEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        @Getter
        @Setter
        private String originalUrl;

        @Column(nullable = false)
        @Getter
        @Setter
        private String shortUrl;

        @Column(nullable = false)
        @Getter
        @Setter
        private LocalDateTime createdAt;

        @Column(nullable = true)
        @Getter
        @Setter
        private LocalDateTime expiryDate;

    }
