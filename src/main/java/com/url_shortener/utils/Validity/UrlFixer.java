package com.url_shortener.utils.Validity;

import com.url_shortener.controller.request.UrlDto;
import com.url_shortener.exception.UrlValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlFixer {
    private final ShortUrlValidator shortUrlValidator;
    private final LongUrlValidator longUrlValidator;

    public UrlDto fix(UrlDto urlDto) throws UrlValidationException {
        urlDto = ProtocolFixer.fixUrl(urlDto);
        shortUrlValidator.validate(urlDto.shortUrl());
        longUrlValidator.validate(urlDto.url());
        return urlDto;
    }
}
