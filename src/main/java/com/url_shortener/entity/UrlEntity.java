package com.url_shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table.
public class UrlEntity {

    @Id // This annotation specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation provides for the specification of generation strategies for the values of primary keys.
    private Long id;

    private String url; // This will create a column named 'url' in your table.

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
