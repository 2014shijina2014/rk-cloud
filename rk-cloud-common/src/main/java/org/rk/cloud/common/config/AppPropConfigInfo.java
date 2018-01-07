/**
 * 
 */
package org.rk.cloud.common.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author 曹仁道（cavion）
 * @email caorendao187@163.com
 * @描述：
 * 2018年1月7日 下午8:18:31
 */
@Configuration
public class AppPropConfigInfo {
	private static String env;
	private static String appName;

	public static String getEnv() {
		return env;
	}
	public static void setEnv(String env) {
		AppPropConfigInfo.env = env;
	}
	public static String getAppName() {
		return appName;
	}
	public static void setAppName(String appName) {
		AppPropConfigInfo.appName = appName;
	}
	

}
