package com.url_shortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ShortUrlConfig {
    @Value("${settings.ShortUrlConfig.SIZE}") public Integer SIZE;
    @Value("0") public Integer SIZE_LOWER_BOUND;
    @Value("#{T(java.lang.Math).pow(62, ${settings.ShortUrlConfig.SIZE})}") public Integer SIZE_UPPER_BOUND;
}
