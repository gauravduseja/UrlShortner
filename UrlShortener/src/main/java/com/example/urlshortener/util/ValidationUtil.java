package com.example.urlshortener.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValidationUtil {

    public static boolean isUrlExpired(LocalDateTime expiryDateTime) {
        return expiryDateTime.isBefore(LocalDateTime.now());
    }
}
