package back.end.config;

import back.end.helpers.UltraVelocityEmailSanitizer;
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
