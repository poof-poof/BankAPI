package org.bataykin.service.auth;

import org.bataykin.dto.AuthUserDto;
import org.bataykin.dto.login.JwtTokenContainer;
import org.bataykin.dto.login.LoginRequest;
import org.bataykin.dto.login.LoginResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Profile("stub")
@Service
public class AuthenticationServiceStub implements AuthenticationService {

    private static final String STUB_TOKEN = "eyJhbGciOiJIUzUxMiJ9."
            .concat("eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MjQ4OTUyNiwiZXhwIjoxNTkyNDkzMTI2fQ.")
            .concat("9OH4NpLhoA8dqEiEgUIZ_y0Wtx4Tpqy9jS1mRokUAY1Cieh8b0RcvRDi8iLJVYajer2JSlNRe2K10eF3yWA71Q");
    private static final long STUB_ID = 1L;
    private static final String STUB_USERNAME = "stub";
    private static final List<String> STUB_ROLES = Collections.singletonList("ROLE_ADMIN");

    @Override
    public LoginResponse login(final LoginRequest loginRequest) {
        return new LoginResponse(new JwtTokenContainer(STUB_TOKEN), new AuthUserDto(STUB_ID, STUB_USERNAME, STUB_ROLES));
    }
}
