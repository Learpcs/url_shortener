package com.url_shortener.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisClearService {

    private final RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0 0 * * * *")
    public void clearRedisData() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().flushAll();
        log.info("Redis data cleared at: " + new Date());
    }
}