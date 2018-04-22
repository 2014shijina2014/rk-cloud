require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax) {
	var sysParamList=rkTable.init("sysParamList", "/rk/admin/core/sys/param/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/sysManager/infoServicing/sysParam/sysParam.detail.html",{method:"add"},"新增系统参数",
		function(){
			rkTable.reload(sysParamList);
		});
	}, function(){
		var ids=rkTable.getChecked("sysParamList");
		rkWin.win("/views/sysManager/infoServicing/sysParam/sysParam.detail.html",{method:"edit",id:ids[0]},"编辑系统参数",
		function(){
			rkTable.reload(sysParamList);
		});
	}, function(){
		var ids=rkTable.getChecked("sysParamList");
		rkAjax.postAjaxNoErr("/rk/admin/core/sys/param/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
