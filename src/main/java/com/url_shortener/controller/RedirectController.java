package com.url_shortener.controller;

import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.UrlNotFoundException;
import com.url_shortener.repository.dao.UrlDao;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
public class RedirectController {
    @Autowired
    UrlService urlService;

    @GetMapping("/api/v1/redirect")
    public RedirectView redirect_requestparams(@RequestParam String url) throws ConverterException, UrlNotFoundException {
        return new RedirectView(
                urlService.findById(IdUrlMapper.getId(url)).orElseThrow(() -> new UrlNotFoundException("Short url doesn't exist")).getUrl()
        );
    }

    @GetMapping("/{link}")
    public RedirectView redirect(@PathVariable("link") String url) throws ConverterException, UrlNotFoundException {
        RedirectView rv = new RedirectView(
                urlService.findById(IdUrlMapper.getId(url)).orElseThrow(() -> new UrlNotFoundException("Short url doesn't exist")).getUrl()
        );
        rv.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        return rv;
    }
}
