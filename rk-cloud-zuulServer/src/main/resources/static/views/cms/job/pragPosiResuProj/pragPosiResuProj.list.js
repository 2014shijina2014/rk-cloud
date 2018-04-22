var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var $searchForm=$("#searchForm");
	var jobPragPosiList=rkTable.init("jobPragPosiList", "/rk/admin/cms/job/pragPosi/queryPage",{},"radio","searchBtn");
	
	var jobPragPosiResuProjList=rkTable.init("jobPragPosiResuProjList", "/rk/admin/cms/job/pragPosiResuProj/queryPage",{},"radio","jobPragPosiResuProjSearchBtn");
	
	rkTable. clickRow("jobPragPosiList",jobPragPosiList,function(row,index,rowData){
		$("#jobPragPosiResuProjSearchForm").find("#pragPosiId").val(rowData.id);
		jobPragPosiResuProjList=rkTable.init("jobPragPosiResuProjList", "/rk/admin/cms/job/pragPosiResuProj/queryPage",{pragPosiId:rowData.id},"radio","jobPragPosiResuProjSearchBtn");
	});
	
	var btns=rkTags.btn.addBtnByName("tableDiv", true,"新加简历项目", function(){
		var ids=rkTable.getChecked("jobPragPosiList");
		if(rkUtil.rkStrUtil.isNull(ids)||ids.length<1){
			rkAlert.errAlertMsg("请先选择行业职位");
			return;
		}else{
			rkWin.win("/views/cms/job/resuProj/choseBack/resuProj.list.html",{},"新增简历项目",
				function(){
					var resuProj=rkStore.rkChooseBcakParam.getParam();
					if(!rkUtil.rkStrUtil.isNull(resuProj)&&!rkUtil.rkStrUtil.isNull(resuProj[0].code)){
						var formData={};
						formData.pragPosiId=ids[0];
						formData.resuProjCode=resuProj[0].code;
						rkAjax.postAjaxNoErr("/rk/admin/cms/job/pragPosiResuProj/save",formData,function(data){
							rkAlert.succAlertMsg(data.msg);
							rkTable.reload(jobPragPosiResuProjList);
						});
					}
				});
		}
	});
	
});
