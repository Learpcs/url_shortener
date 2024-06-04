package com.url_shortener.service.impl;

import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.entity.UrlDao;
import com.url_shortener.exception.AuthentificationException;
import com.url_shortener.exception.ResourceExistsException;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.entity.UserDao;
import com.url_shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void create(UserDto userDto) throws ResourceExistsException, AuthentificationException {
        if (existsByUsername(userDto.login())) {
            throw new ResourceExistsException("Username is already occupied");
        }
        userRepository.save(new UserDao(0L, userDto.login(), userDto.password(), "ADMIN", new ArrayList<UrlDao>()));
    }

    @Override
    public Optional<UserDao> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserDao> find(UserDao userDao) {
        return userRepository.findById(userDao.getId());
    }

    @Override
    public void deleteById(Long id) throws ResourceExistsException {
        if (findById(id).isEmpty()) {
            throw new ResourceExistsException("User id doesn't exists");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void delete(UserDao userDao) throws ResourceExistsException {
        deleteById(userDao.getId());
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public Optional<UserDao> auth(UserDto userDto) throws AuthentificationException {
        return userRepository.auth(userDto.login(), userDto.password());
    }
}
