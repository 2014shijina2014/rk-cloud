require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	rkFormUtil.initForm($("#domainParamForm"));
	/*rkTags.select.createSelectByUrl("domain_div","所属站点","domainId",true,"/rk/admin/core/domain/domain/queryList",{},function(){
		//初始化表单
		rkFormUtil.initForm($("#domainParamForm"));
	});*/
	var btns=rkTags.btn.saveCloseBtn("domainParamBtn", false, 
	function(){
		//保存
		rkFormUtil.submitForm("domainParamForm", "/rk/admin/core/domain/domainParam/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	
});

