package com.url_shortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {
    @Value("${settings.EmailConfig.SIZE_LOWER_BOUND}") public Integer SIZE_LOWER_BOUND;
    @Value("${settings.EmailConfig.SIZE_UPPER_BOUND}") public Integer SIZE_UPPER_BOUND;
}
