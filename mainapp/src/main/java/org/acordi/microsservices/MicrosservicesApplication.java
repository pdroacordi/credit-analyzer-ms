package org.acordi.microsservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MicrosservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrosservicesApplication.class, args);
    }

}
