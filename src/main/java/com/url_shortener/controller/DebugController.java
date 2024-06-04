package com.url_shortener.controller;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
public class DebugController {

    private final UserRepository userRepository;
    private final UrlRepository urlRepository;
    private final ShortUrlConfig shortUrlConfig;
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
    @GetMapping("/hello1")
    public ResponseEntity<String> getGreeting() {


        return ResponseEntity.ok("Hello, World!");
    }


    @GetMapping("/secureMethod")
    @PreAuthorize("hasRole('manage-account')")
    public String secureMethod1() {
        return "This method is secured for users with role 'user'";
    }

    @GetMapping("/hello")
    public String hello()
    {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities.toString() + " " + SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/crap")
    public void crap()
    {
        System.out.println(shortUrlConfig.SIZE);
        System.out.println(shortUrlConfig.SIZE_UPPER_BOUND);
    }
}
