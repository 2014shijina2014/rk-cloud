package org.rk.cloud.officeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class RkCloudOfficeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RkCloudOfficeServiceApplication.class, args);
	}
}
