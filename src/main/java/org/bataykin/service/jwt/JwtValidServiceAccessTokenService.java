package org.bataykin.service.jwt;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static java.util.Optional.of;

@Service
public class JwtValidServiceAccessTokenService implements JwtValidService {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";

    @Override
    public String parseJwt(HttpServletRequest request) {
        return of(request.getHeader(HEADER))
                .filter(StringUtils::hasText)
                .filter(header -> header.startsWith(TOKEN_PREFIX))
                .map(header -> header.substring(TOKEN_PREFIX.length()))
                .orElse(null);
    }
}
