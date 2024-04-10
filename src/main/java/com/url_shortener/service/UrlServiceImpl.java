package com.url_shortener.service;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;


    @Override
    public boolean save(UrlDto v) {
        urlRepository.save(v);
        return true;
    }

    @Override
    public boolean existsById() {
        return false;
    }

    @Override
    public Optional<UrlDto> findById() {
        return Optional.empty();
    }

    @Override
    public List<UrlDto> findAll() {
        return List.of();
    }

    @Override
    public Long count() {
        return 0l;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean deleteById() {
        return false;
    }

    @Override
    public void deleteAll() {

    }
}
