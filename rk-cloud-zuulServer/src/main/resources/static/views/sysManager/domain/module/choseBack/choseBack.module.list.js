var rkWinObj;
require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil,rkStore) {
	'use strict'
	var moduleList=rkTable.init("moduleList", "/rk/admin/core/domain/module/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.yesCloseBtn("tableDiv", true, function(){
		var datas=rkTable.getCheckedData(moduleList,"moduleList");
		if(!rkUtil.rkStrUtil.isNull(datas)){
			rkStore.rkChooseBcakParam.setParam(datas);
			parent.closeWin(true);
		}
	}, function(){
		//关闭窗口
		parent.closeWin(false);
	});
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