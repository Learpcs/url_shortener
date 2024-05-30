package com.url_shortener.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HostConfig {
    @Value("${settings.HostConfig.PROTOCOL}") public String PROTOCOL;
    @Value("${settings.HostConfig.HOST}") public String HOST;
    @Value("${settings.HostConfig.PORT}") public String PORT;
    @Value("${settings.HostConfig.PROTOCOL}://${settings.HostConfig.HOST}:${settings.HostConfig.PORT}") public String HOST_URL;

}
