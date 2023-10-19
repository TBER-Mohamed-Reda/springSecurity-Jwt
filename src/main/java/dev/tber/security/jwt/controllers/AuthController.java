package dev.tber.security.jwt.controllers;

import dev.tber.security.jwt.dtos.requests.SignInRequest;
import dev.tber.security.jwt.dtos.requests.SignUpRequest;
import dev.tber.security.jwt.dtos.responses.JwtAuthResponse;
import dev.tber.security.jwt.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
}
