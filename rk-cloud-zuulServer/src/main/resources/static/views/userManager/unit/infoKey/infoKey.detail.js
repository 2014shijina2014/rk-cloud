require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	rkFormUtil.initForm($("#infoKeyForm"),function(){
		$("#selectType_div").hide("slow");
		$("#selectCode_div").hide("slow");
	});
	
	//数据类型onchange事件
	$("#dataType").on("change",function(){
		var dataType=$(this).val();
		if(!rkUtil.rkStrUtil.isNull(dataType)&&dataType=="3"){
			$("#selectType_div").show("slow");
			$("#selectCode_div").show("slow");
		}else{
			$("#selectType_div").hide("slow");
			$("#selectCode_div").hide("slow");
		}
	});
	
	//按钮
	var btns=rkTags.btn.saveCloseBtn("infoKeyBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("infoKeyForm", "/rk/admin/core/unit/unitInfoKey/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	
});

