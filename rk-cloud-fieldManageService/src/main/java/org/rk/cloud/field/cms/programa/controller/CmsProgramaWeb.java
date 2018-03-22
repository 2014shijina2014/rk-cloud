/**
 * @Copyright caorendao187@163.com
 * @author cavion 
 */
package org.rk.cloud.field.cms.programa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rk.cloud.field.cms.programa.CmsPrograma;
import org.rk.cloud.field.cms.programa.service.ICmsProgramaService;
import org.rk.core.common.anno.docAnno.ApiDocAnno;
import org.rk.core.common.anno.docAnno.DocType;
import org.rk.core.common.bean.OrderBean;
import org.rk.core.common.bean.PageData;
import org.rk.core.common.util.RkObjectUtil;
import org.rk.core.common.util.RkStrUtil;
import org.rk.core.pubServer.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cavion（曹仁道）
 * 2017年1月26日
 */
@ApiDocAnno(name="CmsProgramaWeb",desc="开放端栏目分类", docType = DocType.TYPE)
@RestController
@RequestMapping("/rk/web/field/cms/programa/")
public class CmsProgramaWeb extends BaseController<CmsPrograma> {

	@Resource(name = "CmsProgramaService")
	private ICmsProgramaService cmsProgramaService;

	public Object queryPage(int start, int length, int draw, String moduleCode, Map<String, Object> params) {
		if(RkStrUtil.isEmpty(moduleCode)) {
			return ajaxError("模块编码不可为空");
		}
		params.put("moduleCode", moduleCode);
		PageData page = new PageData();
		page.setStart(start);
		page.setLength(length);
		page.setDraw(draw);
		page = cmsProgramaService.selectModelListPage(params, page);
		return page;
	}

	public Object queryTree(String moduleCode, Map<String, Object> params) {
		if(RkStrUtil.isEmpty(moduleCode)) {
			return ajaxError("模块编码不可为空");
		}
		params.put("moduleCode", moduleCode);
		List<OrderBean> orderList=new ArrayList<OrderBean>();
		orderList.add(new OrderBean("orderNum"));
		List<CmsPrograma> menuTree = cmsProgramaService.selectModelList(params, orderList);
		if(RkObjectUtil.isEmpty(menuTree)||menuTree.size()==0){
			menuTree=new ArrayList<CmsPrograma>();
		}
		// 追加根节点
		CmsPrograma pubPrograma = cmsProgramaService.selectModel("code", "0A");
		menuTree.add(pubPrograma);
		return menuTree;
	}

	@RequestMapping(value = "queryProgByCode", method = RequestMethod.POST)
	public Object queryProgByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		List<CmsPrograma> programaList = cmsProgramaService.selectModelList(params);
		if (RkObjectUtil.isEmpty(programaList)) {
			return ajaxError("没有找到数据");
		}
		return programaList.get(0);
	}

	public Object queryChildByCode(String code, String module) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code + "%");
		params.put("module", module);
		List<CmsPrograma> programaList = cmsProgramaService.selectModelList(params);
		if (RkObjectUtil.isEmpty(programaList)) {
			return ajaxError("没有找到数据");
		}
		return programaList;
	}

	@ApiDocAnno(name = "query", desc = "根据id查询栏目", docType = DocType.METHOD)
	@RequiresPermissions(value = "pubPrograma:query")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public Object queryById(Long id) {
		CmsPrograma pubPrograma = cmsProgramaService.selectModel(id);
		if (RkObjectUtil.isEmpty(pubPrograma)) {
			return ajaxError("没有找到数据");
		}
		return pubPrograma;
	}

	public ICmsProgramaService getCmsProgramaService() {
		return cmsProgramaService;
	}

	public void setCmsProgramaService(ICmsProgramaService cmsProgramaService) {
		this.cmsProgramaService = cmsProgramaService;
	}
	
	
}
