package org.rk.cloud.officeservice.excel.mapping.testcloud;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="sysService",fallback = TestCloudServiceImpl.class)
public interface ITestCloudService {

	@RequestMapping(value="/rk/web/core/sys/param/queryList",method = RequestMethod.GET)
	public List<Map<String, Object>> queryList(@RequestParam(value = "code")String code, @RequestParam(value = "name")String name, @RequestParam(value = "paramType")String paramType);
}
