require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var jobResuProjItemList=rkTable.init("jobResuProjItemList", "/rk/admin/cms/job/resuProjItem/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/cms/job/resuProjItem/resuProjItem.detail.html",{method:"add"},"新增简历项目子项",
		function(){
			rkTable.reload(jobResuProjItemList);
		});
	}, function(){
		var ids=rkTable.getChecked("jobResuProjItemList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/cms/job/resuProjItem/resuProjItem.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/cms/job/resuProjItem/query"},"编辑项目子项",
		function(){
			rkTable.reload(jobResuProjItemList);
		});
	}, function(){
		var ids=rkTable.getChecked("jobResuProjItemList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/cms/job/resuProjItem/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderDataType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"TYSJLXM");
}
function renderSelectType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"TYSJXZLXM");
}