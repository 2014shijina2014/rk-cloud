package org.rk.cloud.sysservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RkCloudSysServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RkCloudSysServiceApplication.class, args);
	}
}
