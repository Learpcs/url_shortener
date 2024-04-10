package com.url_shortener.controller.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="url", indexes= {@Index(name = "idindex", columnList="id", unique = true), @Index(name = "urlindex", columnList="url")})
public class UrlDto {
    @Id
    Long id;

    @Column(nullable = false)
    String url;
}
