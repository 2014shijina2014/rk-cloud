require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	var winParam=rkStore.rkParam.getParam();
	if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.freightTplId)){
		$("#mallFreightPriceForm").find("#freightTplId").val(winParam.freightTplId);
	}
	//rkFormUtil.initForm($("#mallFreightPriceForm"));
	//按钮
	var btns=rkTags.btn.saveCloseBtn("mallFreightPriceBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("mallFreightPriceForm", "/rk/admin/cms/mall/freightPrice/save", function(data){
			rkAlert.succAlertMsg(data.msg);
			//关闭窗口
			parent.closeWin(true);
		});
	}, 
	function(){//关闭
		parent.closeWin(false);
	});
	//区域选择
	var areaUrl="/rk/admin/core/sys/area/queryChildByCode";
	rkTags.select.createNoSearchSelectByUrl($("#province_div"),"province","省/直辖市/自治区",areaUrl,{code:"11A"},function(){
		rkFormUtil.initForm($("#mallFreightPriceForm"));
		provinceChange();
	});
	var provinceChange=function(){
		$("#province").on("change",function(){
			var province=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(province)){
				$("#state_div").show("slow");
				rkTags.select.createNoSearchSelectByUrl($("#state_div"),"state","市/州",areaUrl,{code:province},function(){
					rkFormUtil.initForm($("#mallFreightPriceForm"));
					stateChange();
				});
				$("#area").val(province);
			}
		});
	}
	var stateChange=function(){
		$("#state").on("change",function(){
			var province=$("#province").val();
			var state=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(state)){
				$("#prefecture_div").show("slow");
				rkTags.select.createNoSearchSelectByUrl($("#prefecture_div"),"prefecture","县/区",areaUrl,{code:state},function(){
					rkFormUtil.initForm($("#mallFreightPriceForm"));
					prefectureChange();
				});
				$("#area").val(province+","+state);
			}
		});
	}
	var prefectureChange=function(){
		$("#prefecture").on("change",function(){
			var province=$("#province").val();
			var state=$("#state").val();
			var prefecture=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(prefecture)){
				$("#mallFreightPriceForm").find("#area").val(province+","+state+","+prefecture);
			}
		});
	}
});

