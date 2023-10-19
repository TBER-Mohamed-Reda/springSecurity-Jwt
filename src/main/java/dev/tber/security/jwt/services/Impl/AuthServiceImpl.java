package dev.tber.security.jwt.services.Impl;

import dev.tber.security.jwt.dtos.requests.SignInRequest;
import dev.tber.security.jwt.dtos.requests.SignUpRequest;
import dev.tber.security.jwt.dtos.responses.JwtAuthResponse;
import dev.tber.security.jwt.models.entities.Role;
import dev.tber.security.jwt.models.entities.User;
import dev.tber.security.jwt.models.enums.ERole;
import dev.tber.security.jwt.repositories.RoleRepository;
import dev.tber.security.jwt.repositories.UserRepository;
import dev.tber.security.jwt.services.AuthService;
import dev.tber.security.jwt.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private  final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthResponse signUp(SignUpRequest request) {
        User user= User.builder().fullName(request.getFullName())
                .username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
        String token=jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(token).build();
    }


    @Override
    public JwtAuthResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user=userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        String token=jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(token).build();
    }
}
