var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var $searchForm=$("#searchForm");
	var sysAreaList=rkTable.init("sysAreaList", "/rk/admin/core/sys/area/queryPage",{},"radio","searchBtn");
	var menuObjs=[{id:"addNode",name:"添加地域",icon:"add",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/sysManager/infoServicing/sysArea/sysArea.detail.html",{method:"add",fatherCode:node.code},"新增字典",
				function(){
					rkTable.reload(sysAreaList);
					rkTree.refreshAll("sysAreaTree");
				});
			}},{id:"editNode",name:"修改地域",icon:"delete",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/sysManager/infoServicing/sysArea/sysArea.detail.html",{method:"edit",ajaxUrl:"/rk/admin/core/sys/area/query",id:node.id},"修改字典",
						function(){
							rkTable.reload(sysAreaList);
							rkTree.refreshAll("sysAreaTree");
						});
			}},{id:"deleteNode",name:"删除地域",icon:"delete",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkAjax.postAjaxNoErr("/rk/admin/core/sys/area/delete",{idArr:[node.id]},function(data){
					rkAlert.succAlertMsg(data.msg);
					rkTree.refreshAll("sysAreaTree");
				});
			}}
	];
	var rkTreeObj=rkTree.initTree("sysAreaTree","/rk/admin/core/sys/area/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		$searchForm.find("#fatherCode").val(code);
		sysAreaList=rkTable.init("sysAreaList", "/rk/admin/core/sys/area/queryPage",{fatherCode:code},"radio","searchBtn");
	});
});
function renderAreaType(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"XZQYLXM");
}
