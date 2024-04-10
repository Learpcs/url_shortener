package com.url_shortener.controller;

import com.url_shortener.exception.ConverterException;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.IdUrlConverter;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class RedirectController {
    @Autowired
    UrlService urlService;

    //To-do dynamic mapping
    @GetMapping("ecaaaaaa")
    public RedirectView redirect(HttpServletRequest request) throws ConverterException {
        return new RedirectView(urlService.findById(IdUrlConverter.getId("ecaaaaaa".toCharArray())).get().getUrl());
    }
}
