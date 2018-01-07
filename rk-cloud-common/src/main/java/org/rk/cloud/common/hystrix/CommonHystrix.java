/**
 * 
 */
package org.rk.cloud.common.hystrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 曹仁道（cavion）
 * @email caorendao187@163.com
 * @描述：
 * 2018年1月7日 下午6:24:11
 */
public class CommonHystrix {
	
	/**
	 * @author cavion
	 * @描述：返回map异常数据
	 * 2018年1月7日 下午6:28:06
	 * @return
	 */
	public static Map<String, Object> hystrixMap() {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("errorCode", "404");
		errorMap.put("errorMsg", "断路器生效");
		return errorMap;
	}
	/**
	 * @author cavion
	 * @描述：返回list的map异常数据
	 * 2018年1月7日 下午6:28:42
	 * @return
	 */
	public static List<Map<String, Object>> hystrixMapList(){
		List<Map<String, Object>> errorList= new ArrayList<Map<String, Object>>();
		errorList.add(hystrixMap());
		return errorList;
	}
	/**
	 * @author cavion
	 * @描述：返回string熔断数据
	 * 2018年1月7日 下午6:29:09
	 * @return
	 */
	public static String hystrixString() {
		return "";
	}

}
