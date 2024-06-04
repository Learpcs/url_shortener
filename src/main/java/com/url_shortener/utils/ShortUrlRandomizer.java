package com.url_shortener.utils;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ShortUrlRandomizer {
    private final ShortUrlConfig shortUrlConfig;
    private final IdUrlMapper idUrlMapper;

    public String randomize() throws ConverterException {
        return idUrlMapper.getShortUrl(new Random().nextLong(shortUrlConfig.SIZE_UPPER_BOUND));
    }
}
