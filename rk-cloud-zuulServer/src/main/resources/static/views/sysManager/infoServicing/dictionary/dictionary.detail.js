require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	//根据系统参数来确定备用字段是什么
	rkAjax.postAjaxNoErr("/rk/web/core/sys/param/queryList",{code:"bakfield_dictionary_",name:"",paramType:"bakfield"},function(data){
		if(null != data && data.length>0){
			for(let i=0;i<data.length;i++){
				let fieldCode = data[i].code;
				let fieldCodeArr = fieldCode.split("_");
				let fieldIndex = fieldCodeArr[fieldCodeArr.length-1];
				$("#dictionaryForm").append('<rk-input type="text" name="bakField'+fieldIndex+'" label="'+data[i].value+'"></rk-input>');
			}
		}
		
		//初始化表单
		rkFormUtil.initForm($("#dictionaryForm"));
		var btns=rkTags.btn.saveCloseBtn("dictionaryBtn", false, 
		function(){
			//保存
			rkFormUtil.submitForm("dictionaryForm", "/rk/admin/core/sys/dictionary/save", function(data){
				rkAlert.succAlertMsg(data.msg);
				//关闭窗口
				parent.closeWin(true);
			});
		}, 
		function(){//关闭
			parent.closeWin(false);
		});
	});
	
});

