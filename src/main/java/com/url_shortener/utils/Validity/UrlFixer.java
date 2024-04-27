package com.url_shortener.utils.Validity;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.exception.UrlValidationException;

public class UrlFixer {
    public static UrlDto fix(UrlDto urlDto) throws UrlValidationException {
        urlDto = ProtocolFixer.fixUrl(urlDto);
        ShortUrlValidator.validate(urlDto.shortUrl());
        LongUrlValidator.validate(urlDto.url());
        return urlDto;
    }
}
