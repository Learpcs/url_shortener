package com.url_shortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    // В таком случае здесь должен быть One to many
    @ManyToOne(targetEntity = UrlDao.class)
    List<UrlDao> urls;
}
