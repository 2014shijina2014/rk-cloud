var rkWinObj;
require(['jquery',rkjsPath+'rkUtil.js',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js'], 
		function($,rkUtil,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore) {
	'use strict'
	var $userWebPermForm=$("#userWebPermForm");
	rkTags.choseBack.simpleChoseBack("moduleCode_div","所属模块","moduleCode","/views/sysManager/domain/module/choseBack/choseBack.module.list.html",function(){
		var module=rkStore.rkChooseBcakParam.getParam();
		$("#moduleCode").val(module[0].code);
		$("#moduleCode_show").val(module[0].name);
	});
	//初始化表单
	rkFormUtil.initForm($userWebPermForm);
	var menuObjs=[{id:"addNode",name:"添加菜单",icon:"add",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		rkFormUtil.clearFormData($userWebPermForm);
		$userWebPermForm.find("#fatherCode").val(node.code);
	}},{id:"deleteNode",name:"删除菜单",icon:"delete",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		rkAjax.postAjaxNoErr("/rk/admin/core/userWeb/userWebPerm/delete",{idArr:[node.id]},function(data){
			rkAlert.succAlertMsg(data.msg);
			rkTree.refreshAll("userWebPermTree");
		});
	}}];
	var rkTreeObj=rkTree.initTree("userWebPermTree","/rk/admin/core/userWeb/userWebPerm/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		rkAjax.postAjaxNoErr("/rk/admin/core/userWeb/userWebPerm/queryMenuByCode",{code:code},function(data){
			rkFormUtil.fillFormData($userWebPermForm,data);
			if(rkUtil.rkStrUtil.isNull(data)||rkUtil.rkStrUtil.isNull(data.moduleCode)){
				return;
			}
			rkFormUtil.fillChoseBackData($userWebPermForm,"/rk/admin/core/domain/module/query",{code:data.moduleCode},"moduleCode","name");
		});
	});
	var btns=rkTags.btn.saveBtn("userWebPermBtn",false,function(){//保存
		rkFormUtil.submitForm("userWebPermForm", "/rk/admin/core/userWeb/userWebPerm/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			rkTree.refreshAll("userWebPermTree");
		});
	});
});
