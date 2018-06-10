require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var domainList=rkTable.init("domainList", "/userservice/rk/admin/core/domain/domain/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/sysManager/domain/domain/domain.detail.html",{method:"add"},"新增站点",
		function(){
			rkTable.reload(domainList);
		});
	}, function(){
		var ids=rkTable.getChecked("domainList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/sysManager/domain/domain/domain.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/userservice/rk/admin/core/domain/domain/query"},"编辑站点",
		function(){
			rkTable.reload(domainList);
		});
	}, function(){
		var ids=rkTable.getChecked("domainList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/userservice/rk/admin/core/domain/domain/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderIsValid(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"SF");
}
function renderOwnerType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"ZDYYRLXM");
}