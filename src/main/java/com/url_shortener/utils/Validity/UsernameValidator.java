package com.url_shortener.utils.Validity;

import com.url_shortener.config.app.UsernameConfig;
import com.url_shortener.exception.ResourceValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernameValidator {
    private final UsernameConfig usernameConfig;
    private final CredentialsValidator credentialsValidator;

    public void validate(final String username) throws ResourceValidationException {
        if (username.length() >= usernameConfig.SIZE_UPPER_BOUND) {
            throw new ResourceValidationException(String.format("Username size is too big (should be less than %s): %s", usernameConfig.SIZE_UPPER_BOUND, username));
        }

        if (username.length() < usernameConfig.SIZE_LOWER_BOUND) {
            throw new ResourceValidationException(String.format("Username size is too big (should be less than %s): %s", usernameConfig.SIZE_LOWER_BOUND, username));
        }

        credentialsValidator.validate(username);

        if (!Character.isAlphabetic(username.charAt(0))) {
            throw new ResourceValidationException("Username is not valid");
        }
    }
}