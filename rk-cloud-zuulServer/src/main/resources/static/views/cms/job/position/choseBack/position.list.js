require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil,rkStore) {
	var jobPositionList=rkTable.init("jobPositionList", "/rk/admin/cms/job/position/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.yesCloseBtn("tableDiv", true, function(){
		var datas=rkTable.getCheckedData(jobPositionList,"jobPositionList");
		if(!rkUtil.rkStrUtil.isNull(datas)){
			rkStore.rkChooseBcakParam.setParam(datas);
			parent.closeWin(true);
		}
	}, function(){
		parent.closeWin(false);
	});
});
