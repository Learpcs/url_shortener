package com.url_shortener.utils.Mappers;

import com.url_shortener.config.security.CustomUser;
import com.url_shortener.config.security.WebSecurityConfig;
import com.url_shortener.controller.Dto.AuthWithRolesDto;
import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.ResourceValidationException;
import com.url_shortener.utils.UrlDtoFixer;
import com.url_shortener.utils.Validity.LongUrlValidator;
import com.url_shortener.utils.Validity.PasswordValidator;
import com.url_shortener.utils.Validity.ShortUrlValidator;
import com.url_shortener.utils.Validity.UsernameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DaoMapper {
    private final WebSecurityConfig webSecurityConfig;
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;

    public UserDao map(final AuthWithRolesDto authWithRolesDto) throws ResourceValidationException {
         final UserDao userDao = new UserDao();

         usernameValidator.validate(authWithRolesDto.username());
         passwordValidator.validate(authWithRolesDto.password());

         userDao.setUsername(authWithRolesDto.username());
         userDao.setPassword(webSecurityConfig.passwordEncoder().encode(authWithRolesDto.password()));
         userDao.setRole(authWithRolesDto.roles());

         return userDao;
    }



    private final IdUrlMapper idUrlMapper;
    private final ShortUrlValidator shortUrlValidator;
    private final LongUrlValidator longUrlValidator;
    private final UrlDtoFixer urlDtoFixer;

    public UrlDao map(PickedUrlDto pickedUrlDto) throws ResourceValidationException, ConverterException {
        pickedUrlDto = urlDtoFixer.fix(pickedUrlDto);
        final UrlDao urlDao = new UrlDao();

        longUrlValidator.validate(pickedUrlDto.longUrl());
        shortUrlValidator.validate(pickedUrlDto.shortUrl());

        urlDao.setUrlId(idUrlMapper.map(pickedUrlDto.shortUrl()));
        urlDao.setLongUrl(pickedUrlDto.longUrl());
        urlDao.setLastAccessDate(LocalDateTime.now());
        urlDao.setRedirectCount(0L);
        urlDao.setUserId(((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID());

        return urlDao;
    }

    public UrlDao map(final RandomUrlDto randomUrlDto, final Long id) throws ResourceValidationException, ConverterException {
        return map(new PickedUrlDto(randomUrlDto.longUrl(), idUrlMapper.map(id)));
    }
}
