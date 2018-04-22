require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js',rkjsPath+'rkAjax.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin,rkAjax) {
	'use strict'
	//动态构建表单
	var $form=$("#unitInfoForm");
	rkTags.dynamicCreateForm($form,"/rk/admin/core/unit/unitInfoKey/queryList",{},function(){
		rkFormUtil.initForm($form);
		loadData();
	});
	//填充数据
	var loadData=function(){
		var winParam=rkStore.rkParam.getParam();
		if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.unitId)){
			rkAjax.postAjaxNoErr("/rk/admin/core/unit/unitInfo/query",{unitId:winParam.unitId},function(data){
				if(!rkUtil.rkStrUtil.isNull(data)){
					rkFormUtil.fillFormData($("#unitInfoForm"),data);
				}
			});
		}
	}
	//按钮
	var btns=rkTags.btn.saveCloseBtn("unitInfoBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("unitInfoForm", "/rk/admin/core/unit/unitInfo/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	
});

