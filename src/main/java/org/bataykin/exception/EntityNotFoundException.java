package org.bataykin.exception;

import org.springframework.http.HttpStatus;

import static org.bataykin.constants.ErrorCode.ENTITY_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class EntityNotFoundException extends BankApiException {

    private static final String DEFAULT_MESSAGE = "There were no data found by parameters provided";

    public EntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public String getErrorCode() {
        return ENTITY_NOT_FOUND;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return NOT_FOUND;
    }
}
