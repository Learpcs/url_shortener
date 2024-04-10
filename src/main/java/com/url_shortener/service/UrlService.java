package com.url_shortener.service;

import com.url_shortener.controller.dto.UrlDto;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    //CREATE
    public boolean save(UrlDto v);

    //READ
    public boolean existsById(Long id);
    public Optional<UrlDto> findById(Long id);
    public List<UrlDto> findAll();
    public Long count();

    //DELETE
    public boolean deleteById(Long id);
    public void deleteAll();
}
