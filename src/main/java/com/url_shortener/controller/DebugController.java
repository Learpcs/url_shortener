package com.url_shortener.controller;

import com.url_shortener.config.AppConfig;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
public class DebugController {

    private final UserRepository userRepository;
    private final UrlRepository urlRepository;
    private final AppConfig appConfig;
    private final IdUrlMapper idUrlMapper;

    @GetMapping("/auth")
    void auth(@RequestParam String login, @RequestParam String password) throws AuthentificationException {
        System.out.println(userRepository.Authentificate(login,  password).orElseThrow(() -> new AuthentificationException("wrong credidentials")));
    }

    @GetMapping("/{link}")
    void redirect(@PathVariable("link") String url) throws AuthentificationException, ConverterException {
        System.out.println(urlRepository.findById(idUrlMapper.getId(url)));
        System.out.println(urlRepository.findById(idUrlMapper.getId(url)).get().getUrl());
    }

    @Operation(summary = "Get a greeting message")
    @GetMapping("/hello")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello, World!");
    }

    @GetMapping("/crap")
    public void crap() {
        System.out.println(appConfig.ID_SIZE);
    }
}
