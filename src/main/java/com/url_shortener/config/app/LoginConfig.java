package com.url_shortener.config.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoginConfig {
    @Value("${settings.LoginConfig.SIZE_LOWER_BOUND}") public Integer SIZE_LOWER_BOUND;
    @Value("${settings.LoginConfig.SIZE_UPPER_BOUND}") public Integer SIZE_UPPER_BOUND;
}
