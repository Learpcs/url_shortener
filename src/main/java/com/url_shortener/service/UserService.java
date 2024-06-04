package com.url_shortener.service;

import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.ResourceExistsException;

import java.util.Optional;

public interface UserService {

    Optional<UserDao> find(UserDao userDao);
    Optional<UserDao> findById(Long id);

    void deleteById(Long id) throws ResourceExistsException;
    void delete(UserDao userDao) throws ResourceExistsException;

    void create(UserDto userDto) throws ResourceExistsException, AuthentificationException;

    Boolean existsByUsername(String username);
    Optional<UserDao> auth(UserDto userDto) throws AuthentificationException;


}
