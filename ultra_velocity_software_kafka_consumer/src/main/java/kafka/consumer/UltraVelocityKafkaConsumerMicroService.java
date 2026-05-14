package kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UltraVelocityKafkaConsumerMicroService {

    public static void main(String[] args) {

    //    SFTPClient.callSFTPServer();

        SpringApplication.run(UltraVelocityKafkaConsumerMicroService.class, args);
    }

}
