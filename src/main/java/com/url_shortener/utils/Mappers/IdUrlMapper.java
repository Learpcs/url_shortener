package com.url_shortener.utils.Mappers;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.exception.ConverterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdUrlMapper {
    final ShortUrlConfig shortUrlConfig;

    public String getShortUrl(Long id) throws ConverterException {
        char[] result = new char[shortUrlConfig.SIZE];
        for (int i = shortUrlConfig.SIZE - 1; i >= 0; --i) {
            result[i] = Base62Mapper.get((int)(id % 62));
            id /= 62;
        }
        return String.valueOf(result);
    }

    public Long getId(String shortUrl) throws ConverterException {
        long id = 0L;
        for (int i = 0; i < shortUrlConfig.SIZE; ++i) {
            id = id * 62 + Base62Mapper.get(shortUrl.charAt(i));
        }
        return id;
    }


}
