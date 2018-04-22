var rkWinObj;
require(['jquery',rkjsPath+'rkUtil.js',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js'], 
		function($,rkUtil,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore) {
	'use strict'
	var $adminMenuForm=$("#adminMenuForm");
	rkTags.choseBack.simpleChoseBack("permId_div","所属权限","permId","/views/sysManager/permInfo/perm/choseBack/choseBack.perm.list.html",function(){
		var perm=rkStore.rkChooseBcakParam.getParam();
		if(!rkUtil.rkStrUtil.isNull(perm)){
			$("#permId").val(perm[0].id);
			$("#permId_show").val(perm[0].permName);
			$("#hUrl").val(perm[0].permUrl);
		}
	});
	rkTags.choseBack.simpleChoseBack("moduleCode_div","所属模块","moduleCode","/views/sysManager/domain/module/choseBack/choseBack.module.list.html",function(){
		var module=rkStore.rkChooseBcakParam.getParam();
		$("#moduleCode").val(module[0].code);
		$("#moduleCode_show").val(module[0].name);
	});
	//初始化表单
	rkFormUtil.initForm($adminMenuForm);
	var menuObjs=[{id:"addNode",name:"添加节点",icon:"add",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		rkFormUtil.clearFormData($adminMenuForm);
		$adminMenuForm.find("#fatherCode").val(node.code);
	}},{id:"deleteNode",name:"删除节点",icon:"delete",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		rkAjax.postAjaxNoErr("/rk/admin/core/domain/adminMenu/delete",{idArr:[node.id]},function(data){
			rkAlert.succAlertMsg(data.msg);
			rkTree.refreshAll("adminMenuTree");
		});
	}}];
	var rkTreeObj=rkTree.initTree("adminMenuTree","/rk/admin/core/domain/adminMenu/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		rkAjax.postAjaxNoErr("/rk/admin/core/domain/adminMenu/queryMenuByCode",{code:code},function(data){
			rkFormUtil.fillFormData($adminMenuForm,data);
			rkFormUtil.fillChoseBackData($adminMenuForm,"/rk/admin/core/domain/module/query",{code:data.moduleCode},"moduleCode","name");
			if(!rkUtil.rkStrUtil.isNull(data.permId)){
				rkFormUtil.fillChoseBackData($adminMenuForm,"/rk/admin/core/user/perm/query",{id:data.permId},"permId","permName");
			}
			showOrHidePermShoose();
		});
	});
	var btns=rkTags.btn.saveBtn("adminMenuBtn",false,function(){//保存
		rkFormUtil.submitForm("adminMenuForm", "/rk/admin/core/domain/adminMenu/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			rkTree.refreshAll("adminMenuTree");
		});
	});
	$adminMenuForm.find("#type").on("change", function(e) {
		showOrHidePermShoose();
	});
	var showOrHidePermShoose=function(){
		var type=$adminMenuForm.find("#type").val();
		if(type=="2"){
			$adminMenuForm.find("#permId_div").show("slow");
		}else{
			$adminMenuForm.find("#permId_div").hide("slow");
		}
	}
});
