require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var jobResuProjList=rkTable.init("jobResuProjList", "/rk/admin/cms/job/resuProj/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/cms/job/resuProj/resuProj.detail.html",{method:"add"},"新增简历项目",
		function(){
			rkTable.reload(jobResuProjList);
		});
	}, function(){
		var ids=rkTable.getChecked("jobResuProjList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/cms/job/resuProj/resuProj.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/cms/job/resuProj/query"},"编辑简历项目",
		function(){
			rkTable.reload(jobResuProjList);
		});
	}, function(){
		var ids=rkTable.getChecked("jobResuProjList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/cms/job/resuProj/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
