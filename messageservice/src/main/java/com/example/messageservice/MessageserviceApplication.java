package com.example.messageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessageserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageserviceApplication.class, args);
    }

}
