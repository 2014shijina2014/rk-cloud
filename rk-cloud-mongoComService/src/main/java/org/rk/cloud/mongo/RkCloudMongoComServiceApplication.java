package org.rk.cloud.mongo;

import org.rk.cloud.common.config.AppPropConfigInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
/*@EnableFeignClients
@EnableCircuitBreaker*/
@ComponentScan(basePackages = {"org.rk.core.nosql.mongodb.util","org.rk.cloud.mongo"})
public class RkCloudMongoComServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RkCloudMongoComServiceApplication.class, args);
		String env = context.getEnvironment().getProperty("spring.profiles.active");
		String appName = context.getEnvironment().getProperty("spring.application.name");
		AppPropConfigInfo.setEnv(env);
		AppPropConfigInfo.setAppName(appName);
	}
}
