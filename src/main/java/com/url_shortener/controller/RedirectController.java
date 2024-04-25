package com.url_shortener.controller;

import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.UrlNotFoundException;
import com.url_shortener.repository.dao.UrlDao;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

//TODO dynamic mapping

@RestController
@RequestMapping("/api/v1/redirect")
public class RedirectController {
    @Autowired
    UrlService urlService;

    @GetMapping
    public RedirectView redirect(@RequestParam String url) throws ConverterException, UrlNotFoundException {

        return new RedirectView(
                urlService.findById(IdUrlMapper.getId(url)).orElseThrow(() -> new UrlNotFoundException("Short url doesn't exist")).getUrl()
        );
    }
}
