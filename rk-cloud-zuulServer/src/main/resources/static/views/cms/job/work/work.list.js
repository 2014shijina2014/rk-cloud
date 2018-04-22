require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTab.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkTab) {
	'use strict'
	var jobList=rkTable.init("jobList", "/rk/admin/cms/job/work/queryPage",{},"checkbox","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		var tabObj={id:"menuTab_work_add",title:"新增工作",url:"/views/cms/job/work/work.detail.html",isClose:true,param:{}};
		rkTab.addTabs(tabObj);
	}, function(){
		var ids=rkTable.getChecked("jobList");
		if(ids.length<1||ids.length>1){
			rkAlert.errAlert("请选择一条数据");
		}
		var tabObj={id:"menuTab_work_edit",title:"编辑工作",url:"/views/cms/job/work/work.detail.html",isClose:true,param:{id:ids[0]}};
		rkTab.addTabs(tabObj);
	}, function(){
		var ids=rkTable.getChecked("workList");
		if(ids.length>0){
			rkAlert.confirmDelAlert(function(){
				rkAjax.postAjaxNoErr("/rk/admin/cms/job/work/delete",{idArr:ids},function(data){
					rkAlert.succAlert(data.msg);
				});
			});
		}
	});
});
function renderWorkType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"GZLXM");
}
function renderWelfare(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"GZFLLXM");
}
function renderWorkYear(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"GZNXM");
}
function renderGraduation(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"GZXLLXM");
}
function renderEmolument(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"GZXCLXM");
}
