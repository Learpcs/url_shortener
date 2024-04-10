package com.url_shortener.utils;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.exception.UrlValidationException;
import org.apache.commons.validator.routines.UrlValidator;

//TO-DO UrlValidator International symbol check
public class UrlValidityChecker {

    public static void validityCheck(UrlDto urlDto) throws UrlValidationException {
        if (urlDto.getId()< 0 || urlDto.getId() >= ConstantsClass.ID_UPPER_BOUND) {
            throw new UrlValidationException("ID is not valid: " + urlDto.getId());
        }
        if (urlDto.getUrl().length() >= ConstantsClass.URLSIZE_UPPER_BOUND) {
            throw new UrlValidationException("URL size is not valid: " + urlDto.getUrl());
        }
        if (UrlValidator.getInstance().isValid(urlDto.getUrl())) {
            return;
        }
        urlDto.setUrl("https://" + urlDto.getUrl());
        if (UrlValidator.getInstance().isValid(urlDto.getUrl())) {
            return;
        }

        throw new UrlValidationException("URL is not valid (if valid contact developer)");
    }
}
