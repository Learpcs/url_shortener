package com.url_shortener.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Getter
public class CustomUser extends User {

    private final Long userID;

    public CustomUser(String username, String password, Set<GrantedAuthority> authorities, Long userID) {
        super(username, password, authorities);
        this.userID = userID;
    }
}