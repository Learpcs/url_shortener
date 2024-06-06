package com.url_shortener.utils.Validity;

import com.url_shortener.exception.ResourceValidationException;
import org.springframework.stereotype.Component;

@Component
public class CredentialsValidator {
    public void validate(final String str) throws ResourceValidationException {
        for (int i = 0; i < str.length(); i++)
            if (validate(str.charAt(i)))
                throw new ResourceValidationException(String.format("String consists of invalid characters: %s", str));
    }

    public boolean validate(final char ch) {
        return     '0' <= ch && '9' >= ch
                || 'a' <= ch && 'z' >= ch
                || 'A' <= ch && 'Z' >= ch
                || '_' == ch
                || '-' == ch;
    }
}
