package com.url_shortener.service;

import com.url_shortener.controller.Dto.AuthWithRolesDto;
import com.url_shortener.controller.Dto.AuthDto;
import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.ResourceExistsException;
import com.url_shortener.exception.ResourceNotFoundException;
import com.url_shortener.exception.ResourceValidationException;

import java.util.Optional;

public interface UserService {
    
    void createUser(AuthDto authDto) throws ResourceExistsException, ResourceValidationException;
    void createPremium(AuthDto authDto) throws ResourceExistsException, ResourceValidationException;
    void createAdmin(AuthDto authDto) throws ResourceExistsException, ResourceValidationException;

    void createUser(AuthWithRolesDto authWithRolesDto) throws ResourceExistsException, ResourceValidationException;


    Optional<UserDao> findById(Long id);
    UserDao findByUsername(String username) throws ResourceNotFoundException;

    void deleteById(Long id) throws ResourceExistsException;
    void deleteAdmin(UserDto userDto) throws ResourceExistsException, ResourceNotFoundException;
    void delete() throws ResourceExistsException;

    Boolean existsByUsername(String username);

    UserDao info(UserDto userDto) throws ResourceNotFoundException;
}
