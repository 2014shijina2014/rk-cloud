require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax) {
	'use strict'
	var permList=rkTable.init("permList", "/rk/core/user/perm/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/sysManager/perm/perm.detail.html",{method:"add"},"新增权限",
		function(){
			rkTable.reload(permList);
		});
	}, function(){
		var ids=rkTable.getChecked("permList");
		rkWin.win("/views/sysManager/perm/perm.detail.html",{method:"edit",ajaxUrl:"",id:ids[0]},"编辑权限",
		function(){
			rkTable.reload(permList);
		});
	}, function(){
		var ids=rkTable.getChecked("permList");
		if(ids.length>0){
			rkAjax.postAjaxNoErr("/rk/core/user/perm/delete",{idArr:ids},function(data){
				rkAlert.succAlert(data.msg);
			});
		}
	});
	//rkWinObj=rkWin;
});
function renderEnabled(data, type, row, meta){
	if(data=="1"){
		return "是";
	}else{
		return "否";
	}
}
function renderType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"QXLXM");
}