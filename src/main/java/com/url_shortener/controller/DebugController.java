package com.url_shortener.controller;

import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/debug")
public class DebugController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/auth")
    void auth(@RequestParam String login, @RequestParam String password) throws AuthentificationException {
        System.out.println(userRepository.Authentificate(login,  password).orElseThrow(() -> new AuthentificationException("wrong credidentials")));
    }

    @GetMapping("/redirect")
    void redirect(@RequestParam String url) throws AuthentificationException, ConverterException {
        System.out.println(urlRepository.findById(IdUrlMapper.getId(url)));
        System.out.println(urlRepository.findById(IdUrlMapper.getId(url)).get().getUrl());
    }
}
