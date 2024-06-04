package com.url_shortener.controller;

import com.url_shortener.config.app.HostConfig;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.UrlNotFoundException;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final UrlService urlService;
    private final IdUrlMapper idUrlMapper;
    private final HostConfig hostConfig;

    @GetMapping("/api/v1/redirect")
    public RedirectView redirect_requestparams(@RequestParam String url) throws ConverterException, UrlNotFoundException {
        return new RedirectView(
                urlService.findByShortUrl(url).orElseThrow(() -> new UrlNotFoundException("Short url doesn't exist")).getUrl()
        );
    }

    @GetMapping("/{link:^[a-zA-Z0-9]{5}$}")
    public RedirectView redirect(@PathVariable("link") String url) throws ConverterException, UrlNotFoundException {
        RedirectView rv = new RedirectView(
                urlService.findByShortUrl(url).orElseThrow(() -> new UrlNotFoundException("Short url doesn't exist")).getUrl()
        );
        rv.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        return rv;
    }

    @GetMapping("/")
    public RedirectView homepage() throws ConverterException, UrlNotFoundException {
        RedirectView rv = new RedirectView(hostConfig.HOST_URL + "/swagger-ui.html");
        rv.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        return rv;
    }
}
