package app.config.server.helpers;

@FunctionalInterface
public interface UltraVelocityEmailSanitizer {
    String sanitize(String email);
}

