require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	//动态构建列表列数
	var userInfoList;
	rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfoKey/queryList",{},function(data){
		if(!rkUtil.rkStrUtil.isNull(data)&&data.length>0){
			var itemLength=data.length>5?5:data.length;
			var tag='';
			for(var i=0;i<itemLength;i++){
				tag+='<th style="min-width: 10%;" dataField="'+data[i].keyCode+'">'+data[i].keyName+'</th>';
			}
			$("#userInfoList thead").find("tr").append(tag);
		}
		//构建完成加载数据
		userInfoList=rkTable.init("userInfoList", "/rk/admin/core/user/userInfo/queryPage",{},"radio","searchBtn");
	});
	
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/userManager/user/userInfo/userInfo.detail.html",{method:"add"},"新增用户",
		function(){
			rkTable.reload(userInfoList);
		});
	}, function(){
		var ids=rkTable.getChecked("userInfoList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/userManager/user/userInfo/userInfo.detail.html",{method:"edit",userId:ids[0],ajaxUrl:"/rk/admin/core/user/userInfo/query"},"编辑用户信息",
		function(){
			rkTable.reload(userInfoList);
		});
	}, function(){
		var ids=rkTable.getChecked("userInfoList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfo/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderUserType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"YHLXM");
}
