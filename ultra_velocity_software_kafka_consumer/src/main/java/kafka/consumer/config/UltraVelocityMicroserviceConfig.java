package kafka.consumer.config;

import kafka.consumer.helpers.UltraVelocityEmailSanitizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UltraVelocityMicroserviceConfig {

    // A simple custom bean to demonstrate Spring Beans
    @Bean
    public UltraVelocityEmailSanitizer emailSanitizer() {
        return email -> email == null ? null : email.trim().toLowerCase();
    }
}
