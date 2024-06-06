package com.url_shortener.utils;

import com.url_shortener.config.app.ShortUrlConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ShortUrlRandomizer {
    private final ShortUrlConfig shortUrlConfig;

    public Long randomId() {
        return new Random().nextLong(shortUrlConfig.SIZE_UPPER_BOUND);
    }
}
