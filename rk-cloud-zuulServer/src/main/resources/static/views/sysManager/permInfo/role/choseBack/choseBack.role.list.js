require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js'], 
		function($,rkTree,rkTags,rkStore,rkUtil) {
	'use strict'
	var rkTreeObj=rkTree.initTree("roleTree","/rk/admin/core/user/role/queryTree",null,"radio",null,function(event, treeId, treeNode){
		var code=treeNode.code;
	});
	var btns=rkTags.btn.yesCloseBtn("yesBtn", false, function(){
		var node=rkTree.getCheckNode("roleTree");
		if(!rkUtil.rkStrUtil.isNull(node)){
			rkStore.rkChooseBcakParam.setParam(node);
			parent.closeWin(true);
		}
	}, function(){
		//关闭窗口
		parent.closeWin(false);
	});
});
