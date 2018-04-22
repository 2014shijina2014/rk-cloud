require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax) {
	var sysParamList=rkTable.init("excelMappingList", "/rk/admin/tool/office/excel/mapping/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/tool/office/excelMapping/excelMapping.detail.html",{method:"add"},"新增映射",
		function(){
			rkTable.reload(sysParamList);
		});
	}, function(){
		var ids=rkTable.getChecked("excelMappingList");
		rkWin.win("/views/tool/office/excelMapping/excelMapping.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/tool/office/excel/mapping/query"},"编辑映射",
		function(){
			rkTable.reload(sysParamList);
		});
	}, function(){
		var ids=rkTable.getChecked("excelMappingList");
		rkAjax.postAjaxNoErr("/rk/admin/tool/office/excel/mapping/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderOpraType(data, type, row, meta){
	rkTableObj.tableRender.renderDic(data,row,meta,"EXCELMAPPINGM","");
}
function renderImOrEx(data, type, row, meta){
	rkTableObj.tableRender.renderDic(data,row,meta,"DRHDCM","");
}