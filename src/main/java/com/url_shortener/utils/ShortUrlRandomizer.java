package com.url_shortener.utils;

import com.url_shortener.config.AppConfig;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.utils.Mappers.Base62Mapper;
import com.url_shortener.utils.Mappers.IdUrlMapper;

import java.util.Random;

public class ShortUrlRandomizer {
    public static String randomize() throws ConverterException {
        return IdUrlMapper.getShortUrl(new Random().nextLong(AppConfig.ID_UPPER_BOUND));
    }
}
