package com.url_shortener.controller;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.controller.Dto.ShortUrlDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.*;
import com.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UrlController {

    private final UrlService urlService;

    @PreAuthorize("hasRole('PREMIUM')")
    @PostMapping("/createPicked")
    public void createPicked(@RequestBody final PickedUrlDto pickedUrlDto) throws DatabaseException, ResourceValidationException, ConverterException, ResourceExistsException {
        urlService.create(pickedUrlDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createRandom")
    public String createRandom(@RequestBody final RandomUrlDto randomUrlDto) throws DatabaseException, ResourceValidationException, ConverterException {
        return urlService.create(randomUrlDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getAllLinks")
    public List<UrlDao> getAllLinks() {
        return urlService.getAllLinks();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteLink")
    public void deleteLink(@RequestBody final ShortUrlDto shortUrlDto) throws ConverterException, AuthorizationException, ResourceNotFoundException {
        urlService.delete(shortUrlDto);
    }

    @GetMapping("/info")
    public UrlDao info(@RequestBody final ShortUrlDto shortUrlDto) throws ConverterException, ResourceNotFoundException {
        return urlService.info(shortUrlDto);
    }
}

