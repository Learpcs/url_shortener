package com.url_shortener.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static String hash(String str) {
        return new BCryptPasswordEncoder().encode(str);
    }
}
