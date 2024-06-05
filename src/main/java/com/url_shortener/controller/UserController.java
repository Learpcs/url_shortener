package com.url_shortener.controller;

import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.*;
import com.url_shortener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserDto userDto) throws ResourceExistsException, AuthentificationException {
        userService.createUser(userDto);
    }

    @PostMapping("/createPremium")
    @PreAuthorize("hasRole('ADMIN')")
    public void createPremium(@RequestBody UserDto userDto) throws ResourceExistsException, AuthentificationException {
        userService.createPremium(userDto);
    }

    @PostMapping("/createAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public void createAdmin(@RequestBody UserDto userDto) throws ResourceExistsException, AuthentificationException {
        userService.createAdmin(userDto);
    }
}