package com.url_shortener.utils.Validity;

import com.url_shortener.config.app.LongUrlConfig;
import com.url_shortener.exception.ResourceValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LongUrlValidator {
    private final LongUrlConfig longUrlConfig;

    public void validate(final String url) throws ResourceValidationException {
        if (url.length() >= longUrlConfig.SIZE_UPPER_BOUND) {
            throw new ResourceValidationException(String.format("URL size is too big (should be less than %s): %s", longUrlConfig.SIZE_UPPER_BOUND, url));
        }

        if (url.length() < longUrlConfig.SIZE_LOWER_BOUND) {
            throw new ResourceValidationException(String.format("URL size is too big (should be less than %s): %s", longUrlConfig.SIZE_LOWER_BOUND, url));
        }

        if (!UrlValidator.getInstance().isValid(url)) {
            throw new ResourceValidationException("URL is not valid");
        }
    }
}
