package com.url_shortener.scheduled;

import com.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class DatabaseClearService {
    private final UrlService urlService;

    @Scheduled(cron = "0 0 0 * * 0")
    public void databaseClear() {
        urlService.deleteUrlsOlderThanXMinutes(60L * 24L * 30);
        log.info("Url data cleared at: {}", new Date());
    }
}
