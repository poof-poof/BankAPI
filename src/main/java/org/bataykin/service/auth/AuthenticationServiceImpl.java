package org.bataykin.service.auth;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.AuthUserDto;
import org.bataykin.dto.login.LoginRequest;
import org.bataykin.dto.login.LoginResponse;
import org.bataykin.exception.LoginFailedException;
import org.bataykin.security.model.UserDetailsImpl;
import org.bataykin.service.jwt.generate.AuthGenerateStrategy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Profile("!stub")
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AuthGenerateStrategy jwtGenerateService;

    @Override
    public LoginResponse login(final LoginRequest loginRequest) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            getContext().setAuthentication(authentication);
            final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new LoginResponse(
                    jwtGenerateService.generate(authentication),
                    new AuthUserDto(userDetails.getId(), userDetails.getUsername(), getRoles(userDetails))
            );
        } catch (BadCredentialsException exception) {
            throw new LoginFailedException();
        }
    }

    private List<String> getRoles(final UserDetailsImpl userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toList());
    }
}
