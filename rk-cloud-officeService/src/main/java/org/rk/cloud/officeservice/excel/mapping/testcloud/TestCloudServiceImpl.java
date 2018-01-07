package org.rk.cloud.officeservice.excel.mapping.testcloud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rk.cloud.common.hystrix.CommonHystrix;
import org.springframework.stereotype.Component;

@Component
public class TestCloudServiceImpl implements ITestCloudService{

	@Override
	public List<Map<String, Object>> queryList(String code, String name, String paramType) {
		List<Map<String, Object>> result = CommonHystrix.hystrixMapList();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		paramMap.put("name", name);
		paramMap.put("paramType", paramType);
		result.add(paramMap);
		return result;
	}

}
