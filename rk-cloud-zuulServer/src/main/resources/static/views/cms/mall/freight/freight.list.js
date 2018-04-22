require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	rkFormUtil.initForm($("#searchForm"));
	//模版
	var mallFreightTemplateList=rkTable.init("mallFreightTemplateList", "/rk/admin/cms/mall/freightTemplate/queryPage",{},"radio","searchBtn");
	rkTable.clickRow("mallFreightTemplateList",mallFreightTemplateList,function(row,index,rowData){
		loadFreightPriceList(rowData.id);
	});
	var btns=rkTags.btn.addEditDelBtn("mallFreightTemplateTableDiv", true, function(){
		rkWin.win("/views/cms/mall/freight/freight.template.detail.html",{method:"add"},"新增运费模版",
		function(){
			rkTable.reload(mallFreightTemplateList);
		});
	}, function(){
		var ids=rkTable.getChecked("mallFreightTemplateList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/cms/mall/freight/freight.template.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/cms/mall/freightTemplate/query"},"编辑运费模版",
		function(){
			rkTable.reload(mallFreightTemplateList);
		});
	}, function(){
		var ids=rkTable.getChecked("mallFreightTemplateList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/cms/mall/freightTemplate/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
			rkTable.reload(mallFreightTemplateList);
		});
	});
	//价格
	var isExitPriceBtn=false
	var loadFreightPriceList=function(freightTplId){
		$("#mallFreightPriceTableDiv").show();
		var mallFreightPriceList=rkTable.init("mallFreightPriceList", "/rk/admin/cms/mall/freightPrice/queryPage",{freightTplId:freightTplId},"radio","searchBtn");
		if(!isExitPriceBtn){
			isExitPriceBtn=true;
			var btns=rkTags.btn.addEditDelBtn("mallFreightPriceTableDiv", true, function(){
				var ids=rkTable.getChecked("mallFreightTemplateList");
				if(rkUtil.rkStrUtil.isNull(ids)){
					rkAlert.errAlertMsg("请选择模版");
					return;
				}
				rkWin.win("/views/cms/mall/freight/freight.price.detail.html",{method:"add",freightTplId:ids[0]},"新增区域运费",
				function(){
					rkTable.reload(mallFreightPriceList);
				});
			}, function(){
				var ids=rkTable.getChecked("mallFreightPriceList");
				if(rkUtil.rkStrUtil.isNull(ids)){
					rkAlert.errAlertMsg("请选择一条记录");
					return;
				}
				rkWin.win("/views/cms/mall/freight/freight.price.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/cms/mall/freightPrice/query"},"编辑区域运费",
				function(){
					rkTable.reload(mallFreightPriceList);
				});
			}, function(){
				var ids=rkTable.getChecked("mallFreightPriceList");
				if(rkUtil.rkStrUtil.isNull(ids)){
					rkAlert.errAlertMsg("请至少选择一条记录");
					return;
				}
				rkAjax.postAjaxNoErr("/rk/admin/cms/mall/freightTemplate/delete",{idArr:ids},function(data){
					rkAlert.succAlert(data.msg);
					rkTable.reload(mallFreightPriceList);
				});
			});
		}
	};
});
function renderPackOff(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"FHSJLXM");
}
function renderValuation(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"YFJJFSM");
}
function renderFreightMethod(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"WLYSFSM");
}