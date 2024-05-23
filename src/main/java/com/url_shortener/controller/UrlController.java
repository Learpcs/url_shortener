package com.url_shortener.controller;

import com.url_shortener.controller.request.UrlDto;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.service.UrlService;
import com.url_shortener.service.UserService;
import com.url_shortener.utils.Mappers.DaoMapper;
import com.url_shortener.utils.ShortUrlRandomizer;
import com.url_shortener.utils.Validity.UrlFixer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//TODO Service, URLVALIDATION, TESTS

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final UserService userService;
    private final DaoMapper daoMapper;
    private final ShortUrlRandomizer shortUrlRandomizer;
    private final UrlFixer urlFixer;

    @PostMapping("/create")
    public HttpStatus create(@RequestBody UrlDto urlDto) throws DatabaseException, UrlValidationException, ConverterException {
        urlDto = urlFixer.fix(urlDto);
        if (!urlService.create(daoMapper.map(urlDto, userService))) {
            throw new DatabaseException("ID already exists (probably)");
        }
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/get")
    public HttpStatus get(@RequestBody UrlDto urlDto) throws DatabaseException, UrlValidationException, ConverterException {
        urlDto = urlFixer.fix(urlDto);

        for (int tt = 0; tt < 100; ++tt) {
            urlDto = new UrlDto(urlDto.url(), shortUrlRandomizer.randomize(), urlDto.creatorToken());
            if (urlService.create(daoMapper.map(urlDto, userService))) {
                return HttpStatus.ACCEPTED;
            }
        }
        throw new DatabaseException("We ran out of space, wtf");
    }
}

