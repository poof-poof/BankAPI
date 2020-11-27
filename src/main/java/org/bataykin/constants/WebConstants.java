package org.bataykin.constants;

import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class WebConstants {

    public static final String ANY_URL = "/**";
    public static final String CSRF_IGNORE_URL = "/auth/login";
    public static final List<String> AUTH_POST_RESPONSE_WHITELIST = singletonList(CSRF_IGNORE_URL);
    public static final List<String> WHITE_LIST = AUTH_POST_RESPONSE_WHITELIST;
}
