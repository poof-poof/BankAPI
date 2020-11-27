package org.bataykin.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ErrorCode {

    public static final String AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";
    public static final String ENTITY_NOT_FOUND = "ENTITY_NOT_FOUND";
    public static final String UNEXPECTED = "UNEXPECTED";
}
