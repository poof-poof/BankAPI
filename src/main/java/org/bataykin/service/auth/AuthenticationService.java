package org.bataykin.service.auth;

import org.bataykin.dto.login.LoginRequest;
import org.bataykin.dto.login.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(final LoginRequest loginRequest);
}
