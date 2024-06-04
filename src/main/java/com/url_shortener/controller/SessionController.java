package com.url_shortener.controller;

import com.url_shortener.config.app.ShortUrlConfig;
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
@RequestMapping("/api/v1/debug/session")
@RequiredArgsConstructor
public class SessionController {

    @GetMapping("/authorities")
    public String authorities()
    {
        return ((Collection<SimpleGrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities())).toString();
    }

    @GetMapping("/authentification")
    public String authentification() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @GetMapping("/getPrincipal")
    public String getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
