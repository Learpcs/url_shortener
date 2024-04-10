package com.url_shortener.controller;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//TO-DO Service, URLVALIDATION, TESTS

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @PostMapping("/save")
    public HttpStatus save(@RequestBody UrlDto urlEntity) throws DatabaseException {
        if (urlRepository.existsById(urlEntity.getId())) {
            throw new DatabaseException("ID already exists");
        }
        urlRepository.save(urlEntity);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/get")
    public HttpStatus get(@RequestBody UrlDto urlEntity) throws DatabaseException {
        for (int tt = 0; tt < 100; ++tt) {
            if (!urlRepository.existsById(urlEntity.getId())) {
                urlRepository.save(urlEntity);
                return HttpStatus.ACCEPTED;
            }
        }
        throw new DatabaseException("We ran out of space, wtf");
    }
}

