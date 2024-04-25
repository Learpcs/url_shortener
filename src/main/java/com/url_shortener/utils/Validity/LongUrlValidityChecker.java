package com.url_shortener.utils.Validity;

import com.url_shortener.controller.dto.UrlDto;
import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.utils.Constants;
import org.apache.commons.validator.routines.UrlValidator;

//TODO UrlValidator International symbol check
public class LongUrlValidityChecker {

    public static void validityCheck(UrlDto urlDto) throws UrlValidationException {
        if (urlDto.shortUrl().length() != Constants.ID_SIZE) {
            throw new UrlValidationException(String.format("shortUrl size is not valid (should be %s): %s", Constants.ID_SIZE, urlDto.shortUrl()));
        }
        if (urlDto.url().length() >= Constants.URLSIZE_UPPER_BOUND) {
            throw new UrlValidationException(String.format("URL size is too big (should be less than %s): %s", Constants.URLSIZE_UPPER_BOUND, urlDto.url()));
        }
        if (UrlValidator.getInstance().isValid(urlDto.url())) {
            return;
        }
        urlDto = new UrlDto("https:// " + urlDto.shortUrl(), urlDto.shortUrl(), urlDto.creatorToken());
        if (UrlValidator.getInstance().isValid(urlDto.url())) {
            return;
        }

        throw new UrlValidationException("URL is not valid (if valid contact developer)");
    }
}
