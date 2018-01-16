package org.rk.cloud.officeservice;

import org.rk.cloud.common.config.AppPropConfigInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class RkCloudOfficeServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RkCloudOfficeServiceApplication.class, args);
		String env = context.getEnvironment().getProperty("spring.profiles.active");
		String appName = context.getEnvironment().getProperty("spring.application.name");
		AppPropConfigInfo.setEnv(env);
		AppPropConfigInfo.setAppName(appName);
	}
}
