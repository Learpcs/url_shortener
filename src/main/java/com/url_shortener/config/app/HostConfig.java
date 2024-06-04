package com.url_shortener.config.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HostConfig {
    @Value("${settings.HostConfig.PROTOCOL}") public String PROTOCOL;
    @Value("${settings.HostConfig.HOST}") public String HOST;
    @Value("${server.port}") public String PORT;
    @Value("${settings.HostConfig.PROTOCOL}://${settings.HostConfig.HOST}:${server.port}") public String HOST_URL;

}
