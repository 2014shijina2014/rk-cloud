require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkUtil.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkUtil) {
	var userAddressList=rkTable.init("userAddressList", "/rk/admin/core/user/userAddress/queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		rkWin.win("/views/userManager/userAddress/userAddress.detail.html",{method:"add"},"新增收货人",
		function(){
			rkTable.reload(userAddressList);
		});
	}, function(){
		var ids=rkTable.getChecked("userAddressList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请选择一条记录");
			return;
		}
		rkWin.win("/views/userManager/userAddress/userAddress.detail.html",{method:"edit",id:ids[0],ajaxUrl:"/rk/admin/core/user/userAddress/query"},"编辑收货人",
		function(){
			rkTable.reload(userAddressList);
		});
	}, function(){
		var ids=rkTable.getChecked("userAddressList");
		if(rkUtil.rkStrUtil.isNull(ids)){
			rkAlert.errAlertMsg("请至少选择一条记录");
			return;
		}
		rkAjax.postAjaxNoErr("/rk/admin/core/user/userAddress/delete",{idArr:ids},function(data){
			rkAlert.succAlert(data.msg);
		});
	});
});
function renderAddress(data, type, row, meta){
	return row.area+" "+data;
}
