package com.url_shortener.utils.Validity;

import com.url_shortener.config.AppConfig;
import com.url_shortener.exception.UrlValidationException;

public class ShortUrlValidator {
    public static void validate(String shortUrl) throws UrlValidationException {
        if (shortUrl.length() != AppConfig.ID_SIZE) {
            throw new UrlValidationException(String.format("shortUrl size is not valid (should be %s): %s", AppConfig.ID_SIZE, shortUrl));
        }
        for (int i = 0; i < shortUrl.length(); ++i) {
            final char ch = shortUrl.charAt(i);
            if (!('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z'))
                throw new UrlValidationException(String.format("shortUrl is not valid (there are invalid characters): %s", AppConfig.ID_SIZE, shortUrl));
        }
    }
}
