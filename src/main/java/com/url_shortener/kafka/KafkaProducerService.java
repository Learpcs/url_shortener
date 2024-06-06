package com.url_shortener.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<String, RedirectDto> kafkaTemplate;

    public void sendRequest(final RedirectDto redirectDto) {
        kafkaTemplate.send("redirect", redirectDto);
        log.debug("SENT REDIRECT");
    }
}
