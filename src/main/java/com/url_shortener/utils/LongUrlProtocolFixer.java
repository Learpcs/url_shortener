package com.url_shortener.utils;

import com.url_shortener.controller.Dto.PickedUrlDto;
import org.springframework.stereotype.Component;

@Component
public class LongUrlProtocolFixer {

    public PickedUrlDto fixUrl(final PickedUrlDto urlDto) {
        if (urlDto.longUrl().contains("://"))
            return urlDto;
        else
            return new PickedUrlDto("https://" + urlDto.longUrl(), urlDto.shortUrl());
    }
}
