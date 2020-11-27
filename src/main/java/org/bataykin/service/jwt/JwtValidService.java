package org.bataykin.service.jwt;

import javax.servlet.http.HttpServletRequest;

public interface JwtValidService {

    String parseJwt(final HttpServletRequest request);
}
