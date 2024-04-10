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
        if (existsById(v.getId())) {
            return false;
        }
        urlRepository.save(v);
        return true;
    }

    @Override
    public boolean existsById(Long id) {
        return urlRepository.existsById(id);
    }

    @Override
    public Optional<UrlDto> findById(Long id) {
        return urlRepository.findById(id);
    }

    @Override
    public List<UrlDto> findAll() {
        return urlRepository.findAll();
    }

    @Override
    public Long count() {
        return urlRepository.count();
    }

    @Override
    public boolean deleteById(Long id) {
        if (!existsById(id)){
            return false;
        }
        urlRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteAll() {
        urlRepository.deleteAll();
    }
}
