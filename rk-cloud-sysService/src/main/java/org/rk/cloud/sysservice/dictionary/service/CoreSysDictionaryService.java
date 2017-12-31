package org.rk.cloud.sysservice.dictionary.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.rk.cloud.sysservice.dictionary.CoreSysDictionary;
import org.rk.cloud.sysservice.dictionary.dao.ICoreSysDictionaryDao;
import org.rk.core.common.util.RKAlert;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.pubServer.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CoreSysDictionaryService")
public class CoreSysDictionaryService extends ModelService<CoreSysDictionary> implements ICoreSysDictionaryService{

	private ICoreSysDictionaryDao modelDao;
	@Resource(name="CoreSysDictionaryDao")
	public void setCoreSysDictionaryDao(ICoreSysDictionaryDao modelDao) {
		super.setDbDao(modelDao);
		this.modelDao=modelDao;
	}
	@Transactional
	@Override
	public boolean deleteById(Long id){
		CoreSysDictionary coreSysDictionary=selectModel(id);
		if(coreSysDictionary.getCode().equals("-1")){
			RKAlert.Error("根节点不允许删除");
			return false;
		}
		return modelDao.deleteById(id);
	}
	@Override
	@Transactional
	public Long insertTreeModel(CoreSysDictionary coreSysDictionary){
		//获取当前最大的记录
		String maxId=String.valueOf(modelDao.queryMaxId(getPojoClass()));
		if(!RkStrUtil.hasText(coreSysDictionary.getFatherCode())){
			coreSysDictionary.setFatherCode("-1");//不指定父节点都是紧接着根节点
			coreSysDictionary.setCode(maxId+"A");
		}else{
			if(coreSysDictionary.getFatherCode().equals("-1")){
				coreSysDictionary.setCode(maxId+"A");
			}else{
				coreSysDictionary.setCode(coreSysDictionary.getFatherCode()+maxId+"A");
			}
		}
		return insertModel(coreSysDictionary);
	}
	
	@Override
	public List<CoreSysDictionary> selectListByDicCode(String dicCode){
		CoreSysDictionary fatherDic = selectModel("value", dicCode);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fatherCode", fatherDic.getCode());
		return selectModelList(params);
	}
	
	@Override
	public CoreSysDictionary selectListByDicCodeAndDicValue(String dicCode, String dicValue){
		if(RkStrUtil.isEmpty(dicCode)||RkStrUtil.isEmpty(dicValue)) {
			return null;
		}
		List<CoreSysDictionary> dicList = selectListByDicCode(dicCode);
		if(!CollectionUtils.isEmpty(dicList)) {
			for (CoreSysDictionary coreSysDictionary : dicList) {
				if(dicValue.equals(coreSysDictionary.getValue())) {
					return coreSysDictionary;
				}
			}
		}
		return null;
	}
}
