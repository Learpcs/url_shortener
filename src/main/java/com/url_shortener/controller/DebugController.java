package com.url_shortener.controller;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.config.security.CustomUser;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.utils.Mappers.IdUrlMapper;
//import io.swagger.v3.oas.annotations.Operation;
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
        System.out.println(userRepository.auth(login,  password).orElseThrow(() -> new AuthentificationException("wrong credidentials")));
    }

    @GetMapping("/{link}")
    void redirect(@PathVariable("link") String url) throws AuthentificationException, ConverterException {
        System.out.println(urlRepository.findById(idUrlMapper.getId(url)));
        System.out.println(urlRepository.findById(idUrlMapper.getId(url)).get().getUrl());
    }

//    @Operation(summary = "Get a greeting message")
    @GetMapping("/hello1")
    public ResponseEntity<String> getGreeting() {


        return ResponseEntity.ok("Hello, World!");
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

    @GetMapping("/authentification")
    public String authentification() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @GetMapping("/sessionId")
    public Long sessionId() {
        return ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID();
    }

    @GetMapping("/crap")
    public void crap()
    {
        System.out.println(shortUrlConfig.SIZE);
        System.out.println(shortUrlConfig.SIZE_UPPER_BOUND);
    }
}
