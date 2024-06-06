package com.url_shortener.utils.Validity;

import com.url_shortener.config.app.ShortUrlConfig;
import com.url_shortener.exception.ConverterException;
import com.url_shortener.exception.ResourceValidationException;
import com.url_shortener.utils.Mappers.IdUrlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShortUrlValidator {
    private final ShortUrlConfig shortUrlConfig;
    private final Base62Validator base62Validator;
    private final IdUrlMapper idUrlMapper;

    public void validate(final String shortUrl) throws ResourceValidationException, ConverterException {
        if (shortUrl.length() != shortUrlConfig.SIZE) {
            throw new ResourceValidationException(String.format("shortUrl size is not valid (should be %s): %s", shortUrlConfig.SIZE, shortUrl));
        }
        base62Validator.validate(shortUrl);
        final Long id = idUrlMapper.map(shortUrl);

        if (id >= shortUrlConfig.SIZE_UPPER_BOUND) {
            throw new ResourceValidationException(String.format("ID size is too big (should be less than %s): %s", shortUrlConfig.SIZE_UPPER_BOUND, id));
        }

        if (id < shortUrlConfig.SIZE_LOWER_BOUND) {
            throw new ResourceValidationException(String.format("ID size is too big (should be less than %s): %s", shortUrlConfig.SIZE_LOWER_BOUND, id));
        }
    }
}
