package com.url_shortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Value("${constants.ID_SIZE}") public Integer ID_SIZE;
    @Value("${constants.ID_LOWER_BOUND}") public Integer ID_LOWER_BOUND;
    @Value("${constants.ID_UPPER_BOUND}") public Integer ID_UPPER_BOUND;

    @Value("${constants.URL_SIZE_LOWER_BOUND}") public Integer URL_SIZE_LOWER_BOUND;
    @Value("${constants.URL_SIZE_UPPER_BOUND}") public Integer URL_SIZE_UPPER_BOUND;

    @Value("${constants.LOGIN_SIZE_LOWER_BOUND}") public Integer LOGIN_SIZE_LOWER_BOUND;
    @Value("${constants.LOGIN_SIZE_UPPER_BOUND}") public Integer LOGIN_SIZE_UPPER_BOUND;

    @Value("${constants.PASSWORD_SIZE_LOWER_BOUND}") public Integer PASSWORD_SIZE_LOWER_BOUND;
    @Value("${constants.PASSWORD_SIZE_UPPER_BOUND}") public Integer PASSWORD_SIZE_UPPER_BOUND;

    @Value("${constants.EMAIL_SIZE_LOWER_BOUND}") public Integer EMAIL_SIZE_LOWER_BOUND;
    @Value("${constants.EMAIL_SIZE_UPPER_BOUND}") public Integer EMAIL_SIZE_UPPER_BOUND;
}
