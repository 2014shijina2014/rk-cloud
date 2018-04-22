var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var $searchForm=$("#searchForm");
	var jobPragPosiList=rkTable.init("jobPragPosiList", "/rk/admin/cms/job/pragPosi/queryPage",{},"radio","searchBtn");
	var menuObjs=[{id:"addNode",name:"添加职位",icon:"add",clickEvent:function(){
				var node=rkStore.rkTreeRmenuParam.getParam();
				rkWin.win("/views/cms/job/position/choseBack/position.list.html",{},"添加职位",
				function(){
					var position=rkStore.rkChooseBcakParam.getParam();
					if(!rkUtil.rkStrUtil.isNull(position)&&!rkUtil.rkStrUtil.isNull(position[0].code)&&!rkUtil.rkStrUtil.isNull(node.code)){
						var formData={};
						formData.programaCode=node.code;
						formData.positionCode=position[0].code;
						rkAjax.postAjaxNoErr("/rk/admin/cms/job/pragPosi/save",formData,function(data){
							rkAlert.succAlertMsg(data.msg);
							rkTable.reload(jobPragPosiList);
						});
					}
				});
			}}
	];
	var rkTreeObj=rkTree.initTree("programaTree","/rk/admin/cms/job/programa/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		$searchForm.find("#programaCode").val(code);
		jobPragPosiList=rkTable.init("jobPragPosiList", "/rk/admin/cms/job/pragPosi/queryPage",{programaCode:code},"radio","searchBtn");
	});
});
