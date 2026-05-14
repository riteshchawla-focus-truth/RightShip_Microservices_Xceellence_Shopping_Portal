package kafka.consumer.helpers;

@FunctionalInterface
public interface UltraVelocityEmailSanitizer {
    String sanitize(String email);
}

