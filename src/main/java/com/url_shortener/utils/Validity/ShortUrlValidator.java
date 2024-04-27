package com.url_shortener.utils.Validity;

import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.utils.Constants;

public class ShortUrlValidator {
    public static void validate(String shortUrl) throws UrlValidationException {
        if (shortUrl.length() != Constants.ID_SIZE) {
            throw new UrlValidationException(String.format("shortUrl size is not valid (should be %s): %s", Constants.ID_SIZE, shortUrl));
        }
        for (int i = 0; i < shortUrl.length(); ++i) {
            final char ch = shortUrl.charAt(i);
            if (!('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z'))
                throw new UrlValidationException(String.format("shortUrl is not valid (there are invalid characters): %s", Constants.ID_SIZE, shortUrl));
        }
    }
}
