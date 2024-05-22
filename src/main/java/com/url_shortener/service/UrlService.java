package com.url_shortener.service;

import com.url_shortener.repository.entity.UrlDao;

import java.util.Optional;

public interface UrlService {
    boolean create(UrlDao urlDao);
    Optional<UrlDao> findById(Long id);
    boolean deleteById(Long id);
}
