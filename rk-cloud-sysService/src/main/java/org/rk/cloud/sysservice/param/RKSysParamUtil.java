/**
 * 
 */
package org.rk.cloud.sysservice.param;

import java.util.List;

import org.rk.cloud.sysservice.param.service.CoreSysParamService;
import org.rk.cloud.sysservice.param.service.ICoreSysParamService;
import org.rk.core.common.util.RKAppConfig;
import org.rk.core.common.util.RkObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Cavion(曹仁道)
 * @类描述：读取系统参数工具类
 * 2016年12月25日
 */
public class RKSysParamUtil {
	private RKSysParamUtil(){}//私有化构造
	
	@Autowired
	private static ICoreSysParamService sysParamService;

	public static ICoreSysParamService getSysParamService() {
		if(RkObjectUtil.isEmpty(sysParamService)){
			sysParamService=RKAppConfig.getBean(CoreSysParamService.class);
		}
		return sysParamService;
	}

	public static void setSysParamService(ICoreSysParamService sysParamService) {
		if(RkObjectUtil.isEmpty(sysParamService)){
			sysParamService=RKAppConfig.getBean(CoreSysParamService.class);
		}
		RKSysParamUtil.sysParamService = sysParamService;
	}
	
	public static CoreSysParam getParam(String key){
		return getSysParamService().selectModel("code", key);
	}
	public static List<CoreSysParam> selectByCodePrefix(String keyPrefix){
		return getSysParamService().selectByCodePrefix(keyPrefix);
	}
	public static String getParamValue(String key){
		CoreSysParam coreSysParam=getParam(key);
		if(RkObjectUtil.isEmpty(coreSysParam)){
			return "";
		}
		return coreSysParam.getValue();
	}

}
