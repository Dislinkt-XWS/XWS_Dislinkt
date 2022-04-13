package com.dislinktgateway.DislinktAPIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class DislinktApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DislinktApiGatewayApplication.class, args);
	}

}
