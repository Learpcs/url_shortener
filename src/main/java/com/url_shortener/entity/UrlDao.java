package com.url_shortener.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="url", indexes= {@Index(name = "urlIdIndex", columnList="urlId", unique = true), @Index(name = "userIdIndex", columnList="userId")})
public class UrlDao {
    @Id
    Long urlId;

    @Column(nullable = false)
    String longUrl;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    Long redirectCount = 0L;

    @Column
    LocalDateTime lastAccessDate = LocalDateTime.now();
}
