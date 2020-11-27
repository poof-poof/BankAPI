package org.bataykin.controller;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.login.LoginRequest;
import org.bataykin.dto.login.LoginResponse;
import org.bataykin.service.auth.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    LoginResponse login(@Valid @RequestBody final LoginRequest request) {
        return authenticationService.login(request);
    }
}
