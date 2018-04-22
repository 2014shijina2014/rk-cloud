require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	//加载数据
	var domainUserList=rkTable.init("domainUserList", "/rk/web/core/user/domainUser/queryPage",{},"radio","searchBtn");
	
	var showBtns=rkTags.btn.showBtn("tableDiv", true, function(){
		var datas=rkTable.getCheckedData(domainUserList,"domainUserList");
		if(rkUtil.rkStrUtil.isNull(datas)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/userManager/user/userInfo/userInfo.detail.html",{method:"show",userId:datas[0].userId,ajaxUrl:"/rk/admin/core/user/userInfo/query"},"查看用户信息",
		function(){
			rkTable.reload(domainUserList);
		});
	});
	var btns=rkTags.btn.delBtn("tableDiv", true, function(){
		var ids=rkTable.getChecked("domainUserList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/web/core/user/domainUser/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderUserType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"YHLXM");
}
