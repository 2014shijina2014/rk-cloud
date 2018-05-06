require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var propertyList=rkTable.init("propertyList", "/fieldmanageservice/rk/admin/field/cms/property/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/fieldManager/property/property.detail.html",{method:"add"},"新增属性项",
		function(){
			rkTable.reload(propertyList);
		});
	}, function(){
		var ids=rkTable.getChecked("propertyList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/fieldManager/property/property.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/fieldmanageservice/rk/admin/field/cms/property/query"},"编辑属性项",
		function(){
			rkTable.reload(propertyList);
		});
	}, function(){
		var ids=rkTable.getChecked("propertyList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/fieldmanageservice/rk/admin/field/cms/property/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
