require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax) {
	var domainParamList=rkTable.init("domainParamList", "/rk/admin/core/domain/domainParam/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/sysManager/domain/domainParam/domainParam.detail.html",{method:"add"},"新增站点参数",
		function(){
			rkTable.reload(domainParamList);
		});
	}, function(){
		var ids=rkTable.getChecked("domainParamList");
		rkWin.win("/views/sysManager/domain/domainParam/domainParam.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/core/domain/domainParam/query"},"编辑站点参数",
		function(){
			rkTable.reload(domainParamList);
		});
	}, function(){
		var ids=rkTable.getChecked("domainParamList");
		rkAjax.postAjaxNoErr("/rk/admin/core/domain/domainParam/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
			rkTable.reload(domainParamList);
		});
	});
	
});
function renderDomain(data, type, row, meta){
	rkTableObj.tableRender.renderByUrl(row,meta,"/rk/admin/core/domain/domain/query",{id:data});
}