var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var $searchForm=$("#searchForm");
	var dictionaryList=rkTable.init("dictionaryList", "/rk/admin/core/sys/dictionary/queryPage",{},"radio","searchBtn");
	var menuObjs=[{id:"addNode",name:"添加字典",icon:"add",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/sysManager/infoServicing/dictionary/dictionary.detail.html",{method:"add",fatherCode:node.code},"新增字典",
				function(){
					rkTable.reload(dictionaryList);
					rkTree.refreshAll("dictionaryTree");
				});
			}},{id:"editNode",name:"修改字典",icon:"delete",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/sysManager/infoServicing/dictionary/dictionary.detail.html",{method:"edit",ajaxUrl:"/rk/admin/core/sys/dictionary/query",id:node.id},"修改字典",
						function(){
							rkTable.reload(dictionaryList);
							rkTree.refreshAll("dictionaryTree");
						});
			}},{id:"deleteNode",name:"删除字典",icon:"delete",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkAjax.postAjaxNoErr("/rk/admin/core/sys/dictionary/delete",{idArr:[node.id]},function(data){
					rkAlert.succAlertMsg(data.msg);
					rkTree.refreshAll("dictionaryTree");
				});
			}}
	];
	var rkTreeObj=rkTree.initTree("dictionaryTree","/rk/admin/core/sys/dictionary/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		$searchForm.find("#fatherCode").val(code);
		dictionaryList=rkTable.init("dictionaryList", "/rk/admin/core/sys/dictionary/queryPage",{fatherCode:code},"radio","searchBtn");
	});
});
