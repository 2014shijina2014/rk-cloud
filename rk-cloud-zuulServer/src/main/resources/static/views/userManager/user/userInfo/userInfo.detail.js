require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js',rkjsPath+'rkAjax.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin,rkAjax) {
	'use strict'
	//动态构建表单
	var $form=$("#userInfoForm");
	rkTags.dynamicCreateForm($form,"/rk/admin/core/user/userInfoKey/queryList",{},function(){
		rkFormUtil.initForm($form);
		loadData();
	});
	//填充数据
	var loadData=function(){
		var winParam=rkStore.rkParam.getParam();
		if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.userId)){
			rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfo/query",{userId:winParam.userId},function(data){
				if(!rkUtil.rkStrUtil.isNull(data)){
					//rkFormUtil.fillFormData($("#userForm"),data);
					rkFormUtil.fillFormData($("#userInfoForm"),data);
				}
			});
		}
	}
	//按钮
	var btns=rkTags.btn.saveCloseBtn("userInfoBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("userInfoForm", "/rk/admin/core/user/userInfo/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	
});

