require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var userInfoKeyList=rkTable.init("userInfoKeyList", "/rk/admin/core/user/userInfoKey/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/userManager/user/infoKey/infoKey.detail.html",{method:"add"},"新增用户信息扩展项",
		function(){
			rkTable.reload(userInfoKeyList);
		});
	}, function(){
		var ids=rkTable.getChecked("userInfoKeyList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/userManager/user/infoKey/infoKey.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/core/user/userInfoKey/query"},"编辑用户信息扩展项",
		function(){
			rkTable.reload(userInfoKeyList);
		});
	}, function(){
		var ids=rkTable.getChecked("userInfoKeyList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfoKey/delete",{idArr:ids},function(data){
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
