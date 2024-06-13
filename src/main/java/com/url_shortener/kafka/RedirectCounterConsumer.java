package com.url_shortener.kafka;

import com.url_shortener.repository.UrlRepository;
import com.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedirectCounterConsumer {
    private final UrlRepository urlRepository;

    @KafkaListener(topics = "redirect")
    public void listener(Long id) {
        log.debug("GOT IT YAY! {}", id);
        urlRepository.updateUrlAccess(id);
    }
}
