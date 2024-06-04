package com.url_shortener.service.impl;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.DatabaseException;
import com.url_shortener.exception.ResourceExistsException;
import com.url_shortener.exception.ResourceNotFoundException;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import com.url_shortener.utils.ShortUrlRandomizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final IdUrlMapper idUrlMapper;
    private final ShortUrlRandomizer shortUrlRandomizer;
    
    @Override
    public String create(RandomUrlDto randomUrlDto) throws DatabaseException, ConverterException {
        for (int t = 0; t < 100; ++t) {
            Long id = shortUrlRandomizer.randomId();
            if (findById(id).isEmpty()) {
                urlRepository.save(new UrlDao(id, randomUrlDto.url(), 0L)); //FIXME OWNERID
                return idUrlMapper.getShortUrl(id);
            }
        }
        throw new DatabaseException("We ran out of space????");
    }

    @Override
    public void create(PickedUrlDto pickedUrlDto) throws DatabaseException, ConverterException, ResourceExistsException {
        Long id = idUrlMapper.getId(pickedUrlDto.shortUrl());
        if (urlRepository.findById(id).isPresent()) {
            throw new ResourceExistsException("Short url already exists");
        }
        urlRepository.save(new UrlDao(id, pickedUrlDto.url(), 0L));
    }


    @Override
    public Optional<UrlDao> findById(Long id) {
        return urlRepository.findById(id);
    }

    public Optional<UrlDao> findByShortUrl(String shortUrl) throws ConverterException {
        return findById(idUrlMapper.getId(shortUrl));
    }

    @Override
    public void delete(UrlDao urlDao) throws ResourceNotFoundException {
        deleteById(urlDao.getId());
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (findById(id).isEmpty()) {
            throw new ResourceNotFoundException("ShortUrl doesn't exists");
        }
        urlRepository.deleteById(id);
    }
}
