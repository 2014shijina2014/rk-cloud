var rkWinObj;
require(['jquery',rkjsPath+'rkTree.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js'], 
		function($,rkTree,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkStore,rkUtil) {
	'use strict'
	var $roleForm=$("#roleForm");
	//初始化表单
	rkFormUtil.initForm($roleForm);
	var menuObjs=[{id:"addNode",name:"添加节点",icon:"add",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		rkFormUtil.clearFormData($roleForm);
		$roleForm.find("#fatherCode").val(node.code);
	}},{id:"deleteNode",name:"删除节点",icon:"delete",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		rkAjax.postAjaxNoErr("/rk/admin/core/domain/adminMenu/delete",{idArr:[node.id]},function(data){
			rkAlert.succAlertMsg(data.msg);
			rkTree.refreshAll("roleTree");
		});
	}},{id:"fenpeiPerm",name:"分配权限",icon:"setting",clickEvent:function(){
		var node=rkStore.rkTreeRmenuParam.getParam();
		console.log(node);
		rkWin.win("/views/sysManager/permInfo/perm/choseBack/choseBack.perm.list.html",{},"分配权限",function(){
			var role=rkStore.rkChooseBcakParam.getParam();
			for(var i=0;!rkUtil.rkStrUtil.isNull(role)&&i<role.length;i++){
				rkAjax.postAjaxNoErr("/rk/admin/core/user/rolePerm/save",{roleId:node.id,permId:role[i].id},function(data){
					rkAlert.succAlertMsg(data.msg);
				});
			}
		});
	}}];
	var rkTreeObj=rkTree.initTree("roleTree","/rk/admin/core/user/role/queryTree",null,"radio",menuObjs,function(event, treeId, treeNode){
		var code=treeNode.code;
		rkAjax.postAjaxNoErr("/rk/admin/core/user/role/queryRoleByCode",{code:code},function(data){
			rkFormUtil.fillFormData($roleForm,data);
		});
	});
	var btns=rkTags.btn.saveBtn("roleBtn",false,function(){//保存
		rkFormUtil.submitForm("roleForm", "/rk/admin/core/user/role/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			rkTree.refreshAll("roleTree");
		});
	});
});
