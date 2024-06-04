package com.url_shortener.utils.Validity;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.exception.UrlValidationException;

public class LongUrlProtocolFixer {
    public static RandomUrlDto fixUrl(RandomUrlDto randomUrlDto) throws UrlValidationException {
        if (randomUrlDto.url().contains("://"))
            return randomUrlDto;
        else
            return new RandomUrlDto("https:// " + randomUrlDto.url());
    }

    public static PickedUrlDto fixUrl(PickedUrlDto urlDto) throws UrlValidationException {
        if (urlDto.url().contains("://"))
            return urlDto;
        else
            return new PickedUrlDto("https:// " + urlDto.url(), urlDto.shortUrl());
    }
}
