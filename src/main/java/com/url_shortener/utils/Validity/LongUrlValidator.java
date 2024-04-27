package com.url_shortener.utils.Validity;

import com.url_shortener.exception.UrlValidationException;
import com.url_shortener.utils.Constants;
import org.apache.commons.validator.routines.UrlValidator;

//TODO UrlValidator International symbol check
public class LongUrlValidator {

    public static void validate(String url) throws UrlValidationException {
        if (url.length() >= Constants.URLSIZE_UPPER_BOUND) {
            throw new UrlValidationException(String.format("URL size is too big (should be less than %s): %s", Constants.URLSIZE_UPPER_BOUND, url));
        }

        if (!UrlValidator.getInstance().isValid(url)) {
            throw new UrlValidationException("URL is not valid (if valid contact developer)");
        }
    }
}
