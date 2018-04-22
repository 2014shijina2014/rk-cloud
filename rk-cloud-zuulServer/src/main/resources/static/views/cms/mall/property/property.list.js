require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var mallPropertyList=rkTable.init("mallPropertyList", "/rk/admin/cms/mall/property/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/cms/mall/property/property.detail.html",{method:"add"},"新增属性项",
		function(){
			rkTable.reload(mallPropertyList);
		});
	}, function(){
		var ids=rkTable.getChecked("mallPropertyList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/cms/mall/property/property.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/cms/mall/property/query"},"编辑属性项",
		function(){
			rkTable.reload(mallPropertyList);
		});
	}, function(){
		var ids=rkTable.getChecked("mallPropertyList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/cms/mall/property/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
