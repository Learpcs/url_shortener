package com.url_shortener.controller;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.ResourceExistsException;
import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PreAuthorize("hasRole('PREMIUM')")
    @PostMapping("/createPicked")
    public void createPicked(@RequestBody PickedUrlDto pickedUrlDto) throws DatabaseException, UrlValidationException, ConverterException, ResourceExistsException {
        urlService.create(pickedUrlDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createRandom")
    public String createRandom(@RequestBody RandomUrlDto randomUrlDto) throws DatabaseException, UrlValidationException, ConverterException {
        return urlService.create(randomUrlDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllLinks")
    public List<UrlDao> getAllLinks() throws DatabaseException, UrlValidationException, ConverterException {
        return urlService.getAllLinks();
    }
}

