package com.url_shortener.service;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.ResourceExistsException;
import com.url_shortener.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UrlService {

    void delete(UrlDao urlDao) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;

    UrlDao findByShortUrl(String shortUrl) throws ConverterException, ResourceNotFoundException;
    Optional<UrlDao> findById(Long id);

    String create(RandomUrlDto randomUrlDto) throws DatabaseException, ConverterException;
    void create(PickedUrlDto pickedUrlDto) throws DatabaseException, ConverterException, ResourceExistsException;

    UrlDao getInfo(String shortUrl) throws ConverterException, ResourceNotFoundException;
    List<UrlDao> getAllLinks();
}
