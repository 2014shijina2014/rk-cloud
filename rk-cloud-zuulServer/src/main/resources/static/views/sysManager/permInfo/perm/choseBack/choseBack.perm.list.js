require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil) {
	'use strict'
	var permList=rkTable.init("permList", "/rk/admin/core/user/perm/queryPage",{type:"2"},"checkbox","searchBtn");
	var btns=rkTags.btn.yesCloseBtn("tableDiv", true, function(){
		var datas=rkTable.getCheckedData(permList,"permList");
		if(!rkUtil.rkStrUtil.isNull(datas)){
			rkStore.rkChooseBcakParam.setParam(datas);
			parent.closeWin(true);
		}
	}, function(){
		//关闭窗口
		parent.closeWin(false);
	});
});
function renderEnabled(data, type, row, meta){
	if(data=="1"){
		return "是";
	}else{
		return "否";
	}
}
function renderType(data, type, row, meta){
	if(data=="1"){
		return "操作";
	}else if(data=="2"){
		return "菜单";
	}
}