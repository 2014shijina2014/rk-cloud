require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	//动态构建列表列数
	var unitInfoList;
	rkAjax.postAjaxNoErr("/rk/admin/core/unit/unitInfoKey/queryList",{},function(data){
		if(!rkUtil.rkStrUtil.isNull(data)&&data.length>0){
			var itemLength=data.length>4?4:data.length;
			var tag='';
			for(var i=0;i<itemLength;i++){
				tag+='<th style="min-width: 10%;" dataField="'+data[i].keyCode+'">'+data[i].keyName+'</th>';
			}
			$("#unitInfoList thead").find("tr").append(tag);
		}
		//构建完成加载数据
		unitInfoList=rkTable.init("unitInfoList", "/rk/admin/core/unit/unitInfo/queryPage",{},"radio","searchBtn");
	});
	
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/userManager/unit/unitInfo/unitInfo.detail.html",{method:"add"},"新增单位",
		function(){
			rkTable.reload(unitInfoList);
		});
	}, function(){
		var ids=rkTable.getChecked("unitInfoList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/userManager/unit/unitInfo/unitInfo.detail.html",{method:"edit",unitId:ids[0],ajaxUrl:"/rk/admin/core/unit/unitInfo/query"},"编辑单位信息",
		function(){
			rkTable.reload(unitInfoList);
		});
	}, function(){
		var ids=rkTable.getChecked("unitInfoList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/core/unit/unitInfo/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderUnitType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"DWXZDM");
}
