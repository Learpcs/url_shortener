package com.url_shortener.service.impl;

import com.url_shortener.controller.request.UserDto;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.entity.UserDao;
import com.url_shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean create(UserDao userDao) {
        if (findById(userDao.getId()).orElse(null) != null) {
            return false;
        }
        userRepository.save(userDao);
        return true;
    }

    @Override
    public Optional<UserDao> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if (findById(id).orElse(null) == null) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDao Authentificate(UserDto userDto) throws AuthentificationException {
        return userRepository.Authentificate(userDto.login(), userDto.password()).orElseThrow(() -> new AuthentificationException("Invalid credentials"));
    }
}
