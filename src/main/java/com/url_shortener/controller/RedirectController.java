package com.url_shortener.controller;

import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.ResourceNotFoundException;
import com.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/api/v1/redirect")
    public RedirectView redirect_request_params(@RequestParam final String url) throws ConverterException, ResourceNotFoundException {
        return new RedirectView(
                urlService.findByShortUrl(url).getLongUrl()
        );
    }

    @GetMapping("/{link:^[a-zA-Z0-9]{5}$}")
    public RedirectView redirect(@PathVariable("link") final String url) throws ConverterException, ResourceNotFoundException {
        final RedirectView rv = new RedirectView(
                urlService.findByShortUrl(url).getLongUrl()
        );
        rv.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        return rv;
    }

    @GetMapping("/")
    public RedirectView homepage() {
        final RedirectView rv = new RedirectView("swagger-ui.html");
        rv.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        return rv;
    }
}
