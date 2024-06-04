package com.url_shortener.utils.Validity;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.exception.UrlValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlFixer {
    private final ShortUrlValidator shortUrlValidator;
    private final LongUrlValidator longUrlValidator;

    public RandomUrlDto fix(RandomUrlDto randomUrlDto) throws UrlValidationException {
        randomUrlDto = LongUrlProtocolFixer.fixUrl(randomUrlDto);
        longUrlValidator.validate(randomUrlDto.url());
        return randomUrlDto;
    }

    public PickedUrlDto fix(PickedUrlDto pickedUrlDto) throws UrlValidationException {
        pickedUrlDto = LongUrlProtocolFixer.fixUrl(pickedUrlDto);
        shortUrlValidator.validate(pickedUrlDto.shortUrl());
        longUrlValidator.validate(pickedUrlDto.url());
        return pickedUrlDto;
    }
}
