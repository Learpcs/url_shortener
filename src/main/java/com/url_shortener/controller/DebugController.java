package com.url_shortener.controller;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.config.security.CustomUser;
import com.url_shortener.config.security.WebSecurityConfig;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.kafka.KafkaProducerService;
import com.url_shortener.kafka.RedirectDto;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class DebugController {

    private final UrlRepository urlRepository;
    private final ShortUrlConfig shortUrlConfig;
    private final IdUrlMapper idUrlMapper;
    private final WebSecurityConfig webSecurityConfig;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/{link}")
    void redirect(@PathVariable("link") final String url) throws ConverterException {
        System.out.println(urlRepository.findById(idUrlMapper.map(url)));
        System.out.println(urlRepository.findById(idUrlMapper.map(url)).orElseThrow().getLongUrl());
    }

    @GetMapping("/hello1")
    public ResponseEntity<String> getGreeting() {


        return ResponseEntity.ok("Hello, World!");
    }

    @GetMapping("/getPasswords")
    public String getPasswords() {
        return webSecurityConfig.passwordEncoder().encode("user")
                + "\n" + webSecurityConfig.passwordEncoder().encode("premium")
                + "\n" + webSecurityConfig.passwordEncoder().encode("admin");
    }

    @GetMapping("/secureMethodUser")
    @PreAuthorize("hasRole('USER')")
    public String secureMethodUser() {
        return "This method is secured for users with role 'USER'";
    }

    @GetMapping("/secureMethodPremium")
    @PreAuthorize("hasRole('PREMIUM')")
    public String secureMethodPremium() {
        return "This method is secured for users with role 'PREMIUM'";
    }

    @GetMapping("/secureMethodAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String secureMethodAdmin() {
        return "This method is secured for users with role 'ADMIN'";
    }

    @GetMapping("/authentication")
    public String authentication() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @GetMapping("/sessionId")
    public Long sessionId() {
        return ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID();
    }

    @GetMapping("/getAllLinks")
    public List<UrlDao> getAllLinks() {
        return urlRepository.findAll();
    }

    @GetMapping("/kafka")
    public void kafka() {
        kafkaProducerService.sendRequest(new RedirectDto(0L));
    }

    @GetMapping("/crap")
    public void crap()
    {
        System.out.println(shortUrlConfig.SIZE);
        System.out.println(shortUrlConfig.SIZE_UPPER_BOUND);
    }
}
