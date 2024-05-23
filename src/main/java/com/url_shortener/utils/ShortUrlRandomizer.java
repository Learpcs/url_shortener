package com.url_shortener.utils;

import com.url_shortener.config.AppConfig;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.utils.Mappers.Base62Mapper;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ShortUrlRandomizer {
    private final AppConfig appConfig;
    private final IdUrlMapper idUrlMapper;

    public String randomize() throws ConverterException {
        return idUrlMapper.getShortUrl(new Random().nextLong(appConfig.ID_UPPER_BOUND));
    }
}
