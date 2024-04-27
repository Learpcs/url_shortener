package com.url_shortener.utils.Mappers;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.controller.dto.UserDto;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.repository.dao.UrlDao;
import com.url_shortener.repository.dao.UserDao;
import com.url_shortener.service.UserService;
import com.url_shortener.utils.PasswordHasher;

public class DaoMapper {

    public static UrlDao map(UrlDto urlDto, UserService userService) throws ConverterException {
        return new UrlDao(
                IdUrlMapper.getId(urlDto.shortUrl())    ,
                urlDto.url(),
                userService.findById(0L).get()
        );
    }

    public static UserDao map(UserDto userDto, UserService userService) throws AuthentificationException {
        return userService.Authentificate(userDto);
    }

}
