package io.zivame.MovieInfoServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServicesApplication.class, args);
	}

}
