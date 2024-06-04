package com.url_shortener.controller.request;

// Dto классы лучше положить в соответствующий пакет
public record UrlDto(String url, String shortUrl, String creatorToken) {

}
