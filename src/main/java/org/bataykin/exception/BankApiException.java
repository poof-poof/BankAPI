package org.bataykin.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.bataykin.constants.ErrorCode.UNEXPECTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class BankApiException extends RuntimeException {

    public BankApiException(final String message) {
        super(message);
    }

    public BankApiException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode(){
        return UNEXPECTED;
    }

    public HttpStatus getHttpStatus() {
        return BAD_REQUEST;
    }
}
