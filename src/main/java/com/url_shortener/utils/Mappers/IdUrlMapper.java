package com.url_shortener.utils.Mappers;

import com.url_shortener.config.AppConfig;
import com.url_shortener.exception.ConverterException;

public class IdUrlMapper {
    public static String getShortUrl(Long id) throws ConverterException {
        char[] result = new char[AppConfig.ID_SIZE];
        for (int i = 0; i < AppConfig.ID_SIZE; ++i) {
            result[i] = Base62Mapper.get((int)(id % 62));
            id /= 62;
        }
        return String.valueOf(result);
    }

    public static Long getId(String shortUrl) throws ConverterException {
        long id = 0L;
        for (int i = 0; i < AppConfig.ID_SIZE; ++i) {
            id = id * 62 + Base62Mapper.get(shortUrl.charAt(i));
        }
        return id;
    }
}
