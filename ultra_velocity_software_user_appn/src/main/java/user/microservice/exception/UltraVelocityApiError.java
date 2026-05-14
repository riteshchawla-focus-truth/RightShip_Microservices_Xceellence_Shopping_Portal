package user.microservice.exception;

import java.time.Instant;
import java.util.List;

public record UltraVelocityApiError (
        Instant timestamp,
int status,
String error,
String message,
String path,
List<String> validationErrors
) {}
