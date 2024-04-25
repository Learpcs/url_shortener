package com.url_shortener.controller;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.service.UrlService;
import com.url_shortener.service.UserService;
import com.url_shortener.utils.Mappers.DaoMapper;
import com.url_shortener.utils.Validity.LongUrlValidityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//TODO Service, URLVALIDATION, TESTS

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public HttpStatus save(@RequestBody UrlDto urlDto) throws DatabaseException, UrlValidationException, ConverterException {
        LongUrlValidityChecker.validityCheck(urlDto);
        if (!urlService.create(DaoMapper.map(urlDto, userService))) {
            throw new DatabaseException("ID already exists (probably)");
        }
        return HttpStatus.ACCEPTED;
    }

//    @GetMapping("/get")
//    public HttpStatus get(@RequestBody UrlDto urlEntity) throws DatabaseException, UrlValidationException {
//        UrlValidityChecker.validityCheck(urlEntity);
//
//        for (int tt = 0; tt < 100; ++tt) {
//            if (urlService.save(DaoMapper.map(urlEntity)) {
//                return HttpStatus.ACCEPTED;
//            }
//        }
//        throw new DatabaseException("We ran out of space, wtf");
//    }
}

