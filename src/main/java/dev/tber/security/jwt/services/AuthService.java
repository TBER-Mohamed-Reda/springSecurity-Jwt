package dev.tber.security.jwt.services;

import dev.tber.security.jwt.dtos.requests.SignInRequest;
import dev.tber.security.jwt.dtos.requests.SignUpRequest;
import dev.tber.security.jwt.dtos.responses.JwtAuthResponse;

public interface AuthService {
    JwtAuthResponse signUp(SignUpRequest request);
    JwtAuthResponse signIn(SignInRequest request);
}
