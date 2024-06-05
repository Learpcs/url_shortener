package com.url_shortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app_user", indexes= {@Index(name = "idIndex", columnList="id", unique = true), @Index(name = "usernameIndex", columnList="username")})
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username, password;


    @Column(nullable = false)
    String role;

    @OneToMany
    List<UrlDao> urls;
}
