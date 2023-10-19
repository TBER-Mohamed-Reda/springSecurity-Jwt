package dev.tber.security.jwt.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    @Size(min = 3, max = 20, message = "Full name should have 3 to 20 characters")
    @NotBlank(message = "FullName is required")
    private String fullName;
    @Pattern(regexp = "^[A-Za-z0-9]+@gmail\\.com$", message = "Invalid username format")
    @NotBlank(message = "Username is required")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters long and include both letters and numbers")
    @NotBlank(message = "Password is required")
    private String password;
}
