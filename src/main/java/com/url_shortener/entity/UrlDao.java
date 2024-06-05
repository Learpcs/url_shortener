package com.url_shortener.entity;

import com.url_shortener.exception.DatabaseException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//TODO non-wrapper types
//FIXME varchar len

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="url", indexes= {@Index(name = "idIndex", columnList="id", unique = true), @Index(name = "ownerIdIndex", columnList="ownerId")})
public class UrlDao {
    @Id
    Long id;

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    Long ownerId;

    @Column(nullable = false)
    Long redirectCount = 0L;

    @Column
    LocalDateTime lastAccessDate = LocalDateTime.now();

    public UrlDao(Long id, String url, Long ownerId) throws DatabaseException {
        this.id = id;
        this.url = url;
        this.ownerId = ownerId;
        this.redirectCount = 0L;
        this.lastAccessDate = LocalDateTime.now();
        if (this.ownerId == null) {
            throw new DatabaseException("Owner id is null");
        }
    }
}
