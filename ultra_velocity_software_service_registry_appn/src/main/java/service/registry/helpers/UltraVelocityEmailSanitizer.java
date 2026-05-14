package service.registry.helpers;

@FunctionalInterface
public interface UltraVelocityEmailSanitizer {
    String sanitize(String email);
}

