/**
 * 表单工具
 */
define(['jquery','jqueryValid','jqueryValidCh','bSelectCh',rkjsPath+'rkAjax.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js'], 
		function($,valid,validCh,bSelect,rkAjax,rkAlert,rkUtil,rkStore,rkTags) {
	"use strict";
	var validForm = function() {
		$.validator.setDefaults({
			debug: true,//debug模式开关
			errorElement : "span", //显示错误消息
			errorClass : 'help-block',
			errorPlacement : function(error, element) {//为每一个input类型或select类型表单设置提示消息
				if (element.attr("type") == "radio" || element.attr("type") == "checkbox") {//选择标签最后插入错误信息
					error.insertAfter($(element).closest('.form-group').children('div').children().last());
				} else if (element.attr("name") == "card_expiry_mm" || element.attr("name") == "card_expiry_yyyy") {
					error.appendTo($(element).closest('.form-group').children('div'));
				} else {
					error.insertAfter(element);
				}
			},
			ignore : ':hidden',
			success : function(label, element) {
				label.addClass('help-block valid');
				//成功的话移除错误提示
				$(element).closest('.form-group').removeClass('has-error');
			},
			highlight : function(element) {
				$(element).closest('.help-block').removeClass('valid');
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {// revert the change done by hightlight
				$(element).closest('.form-group').removeClass('has-error');
				// set error class to the control group
			}
		});
	};
	var submitForm=function(eleId,url,fnBack){
		validForm();
		var form = $('#'+eleId);
		var errorHandler = $('.errorHandler', form);
		var flag=form.valid();
		if(flag){//验证通过，提交表单
			var formData=formToJson(form);
			for (var key in formData){
		        var dataType=rkUtil.getDataType(formData[key]);
		        if(dataType=="Array"){
		        	var formArrData=formData[key];
		        	var arrData="";
		        	for(var i=0;i<formArrData.length;i++){
		        		if(rkUtil.rkStrUtil.isNull(arrData)){
		        			arrData+=formArrData[i];
		        		}else{
		        			arrData+=rkUtil.rkConst.selectSplitStr+formArrData[i];
		        		}
		        	}
		        	formData[key]=arrData;
		        }
		    }
			rkAjax.postAjax(url,formData,false,fnBack,function(erro){
				rkAlert.alert(erro);
			});
		}else{
			rkAlert.errAlertMsg("表单验证不通过");
			//fnBack(false);
		}
	};
	var formToJson = function($form) {
		var o = {};
		var a = $form.serializeArray();
		$.each(a, function(index) {
			if (o[a[index].name]) {
				if (!o[a[index].name].push) {
					o[a[index].name] = [ o[a[index].name] ];
				}
				o[a[index].name].push(a[index].value || '');
			} else {
				o[a[index].name] = a[index].value || '';
			}
		});
		return o;
	};
	/**
	 * 填充表单数据
	 */
	var fillFormData=function($form,data){
		$.each(data, function(key) {
			var dataContent=data[key];
			if(!rkUtil.rkStrUtil.isNull(dataContent)&&rkUtil.getDataType(dataContent)=="String"&&dataContent.indexOf(rkUtil.rkConst.selectSplitStr)>0){
				dataContent=dataContent.split(rkUtil.rkConst.selectSplitStr);
			}
			$form.find("#"+key).val(dataContent);
		});
		$form.find('.selectpicker').selectpicker('render');
	};
	var fillChoseBackData=function($form,url,param,field,key){
		rkAjax.postAjaxNoErr(url,param,function(data){
			$form.find("#"+field+"_show").val(data[key]);
		});
	};
	/**
	 * 清空表单数据
	 */
	var clearFormData=function($form){
		var tag = $form.serializeArray();
		$.each(tag, function(index) {
			var tagName=tag[index].name;
			$("input[name='"+tagName+"']").val("");
			$("select[name='"+tagName+"']").val("");
			$("textarea[name='"+tagName+"']").val("");
		});
		$("input[id$='_show']").val("");
		$form.find('.selectpicker').selectpicker('render');
	};
	/**
	 * 初始化表单样式
	 */
	var initFormCss=function($form){
		//$form.find("label.control-label").css("width", "150px");
		$form.find("label.control-label").each(function(){
			var $thisStyle=$(this).attr("style");
			if((!rkUtil.rkStrUtil.isNull($thisStyle)&&$thisStyle.indexOf("width:")!=-1)){
				//$(this).css("width", "150px");
			}else{
				$(this).css("width", "150px");
			}
		});
		$form.find(".form-control").each(function(){
			var $thisStyle=$(this).attr("style");
			var $thisCols=$(this).attr("cols");
			if((!rkUtil.rkStrUtil.isNull($thisStyle)&&$thisStyle.indexOf("width:")!=-1)||!rkUtil.rkStrUtil.isNull($thisCols)){
				if($(this).closest(".input-group").length>0){
					$(this).closest(".input-group").css("margin-right", "0px");
				}else{
					$(this).css("margin-right", "20px");
				}
			}else{
				if($(this).closest(".input-group").length>0){
					$(this).closest(".input-group").css("width", "330px").css("margin-right", "0px");
				}else{
					$(this).css("width", "300px").css("margin-right", "20px");
				}
			}
		});
		var $bselect=$form.find(".bootstrap-select").each(function(){
			var width="300px";
			var style=$(this).find("select.selectpicker").attr("style");
			if(!rkUtil.rkStrUtil.isNull(style)){
				width=style.substring(style.indexOf("width:")+6,style.indexOf("px"))+"px";
			}
			$(this).css("width", width).css("margin-right", "20px");
		});
		$form.find(".form-group").css("margin-top", "10px").css("margin-left", "10px");
	};
	var initForm=function($form,fnBack){
		$form.find('select.selectpicker').selectpicker({});
		//读取参数
		var winParam=rkStore.rkParam.getParam();
		if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.method)&&winParam.method=="edit"&&!rkUtil.rkStrUtil.isNull(winParam.id)&&!rkUtil.rkStrUtil.isNull(winParam.ajaxUrl)){
			var url=winParam.ajaxUrl;
			rkAjax.postAjaxNoErr(url,{id:winParam.id},function(data){
				fillFormData($form,data);
			});
		}
		if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.fatherCode)){
			$form.find("#fatherCode").val(winParam.fatherCode);
		}
		//扫描构建rk-input标签
		rkTags.input.rkInput($form);
		//扫描构建rk-textarea标签
		rkTags.textarea.rkTextarea($form);
		initFormCss($form);
		//扫描构建rk-select标签
		rkTags.select.rkSelect($form,function(){
			setTimeout(function(){
				$form.find('select.selectpicker').selectpicker({});
				initFormCss($form);
				if(fnBack){
					fnBack();
				}
			},200);
		});
	}
	return {
		//validForm:validForm,//验证器不暴露
		submitForm:submitForm,
		formToJson:formToJson,
		initFormCss:initFormCss,
		fillFormData:fillFormData,
		initForm:initForm,
		fillChoseBackData:fillChoseBackData,
		clearFormData:clearFormData
	}
})