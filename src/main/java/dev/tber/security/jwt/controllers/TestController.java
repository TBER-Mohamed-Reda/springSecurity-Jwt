package dev.tber.security.jwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/public")
    public String publicAccess() {
        return "Public access";
    }

    @GetMapping("/private")
    public String privateAccess() {
        return "Private access";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public String greetingUser() {
        return "Hello User";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String greetingAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public String greetingModerator() {
        return "Hello Moderator";
    }
}
