package com.url_shortener.utils.Validity;

import com.url_shortener.config.app.PasswordConfig;
import com.url_shortener.exception.ResourceValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordValidator {
    private final PasswordConfig passwordConfig;
    private final CredentialsValidator credentialsValidator;

    public void validate(final String password) throws ResourceValidationException {
        if (password.length() >= passwordConfig.SIZE_UPPER_BOUND) {
            throw new ResourceValidationException(String.format("Password size is too big (should be less than %s): %s", passwordConfig.SIZE_UPPER_BOUND, password));
        }

        if (password.length() < passwordConfig.SIZE_LOWER_BOUND) {
            throw new ResourceValidationException(String.format("Password size is too big (should be less than %s): %s", passwordConfig.SIZE_LOWER_BOUND, password));
        }

        credentialsValidator.validate(password);

        if (!isStrong(password)) {
            throw new ResourceValidationException("Password is weak");
        }
    }

    //FIXME
    public Boolean isStrong(final String password) {
        return 1 < password.length();
    }
}

