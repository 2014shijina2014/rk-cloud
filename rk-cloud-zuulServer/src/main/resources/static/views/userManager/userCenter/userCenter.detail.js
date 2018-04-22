require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js',rkjsPath+'rkAjax.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin,rkAjax) {
	'use strict'
	//$("#userType_div").find("select").attr("disabled","disabled");
	//$("#isActive_div").find("select").attr("disabled","disabled");
	//动态构建表单
	rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfoKey/queryList",{},function(data){
		if(!rkUtil.rkStrUtil.isNull(data)&&data.length>0){
			for(var i=0;i<data.length;i++){
				var dataType="text";
				if(data[i].dataType=="2"){
					dataType="number";
				}else if(data[i].dataType=="3"){//选择
					var selectTag='<rk-select field="'+data[i].keyCode+'" label="'+data[i].keyName+'" ';
					if(data[i].selectDataType=="1"){//数据字典选择
						selectTag+=' dicCode="'+data[i].selectCode+'" ';
					}
					if(data[i].selectType=="2"){//1单选，2多选
						selectTag+=' multiple="multiple" ';
					}
					selectTag+=' ></rk-select>';
					$("#userInfoForm").append(selectTag);
					rkFormUtil.initForm($("#userInfoForm"));
					continue;
				}
				var attribute={label:data[i].keyName,dataType:dataType,fieldName:data[i].keyCode};
				rkTags.input.createInput($("#userInfoForm"),attribute);
				rkFormUtil.initForm($("#userInfoForm"));
			}
			loadData();
		}
	});
	//填充数据
	var loadData=function(){
		rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfo/queryUser",{},function(data){
			if(!rkUtil.rkStrUtil.isNull(data)){
				//rkFormUtil.fillFormData($("#userForm"),data);
				rkFormUtil.fillFormData($("#userInfoForm"),data);
			}
		});
	}
	//按钮
	var btns=rkTags.btn.saveBtn("userInfoBtn", false, 
	function(){//保存
		rkFormUtil.submitForm("userInfoForm", "/rk/admin/core/user/userInfo/save", function(data){
			rkAlert.succAlertMsg(data.msg);
		});
	});
	
});

