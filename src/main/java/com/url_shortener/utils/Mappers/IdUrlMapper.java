package com.url_shortener.utils.Mappers;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.exception.ConverterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdUrlMapper {
    final ShortUrlConfig shortUrlConfig;

    public String map(Long id) throws ConverterException {
        final char[] result = new char[shortUrlConfig.SIZE];
        for (int i = shortUrlConfig.SIZE - 1; 0 <= i; --i) {
            result[i] = Base62Mapper.get((int)(id % 62));
            id /= 62;
        }
        return String.valueOf(result);
    }

    public Long map(final String shortUrl) throws ConverterException {
        long id = 0L;
        for (int i = 0; i < shortUrlConfig.SIZE; ++i) {
            id = id * 62 + Base62Mapper.get(shortUrl.charAt(i));
        }
        return id;
    }


}
