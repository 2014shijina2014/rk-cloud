var rkWinObj;
require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax) {
	var moduleList=rkTable.init("moduleList", "/rk/admin/core/domain/module/queryPage",{},"checkbox","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/sysManager/domain/module/module.detail.html",{method:"add"},"新增模块",
		function(){
			rkTable.reload(moduleList);
		});
	}, function(){
		var ids=rkTable.getChecked("moduleList");
	}, function(){
		var ids=rkTable.getChecked("moduleList");
		rkAjax.postAjaxNoErr("/rk/admin/core/domain/module/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
	rkWinObj=rkWin;
});
function closeWin(isLazy){
	rkWinObj.closeWin(isLazy);
}
function renderIsValid(data, type, row, meta){
	if(data=="1"){
		return "是";
	}else{
		return "否";
	}
}