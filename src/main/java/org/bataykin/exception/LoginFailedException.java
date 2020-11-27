package org.bataykin.exception;

import org.springframework.http.HttpStatus;

import static org.bataykin.constants.ErrorCode.AUTHENTICATION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
public class LoginFailedException extends BankApiException {

    private static final String DEFAULT_MESSAGE = "Username or password was incorrect. Please try again.";

    public LoginFailedException() {
        super(DEFAULT_MESSAGE);
    }

    public LoginFailedException(final Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public LoginFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(final String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return AUTHENTICATION_FAILED;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return UNAUTHORIZED;
    }
}
