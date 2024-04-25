package com.url_shortener.service;

import com.url_shortener.controller.dto.UserDto;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.repository.dao.UserDao;

import java.util.Optional;

public interface UserService {
    boolean create(UserDao userDao);
    Optional<UserDao> findById(Long id);
    boolean deleteById(Long id);
    UserDao Authentificate(UserDto userDto) throws AuthentificationException;
}