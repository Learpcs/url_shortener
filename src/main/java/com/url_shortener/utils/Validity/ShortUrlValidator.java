package com.url_shortener.utils.Validity;

import com.url_shortener.config.AppConfig;
import com.url_shortener.exception.UrlValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShortUrlValidator {
    final AppConfig appConfig;

    public void validate(String shortUrl) throws UrlValidationException {
        if (shortUrl.length() != appConfig.ID_SIZE) {
            throw new UrlValidationException(String.format("shortUrl size is not valid (should be %s): %s", appConfig.ID_SIZE, shortUrl));
        }
        for (int i = 0; i < shortUrl.length(); ++i) {
            final char ch = shortUrl.charAt(i);
            if (!('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z'))
                throw new UrlValidationException(String.format("shortUrl is not valid (there are invalid characters): %s", appConfig.ID_SIZE, shortUrl));
        }
    }
}
