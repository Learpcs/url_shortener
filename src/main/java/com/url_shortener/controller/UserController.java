package com.url_shortener.controller;

import com.url_shortener.controller.Dto.AuthDto;
import com.url_shortener.controller.Dto.AuthWithRolesDto;
import com.url_shortener.controller.Dto.UserDto;
import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.*;
import com.url_shortener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody final AuthDto authDto) throws ResourceExistsException, ResourceValidationException {
        userService.createUser(authDto);
    }

    @PostMapping("/createPremium")
    @PreAuthorize("hasRole('ADMIN')")
    public void createPremium(@RequestBody final AuthDto authDto) throws ResourceExistsException, ResourceValidationException {
        userService.createPremium(authDto);
    }

    @PostMapping("/createAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public void createAdmin(@RequestBody final AuthDto authDto) throws ResourceExistsException, ResourceValidationException {
        userService.createAdmin(authDto);
    }

    @PostMapping("/createUserWithRoles")
    public void createUser(@RequestBody final AuthWithRolesDto authWithRolesDto) throws ResourceExistsException, ResourceValidationException {
        userService.createUser(authWithRolesDto);
    }

    @GetMapping("/info")
    public UserDao info(@RequestBody final UserDto userDto) throws ResourceNotFoundException {
        return userService.info(userDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public void delete() throws ResourceExistsException {
        userService.delete();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAdmin")
    public void deleteAdmin(@RequestBody final UserDto userDto) throws ResourceNotFoundException, ResourceExistsException {
        userService.deleteAdmin(userDto);
    }

}