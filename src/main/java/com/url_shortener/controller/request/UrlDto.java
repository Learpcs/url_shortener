package com.url_shortener.controller.request;

public record UrlDto(String url, String shortUrl, String creatorToken) {

}
