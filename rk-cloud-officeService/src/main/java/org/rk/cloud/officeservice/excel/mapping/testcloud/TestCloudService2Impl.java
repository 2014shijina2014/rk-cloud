package org.rk.cloud.officeservice.excel.mapping.testcloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TestCloudService2Impl implements ITestCloudService2{

	@Override
	public List<Map<String, Object>> queryList(String keyCode, String keyName) {
		List<Map<String, Object>> errorList= new ArrayList<Map<String, Object>>();
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("errorCode", "404");
		errorMap.put("errorMsg", "断路器生效");
		errorList.add(errorMap);
		return errorList;
	}

}
