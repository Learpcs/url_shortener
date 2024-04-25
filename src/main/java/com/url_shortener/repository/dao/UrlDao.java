package com.url_shortener.repository.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO non-wrapper types

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="user", indexes= {@Index(name = "idindex", columnList="id", unique = true), @Index(name = "urlindex", columnList="url")})
public class UrlDao {
    @Id
    Long id;

    @Column(nullable = false)
    String url;

    @ManyToOne(targetEntity = UserDao.class)
    UserDao ownerId;
}
