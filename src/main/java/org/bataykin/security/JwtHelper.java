package org.bataykin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parser;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Log4j2
@Component
public class JwtHelper {

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtAccessTokenExpirationMs}")
    private int jwtAccessTokenExpirationMs;

    public String generateAccessToken(final String userName) {
        return generateDefaultToken(userName, jwtAccessTokenExpirationMs);
    }

    private String generateDefaultToken(final String userName, final int liveTime) {
        return builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + liveTime))
                .signWith(HS512, jwtSecret)
                .compact();
    }
    public String getUserNameFromToken(final String authToken) {
        return getClaimsJws(authToken).getBody().getSubject();
    }

    public boolean isValidToken(final String authToken) {
        try {
            getClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error("Invalid the JWT token: {}", e.getMessage());
        }
        return false;
    }

    private Jws<Claims> getClaimsJws(final String authToken) {
        return parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
    }

}
