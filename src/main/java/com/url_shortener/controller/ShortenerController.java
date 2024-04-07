package com.url_shortener.controller;

import com.url_shortener.controller.dto.URLDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController {

    @GetMapping("/shorten")
    public URLDTO Shorten(@RequestParam(value = "url", defaultValue = "World") String url) {
        return new URLDTO(url);
    }
}

