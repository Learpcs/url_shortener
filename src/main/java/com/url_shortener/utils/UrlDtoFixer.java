package com.url_shortener.utils;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.ResourceValidationException;
import com.url_shortener.utils.Validity.LongUrlValidator;
import com.url_shortener.utils.Validity.ShortUrlValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlDtoFixer {
    private final ShortUrlValidator shortUrlValidator;
    private final LongUrlValidator longUrlValidator;
    private final LongUrlProtocolFixer longUrlProtocolFixer;

    public PickedUrlDto fix(PickedUrlDto pickedUrlDto) throws ResourceValidationException, ConverterException {
        pickedUrlDto = longUrlProtocolFixer.fixUrl(pickedUrlDto);
        shortUrlValidator.validate(pickedUrlDto.shortUrl());
        longUrlValidator.validate(pickedUrlDto.longUrl());
        return pickedUrlDto;
    }
}
