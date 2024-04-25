package com.url_shortener.repository.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//TODO indexes

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="url", indexes= {@Index(name = "idindex", columnList="id", unique = true), @Index(name = "urlindex", columnList="url")})
public class UserDao {
    @Id
    Long id;

    @Column(nullable = false)
    String login, password;

    @ManyToOne(targetEntity = UrlDao.class)
    List<UrlDao> urls;
}
