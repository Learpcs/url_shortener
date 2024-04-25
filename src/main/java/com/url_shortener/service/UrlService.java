package com.url_shortener.service;

import com.url_shortener.repository.dao.UrlDao;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    boolean create(UrlDao urlDao);
    Optional<UrlDao> findById(Long id);
    boolean deleteById(Long id);
}
