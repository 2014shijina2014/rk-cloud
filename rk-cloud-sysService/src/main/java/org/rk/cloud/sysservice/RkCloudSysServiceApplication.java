package org.rk.cloud.sysservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RkCloudSysServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RkCloudSysServiceApplication.class, args);
	}
}
