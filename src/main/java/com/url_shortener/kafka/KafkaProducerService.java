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
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendRequest(final String id) {
        log.debug("SENDING REDIRECT: {}", id);
        kafkaTemplate.send("redirect", id);
        log.debug("SENT REDIRECT: {}", id);
    }
}
