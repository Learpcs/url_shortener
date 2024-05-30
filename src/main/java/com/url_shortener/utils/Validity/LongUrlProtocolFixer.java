package com.url_shortener.utils.Validity;

import com.url_shortener.controller.request.UrlDto;
import com.url_shortener.exception.UrlValidationException;

public class LongUrlProtocolFixer {
    public static UrlDto fixUrl(UrlDto urlDto) throws UrlValidationException {
        if (urlDto.url().contains("://"))
            return urlDto;
        else
            return new UrlDto("https:// " + urlDto.url(), urlDto.shortUrl(), urlDto.creatorToken());
    }
}
