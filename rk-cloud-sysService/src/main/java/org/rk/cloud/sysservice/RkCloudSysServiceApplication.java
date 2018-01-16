package org.rk.cloud.sysservice;

import org.rk.cloud.common.config.AppPropConfigInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class RkCloudSysServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RkCloudSysServiceApplication.class, args);
		String env = context.getEnvironment().getProperty("spring.profiles.active");
		String appName = context.getEnvironment().getProperty("spring.application.name");
		AppPropConfigInfo.setEnv(env);
		AppPropConfigInfo.setAppName(appName);
	}
}
