require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	rkFormUtil.initForm($("#infoKeyForm"),function(){
		$("#select_selectType_div").hide("slow");
		$("#select_selectCode_div").hide("slow");
		$("#select_selectDataType_div").hide("slow");
	});
	//数据类型onchange事件
	$("#dataType").on("change",function(){
		var dataType=$(this).val();
		if(!rkUtil.rkStrUtil.isNull(dataType)&&dataType=="3"){
			$("#select_selectType_div").show("slow");
			$("#select_selectCode_div").show("slow");
			$("#select_selectDataType_div").show("slow");
		}else{
			$("#select_selectType_div").hide("slow");
			$("#select_selectCode_div").hide("slow");
			$("#select_selectDataType_div").hide("slow");
		}
	});
	
	//按钮
	var btns=rkTags.btn.saveCloseBtn("infoKeyBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("infoKeyForm", "/rk/admin/core/user/userInfoKey/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	
});

