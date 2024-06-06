package com.url_shortener.service.impl;

import com.url_shortener.config.security.CustomUser;
import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.controller.Dto.ShortUrlDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.*;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.service.UrlService;
import com.url_shortener.utils.Mappers.DaoMapper;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import com.url_shortener.utils.ShortUrlRandomizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final DaoMapper daoMapper;
    private final IdUrlMapper idUrlMapper;
    private final ShortUrlRandomizer shortUrlRandomizer;

    @Override
    public String create(final RandomUrlDto randomUrlDto) throws DatabaseException, ConverterException, ResourceValidationException {
        daoMapper.map(randomUrlDto, 0L); //Validation checking
        for (int t = 0; 100 > t; ++t) {
            final Long id = shortUrlRandomizer.randomId();
            if (findById(id).isEmpty()) {
                return idUrlMapper.map(urlRepository.save(daoMapper.map(randomUrlDto, id)).getUrlId());
            }
        }
        throw new DatabaseException("We ran out of space????");
    }

    @Override
    public void create(final PickedUrlDto pickedUrlDto) throws ConverterException, ResourceExistsException, ResourceValidationException {
        final Long id = idUrlMapper.map(pickedUrlDto.shortUrl());
        if (urlRepository.findById(id).isPresent()) {
            throw new ResourceExistsException("ShortUrl already exists");
        }

        urlRepository.save(daoMapper.map(pickedUrlDto));
    }

    @Override
    public List<UrlDao> getAllLinks() {
        return urlRepository.findByUserId((((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID()));
    }

    @Override
    public void deleteUrlsOlderThanXMinutes(final Long X) {
        urlRepository.deleteUrlsOlderThanXMinutes(X);
    }


    @Override
    public Optional<UrlDao> findById(final Long id) {
        return urlRepository.findById(id);
    }



    @Override
    @Cacheable(cacheNames = "url")
    public UrlDao findByShortUrl(final String shortUrl) throws ConverterException, ResourceNotFoundException {
        final Long id = idUrlMapper.map(shortUrl);
        final UrlDao urlDao = findById(id).orElseThrow(() -> new ResourceNotFoundException("ShortUrl not found"));
        urlRepository.updateUrlAccess(id);
        log.debug("Redirect from database");
        return urlDao;
    }

    @Override
    public void delete(final ShortUrlDto shortUrlDto) throws ResourceNotFoundException, ConverterException, AuthorizationException {
        final Long userId = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID();
        final boolean hasRoleAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(r -> "ROLE_ADMIN".equals(r.getAuthority()));

        final UrlDao urlDao = findByShortUrl(shortUrlDto.shortUrl());

        if (!(urlDao.getUserId().equals(userId) || hasRoleAdmin)) {
            throw new AuthorizationException("User has no rights over this shortUrl");
        }

        deleteById(urlDao.getUrlId());
    }

    @Override
    public void deleteById(final Long id) throws ResourceNotFoundException {
        if (findById(id).isEmpty()) {
            throw new ResourceNotFoundException("ShortUrl doesn't exists");
        }
        urlRepository.deleteById(id);
    }

    @Override
    public UrlDao info(final ShortUrlDto shortUrlDto) throws ConverterException, ResourceNotFoundException {
        return findByShortUrl(shortUrlDto.shortUrl());
    }
}
