package org.rk.cloud.centralserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RkCloudCentralServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RkCloudCentralServerApplication.class, args);
	}
}
