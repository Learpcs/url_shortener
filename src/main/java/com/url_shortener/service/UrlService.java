package com.url_shortener.service;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.controller.Dto.ShortUrlDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.*;

import java.util.List;
import java.util.Optional;

public interface UrlService {

    void delete(ShortUrlDto shortUrlDto) throws ResourceNotFoundException, ConverterException, AuthorizationException;
    void deleteById(Long id) throws ResourceNotFoundException;

    UrlDao findByShortUrl(String shortUrl) throws ConverterException, ResourceNotFoundException;


    Optional<UrlDao> findById(Long id);

    String create(RandomUrlDto randomUrlDto) throws DatabaseException, ConverterException, ResourceValidationException;
    void create(PickedUrlDto pickedUrlDto) throws DatabaseException, ConverterException, ResourceExistsException, ResourceValidationException;

    List<UrlDao> getAllLinks();

    void deleteUrlsOlderThanXMinutes(Long X);

    UrlDao info(ShortUrlDto shortUrlDto) throws ConverterException, ResourceNotFoundException;
}
