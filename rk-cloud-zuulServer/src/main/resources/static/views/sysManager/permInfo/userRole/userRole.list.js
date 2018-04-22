require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil,rkStore) {
	'use strict'
	var coreUserList=rkTable.init("coreUserList", "/rk/admin/core/user/user/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addBtnByName("tableDiv", true,"分配角色", function(){
		var datas=rkTable.getCheckedData(coreUserList,"coreUserList");
		if(!rkUtil.rkStrUtil.isNull(datas)){
			var userId=datas[0].id;
			rkWin.win("/views/sysManager/permInfo/role/choseBack/choseBack.role.list.html",{},"分配角色",function(){
				var role=rkStore.rkChooseBcakParam.getParam();
				rkAjax.postAjaxNoErr("/rk/admin/core/user/userRole/save",{userId:userId,roleId:role.id},function(data){
					rkAlert.succAlertMsg(data.msg);
					//rkTable.reload(permList);
				});
			});
		}
	});
	var exportBtns=rkTags.btn.addBtnByName("tableDiv", true,"导出用户信息", function(){
		location.href="/rk/admin/core/user/user/exportUserInfo";
		//rkAjax.postAjaxNoErr("/rk/admin/core/user/userRole/save",{userId:userId,roleId:role.id},function(data){
			//rkAlert.succAlertMsg(data.msg);
			//rkTable.reload(permList);
		//});
	});
});
function renderUserType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"YHLXM");
}