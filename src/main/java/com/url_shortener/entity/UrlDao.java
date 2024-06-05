package com.url_shortener.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.url_shortener.exception.DatabaseException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//TODO non-wrapper types
//FIXME varchar len

@Entity
@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
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

    public UrlDao(Long urlId, String url, Long userId) throws DatabaseException {
        this.urlId = urlId;
        this.longUrl = url;
        this.userId = userId;
        this.redirectCount = 0L;
        this.lastAccessDate = LocalDateTime.now();
    }
}
