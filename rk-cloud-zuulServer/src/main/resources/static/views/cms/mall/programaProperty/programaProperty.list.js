var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var $searchForm=$("#searchForm");
	var programaPropertyList=rkTable.init("programaPropertyList", "/rk/admin/cms/mall/programaProperty/queryPage",{},"radio","searchBtn");
	var menuObjs=[{id:"addNode",name:"添加属性",icon:"add",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/cms/mall/property/choseBack/property.list.html",{},"添加属性",
				function(){
					var property=rkStore.rkChooseBcakParam.getParam();
					if(!rkUtil.rkStrUtil.isNull(property)&&!rkUtil.rkStrUtil.isNull(property[0].code)&&!rkUtil.rkStrUtil.isNull(node.code)){
						var formData={};
						formData.programaCode=node.code;
						formData.propertyCode=property[0].code;
						rkAjax.postAjaxNoErr("/rk/admin/cms/mall/programaProperty/save",formData,function(data){
							rkAlert.succAlertMsg(data.msg);
							rkTable.reload(programaPropertyList);
							//rkTree.refreshAll("programaTree");
						});
					}
				});
			}}
	];
	var rkTreeObj=rkTree.initTree("programaTree","/rk/admin/cms/mall/programa/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		$searchForm.find("#programaCode").val(code);
		programaPropertyList=rkTable.init("programaPropertyList", "/rk/admin/cms/mall/programaProperty/queryPage",{programaCode:code},"radio","searchBtn");
	});
});
