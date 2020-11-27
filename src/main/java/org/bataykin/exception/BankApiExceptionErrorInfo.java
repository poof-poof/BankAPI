package org.bataykin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankApiExceptionErrorInfo {

    private String code;
    private String message;

    public BankApiExceptionErrorInfo(final BankApiException exception) {
        this.code = exception.getErrorCode();
        this.message = exception.getMessage();
    }
}
