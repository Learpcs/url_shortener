package com.url_shortener.service.impl;

import com.url_shortener.config.security.CustomUser;
import com.url_shortener.controller.Dto.AuthDto;
import com.url_shortener.controller.Dto.AuthWithRolesDto;
import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.ResourceExistsException;
import com.url_shortener.exception.ResourceNotFoundException;
import com.url_shortener.exception.ResourceValidationException;
import com.url_shortener.repository.UserRepository;
import com.url_shortener.service.UserService;
import com.url_shortener.utils.Mappers.DaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DaoMapper daoMapper;

    @Override
    public void createUser(final AuthDto authDto) throws ResourceExistsException, ResourceValidationException {
        createUser(new AuthWithRolesDto(authDto.username(), authDto.password(), "USER"));
    }

    @Override
    public void createPremium(final AuthDto authDto) throws ResourceExistsException, ResourceValidationException {
        createUser(new AuthWithRolesDto(authDto.username(), authDto.password(), "USER,PREMIUM"));
    }

    @Override
    public void createAdmin(final AuthDto authDto) throws ResourceExistsException, ResourceValidationException {
        createUser(new AuthWithRolesDto(authDto.username(), authDto.password(), "USER,PREMIUM,ADMIN"));
    }

    @Override
    public void createUser(final AuthWithRolesDto authWithRolesDto) throws ResourceExistsException, ResourceValidationException {
        if (existsByUsername(authWithRolesDto.username())) {
            throw new ResourceExistsException("Username is already occupied");
        }
        userRepository.save(daoMapper.map(authWithRolesDto));
    }

    @Override
    public Optional<UserDao> findById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDao findByUsername(final String username) throws ResourceNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username doesn't exist"));
    }

    @Override
    public void deleteById(final Long id) throws ResourceExistsException {
        if (findById(id).isEmpty()) {
            throw new ResourceExistsException("User id doesn't exists");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAdmin(final UserDto userDto) throws ResourceExistsException, ResourceNotFoundException {
        final UserDao userDao = findByUsername(userDto.username());

        deleteById(userDao.getUserId());
    }

    @Override
    public void delete() throws ResourceExistsException {
        deleteById(((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID());
    }

    @Override
    public Boolean existsByUsername(final String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDao info(final UserDto userDto) throws ResourceNotFoundException {
        return userRepository.findByUsername(userDto.username()).orElseThrow(() -> new ResourceNotFoundException("Username doesn't exist"));
    }
}
