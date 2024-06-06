package com.url_shortener.config.security;

import com.url_shortener.entity.UserDao;
import com.url_shortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final UserDao user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return new CustomUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getUserId());
    }

    private Set<GrantedAuthority> getAuthorities(final UserDao userDao) {
        final Set<GrantedAuthority> authorities = new HashSet<>();
        final String[] roles = userDao.getRole().split(",");
        for (final String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim().toUpperCase()));
        }
        return authorities;
    }
}
