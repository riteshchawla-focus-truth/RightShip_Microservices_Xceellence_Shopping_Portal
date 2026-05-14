package user.microservice;

import org.springframework.boot.SpringApplication;

public class TestUltraVelocityJavaFunctionalPgmgMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(UltraVelocityUserMicroservice::main).with(TestcontainersConfiguration.class).run(args);
    }

}
