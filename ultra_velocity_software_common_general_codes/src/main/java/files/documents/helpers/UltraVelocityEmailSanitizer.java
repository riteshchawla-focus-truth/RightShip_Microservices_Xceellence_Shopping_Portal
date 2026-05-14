package files.documents.helpers;

@FunctionalInterface
public interface UltraVelocityEmailSanitizer {
    String sanitize(String email);
}

