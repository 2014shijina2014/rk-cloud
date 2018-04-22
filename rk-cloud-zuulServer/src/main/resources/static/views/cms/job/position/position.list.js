require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var jobPositionList=rkTable.init("jobPositionList", "/rk/admin/cms/job/position/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/cms/job/position/position.detail.html",{method:"add"},"新增职位",
		function(){
			rkTable.reload(jobPositionList);
		});
	}, function(){
		var ids=rkTable.getChecked("jobPositionList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/cms/job/position/position.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/cms/job/position/query"},"编辑职位",
		function(){
			rkTable.reload(jobPositionList);
		});
	}, function(){
		var ids=rkTable.getChecked("jobPositionList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/cms/job/position/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
