package rest.client.helpers;

@FunctionalInterface
public interface UltraVelocityEmailSanitizer {
    String sanitize(String email);
}

