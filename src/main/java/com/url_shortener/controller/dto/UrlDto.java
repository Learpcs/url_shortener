package com.url_shortener.controller.dto;

import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.utils.ConstantsClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.routines.UrlValidator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="url", indexes= {@Index(name = "idindex", columnList="id", unique = true), @Index(name = "urlindex", columnList="url", unique = true)})
public class UrlDto {
    @Id
    Long id;
    @Column(unique=true, nullable = false)
    String url;

    UrlDto(Long id, String url) throws UrlValidationException {
        if (id < 0 || id >= ConstantsClass.ID_UPPER_BOUND) {
            throw new UrlValidationException("ID is not valid: " + id);
        }
        if (url.length() >= 2048 || !UrlValidator.getInstance().isValid(url)) {
            throw new UrlValidationException("URL is not valid: " + url);
        }
        this.id = id;
        this.url = url;
    }


}
