package com.url_shortener.service;

import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.ResourceExistsException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDao> find(UserDao userDao);

    void createUser(UserDto userDto) throws ResourceExistsException, AuthentificationException;
    void createPremium(UserDto userDto) throws ResourceExistsException, AuthentificationException;
    void createAdmin(UserDto userDto) throws ResourceExistsException, AuthentificationException;

    Optional<UserDao> findById(Long id);

    void deleteById(Long id) throws ResourceExistsException;
    void delete(UserDao userDao) throws ResourceExistsException;


    Boolean existsByUsername(String username);
    Optional<UserDao> auth(UserDto userDto) throws AuthentificationException;

    List<UrlDao> getAllLinks();
}
