package com.url_shortener.controller;

import com.url_shortener.controller.Dto.PickedUrlDto;
import com.url_shortener.controller.Dto.RandomUrlDto;
import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.exception.*;
import com.url_shortener.service.UrlService;
import com.url_shortener.service.UserService;
import com.url_shortener.utils.Mappers.DaoMapper;
import com.url_shortener.utils.ShortUrlRandomizer;
import com.url_shortener.utils.Validity.UrlFixer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void create(@RequestBody UserDto userDto) throws DatabaseException, UrlValidationException, ConverterException, ResourceExistsException, AuthentificationException {
        userService.create(userDto);
    }


    @PostMapping("/auth")
    public void auth(@RequestBody UserDto userDto) throws DatabaseException, UrlValidationException, ConverterException, AuthentificationException {
        userService.auth(userDto);
    }
}