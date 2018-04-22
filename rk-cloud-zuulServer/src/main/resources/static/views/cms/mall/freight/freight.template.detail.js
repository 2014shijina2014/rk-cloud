require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	rkFormUtil.initForm($("#mallFreightTemplateForm"));
	//按钮
	var btns=rkTags.btn.saveCloseBtn("mallFreightTemplateBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("mallFreightTemplateForm", "/rk/admin/cms/mall/freightTemplate/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	
});

