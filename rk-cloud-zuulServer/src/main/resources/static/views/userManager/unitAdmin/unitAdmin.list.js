require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var unitAdminList=rkTable.init("unitAdminList", "/rk/admin/core/unit/unitAdmin/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addBtn("tableDiv", true, function(){
		rkWin.win("/views/userManager/unit/infoKey/infoKey.detail.html",{method:"add"},"新增新增用户",
		function(){
			rkTable.reload(unitAdminList);
		});
	});
});
function renderUnitType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"DWXZDM");
}
