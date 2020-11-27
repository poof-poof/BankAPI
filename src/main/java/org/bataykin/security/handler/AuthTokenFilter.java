package org.bataykin.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bataykin.security.JwtHelper;
import org.bataykin.security.service.UserDetailsServiceImpl;
import org.bataykin.service.jwt.JwtValidService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;
import static org.bataykin.constants.WebConstants.WHITE_LIST;

@Log4j2
@Service
@Profile("!stub")
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final JwtValidService validAccessToken;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        try {
            ofNullable(validAccessToken.parseJwt(request))
                    .filter(jwtHelper::isValidToken)
                    .map(jwtHelper::getUserNameFromToken)
                    .map(userDetailsService::loadUserByUsername)
                    .map(userDetails -> new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()))
                    .ifPresent(authentication -> {
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(final HttpServletRequest request) {
        return WHITE_LIST.stream().anyMatch(url -> url.equals(request.getRequestURI()));
    }
}
