var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTable.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil,rkTable) {
	'use strict'
	var btns=rkTags.btn.yesCloseBtn("programaTreeDiv", true, function(){
		var nodes=rkTree.getCheckNodes("programaTree");
		if(!rkUtil.rkStrUtil.isNull(nodes)){
			rkStore.rkChooseBcakParam.setParam(nodes);
			parent.closeWin(true);
		}
	}, function(){
		parent.closeWin(false);
	});
	var rkTreeObj=rkTree.initTree("programaTree","/rk/admin/cms/mall/programa/queryTree",null,"radio",null,function(event, treeId, treeNode){
		//var code=treeNode.code;
	});
});
