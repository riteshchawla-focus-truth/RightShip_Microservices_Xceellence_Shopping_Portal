package payment.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UltraVelocityPaymentMicroService {

    public static void main(String[] args) {

    //    SFTPClient.callSFTPServer();

        SpringApplication.run(UltraVelocityPaymentMicroService.class, args);
    }

}
