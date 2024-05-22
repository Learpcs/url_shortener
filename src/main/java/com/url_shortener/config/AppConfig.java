package com.url_shortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${ID_SIZE}") public static final Integer ID_SIZE = -1;
    @Value("${ID_LOWER_BOUND}") public static final Integer ID_LOWER_BOUND = 0;
    @Value("${ID_UPPER_BOUND}") public static final Integer ID_UPPER_BOUND = 916132832; //62 ** 5

    @Value("${URL_SIZE_LOWER_BOUND}") public static final Integer URL_SIZE_LOWER_BOUND = 3;
    @Value("${URL_SIZE_UPPER_BOUND}") public static final Integer URL_SIZE_UPPER_BOUND = 2048;

    @Value("${LOGIN_SIZE_LOWER_BOUND}") public static final Integer LOGIN_SIZE_LOWER_BOUND = 1;
    @Value("${LOGIN_SIZE_UPPER_BOUND}") public static final Integer LOGIN_SIZE_UPPER_BOUND = 36;

    @Value("${PASSWORD_SIZE_LOWER_BOUND}") public static final Integer PASSWORD_SIZE_LOWER_BOUND = 8;
    @Value("${PASSWORD_SIZE_UPPER_BOUND}") public static final Integer PASSWORD_SIZE_UPPER_BOUND = 254;

    @Value("${EMAIL_SIZE_LOWER_BOUND}") public static final Integer EMAIL_SIZE_LOWER_BOUND = 1;
    @Value("${EMAIL_SIZE_UPPER_BOUND}") public static final Integer EMAIL_SIZE_UPPER_BOUND = 254;
}
