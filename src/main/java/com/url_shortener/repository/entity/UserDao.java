package com.url_shortener.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
//    @Size(min = 0, message = "{validation.name.size.too_short}")
//    @Size(max = 916132832, message = "{validation.name.size.too_long}")
    Long id;

    @Column(nullable = false)
//    @Size(min = 3, message = "{validation.name.size.too_short}")
//    @Size(max = 200, message = "{validation.name.size.too_long}")
    String login, password;

    // В таком случае здесь должен быть One to many
    @ManyToOne(targetEntity = UrlDao.class)
    List<UrlDao> urls;
}
