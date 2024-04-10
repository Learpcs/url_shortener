package com.url_shortener.service;

import com.url_shortener.controller.dto.UrlDto;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    //CREATE
    boolean save(UrlDto v);

    //READ
    boolean existsById(Long id);
    Optional<UrlDto> findById(Long id);
    List<UrlDto> findAll();
    Long count();

    //DELETE
    boolean deleteById(Long id);
    void deleteAll();
}
