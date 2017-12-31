package org.rk.cloud.officeservice.excel.mapping.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.rk.cloud.officeservice.excel.mapping.dao.IToolOfficeExcelMappingDao;
import org.rk.core.pubServer.service.ModelService;
import org.rk.core.tools.office.mapping.ToolOfficeExcelMapping;
import org.rk.core.tools.office.mapping.service.IToolOfficeExcelMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ToolOfficeExcelMappingService")
public class ToolOfficeExcelMappingService extends ModelService<ToolOfficeExcelMapping> implements IToolOfficeExcelMappingService{

	private IToolOfficeExcelMappingDao modelDao;
	@Resource(name="ToolOfficeExcelMappingDao")
	public void setToolOfficeExcelMappingDao(IToolOfficeExcelMappingDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Override
	@Transactional
	public boolean deleteById(Long id){
		return modelDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public List<ToolOfficeExcelMapping> selectExcelMappingList(String opraType,String imOrEx){
		Map<String ,Object> mapObjs=new HashMap<String, Object>();
		mapObjs.put("opraType", opraType);
		mapObjs.put("imOrEx", imOrEx);
		return selectModelList(mapObjs);
	}
}
