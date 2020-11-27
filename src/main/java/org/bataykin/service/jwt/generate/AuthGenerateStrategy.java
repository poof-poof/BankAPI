package org.bataykin.service.jwt.generate;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.login.JwtTokenContainer;
import org.bataykin.security.JwtHelper;
import org.bataykin.security.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class AuthGenerateStrategy implements JwtGenerateStrategy<Authentication> {

    private final JwtHelper jwtHelper;

    private String getUserName(final Authentication authentication) {
        return of(authentication)
                .map(Authentication::getPrincipal)
                .map(UserDetailsImpl.class::cast)
                .map(UserDetailsImpl::getUsername)
                .orElse(null);
    }

    @Override
    public JwtTokenContainer generate(Authentication param) {
        return new JwtTokenContainer(jwtHelper.generateAccessToken(getUserName(param)));
    }
}
