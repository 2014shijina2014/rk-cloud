package org.rk.cloud.officeservice.excel.mapping.testcloud;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value="userService",fallbackFactory=TestCloudServiceBreakerFactory.class)
public interface ITestCloudService2 {

	@RequestMapping(value="/rk/web/core/user/userInfoKey/queryList",method = RequestMethod.GET)
	public List<Map<String, Object>> queryList(@RequestParam(value = "keyCode")String keyCode, @RequestParam(value = "keyName")String keyName);
}
