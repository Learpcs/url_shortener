package com.url_shortener.config.security;

import com.url_shortener.entity.UserDao;
import com.url_shortener.exception.ResourceNotFoundException;
import com.url_shortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDao user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return new CustomUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getUserId());
    }

    private Set<GrantedAuthority> getAuthorities(UserDao userDao) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        String[] roles = userDao.getRole().split(",");
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim().toUpperCase()));
        }
        return authorities;
    }
}
