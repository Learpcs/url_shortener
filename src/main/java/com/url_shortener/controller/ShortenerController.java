package com.url_shortener.controller;

import com.url_shortener.controller.dto.URLDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController {

    @PostMapping("/api/v1/shorten")
    public URLDTO Shorten(@RequestParam(value = "url") String url) {
        return new URLDTO(url);
    }
}

