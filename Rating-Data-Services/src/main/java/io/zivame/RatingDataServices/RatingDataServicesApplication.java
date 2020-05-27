package io.zivame.RatingDataServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingDataServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingDataServicesApplication.class, args);
	}

}
