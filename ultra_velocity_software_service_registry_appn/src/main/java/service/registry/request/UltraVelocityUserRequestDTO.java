package service.registry.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UltraVelocityUserRequestDTO(
        @NotBlank(message = "First name is required") String firstName,
        @NotBlank(message = "Last name is required") String lastName,
        @Email @NotBlank(message = "Email is required") String email,
        String phone
) {}

