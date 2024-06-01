package com.url_shortener.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO non-wrapper types
//FIXME varchar len

// Entity лучше положить отдельно, не рядом с репозиториями

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
