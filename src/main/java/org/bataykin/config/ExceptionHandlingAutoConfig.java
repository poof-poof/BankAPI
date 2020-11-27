package org.bataykin.config;

import lombok.extern.log4j.Log4j2;
import org.bataykin.exception.BankApiException;
import org.bataykin.exception.BankApiExceptionErrorInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.valueOf;

@Log4j2
@Configuration
@ControllerAdvice
public class ExceptionHandlingAutoConfig {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BankApiExceptionErrorInfo> handle(final Exception exception) {
        if (exception instanceof BankApiException) {
            return handleException((BankApiException) exception);
        }
        log.error("Error during execution.", exception);
        if (exception instanceof AccessDeniedException) {
            throw (AccessDeniedException) exception;
        }
        if (exception instanceof AuthenticationException) {
            throw (AuthenticationException) exception;
        }
        return handleException(exception);
    }

    private ResponseEntity<BankApiExceptionErrorInfo> handleException(final BankApiException exception) {
        return new ResponseEntity<>(
                new BankApiExceptionErrorInfo(exception),
                new HttpHeaders(),
                valueOf(exception.getHttpStatus().name())
        );
    }

    private ResponseEntity<BankApiExceptionErrorInfo> handleException(final Exception exception) {
        return new ResponseEntity<>(
                new BankApiExceptionErrorInfo(new BankApiException(exception.getMessage(), exception)),
                new HttpHeaders(),
                INTERNAL_SERVER_ERROR
        );
    }
}
