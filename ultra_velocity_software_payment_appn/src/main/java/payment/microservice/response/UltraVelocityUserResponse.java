package payment.microservice.response;

import java.time.Instant;

public record UltraVelocityUserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Instant createdAt,
        Instant updatedAt
) {}
