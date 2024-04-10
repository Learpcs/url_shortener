package com.url_shortener.controller;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.UrlValidityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//TO-DO Service, URLVALIDATION, TESTS

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/save")
    public HttpStatus save(@RequestBody UrlDto urlEntity) throws DatabaseException, UrlValidationException {
        UrlValidityChecker.validityCheck(urlEntity);
        if (!urlService.save(urlEntity)) {
            throw new DatabaseException("ID already exists (probably)");
        }
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/get")
    public HttpStatus get(@RequestBody UrlDto urlEntity) throws DatabaseException, UrlValidationException {
        UrlValidityChecker.validityCheck(urlEntity);

        for (int tt = 0; tt < 100; ++tt) {
            if (urlService.save(urlEntity)) {
                return HttpStatus.ACCEPTED;
            }
        }
        throw new DatabaseException("We ran out of space, wtf");
    }
}

