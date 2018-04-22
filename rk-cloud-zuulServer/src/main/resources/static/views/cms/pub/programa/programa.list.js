var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var $searchForm=$("#searchForm");
	var programaList=rkTable.init("programaList", "/rk/admin/cms/article/programa/queryPage",{},"radio","searchBtn");
	var menuObjs=[{id:"addNode",name:"添加栏目",icon:"add",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/cms/article/programa/programa.detail.html",{method:"add",fatherCode:node.code},"新增栏目",
				function(){
					rkTable.reload(programaList);
					rkTree.refreshAll("programaTree");
				});
			}},{id:"editNode",name:"修改栏目",icon:"delete",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/cms/article/programa/programa.detail.html",{method:"edit",ajaxUrl:"/rk/cms/article/programa/queryById",id:node.id},"修改栏目",
						function(){
							rkTable.reload(programaList);
							rkTree.refreshAll("programaTree");
						});
			}},{id:"deleteNode",name:"删除栏目",icon:"delete",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkAjax.postAjaxNoErr("/rk/admin/cms/article/programa/delete",{idArr:[node.id]},function(data){
					rkAlert.succAlertMsg(data.msg);
					rkTree.refreshAll("programaTree");
				});
			}}
	];
	var rkTreeObj=rkTree.initTree("programaTree","/rk/admin/cms/article/programa/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		$searchForm.find("#fatherCode").val(code);
		programaList=rkTable.init("programaList", "/rk/admin/cms/article/programa/queryPage",{fatherCode:code},"radio","searchBtn");
	});
});
function renderModule(data, type, row, meta){
	return rkTableObj.tableRender.renderByUrl(row,meta,"/rk/admin/core/domain/module/query",{code:data});
}