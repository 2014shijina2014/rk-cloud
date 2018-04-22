require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkWin.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTree.js',
         rkjsPath+'rkEditor.js'], 
		function($,bt,rkUtil,rkWin,rkStore,rkTags,rkFormUtil,rkAlert,rkAjax,rkTree,rkEditor,rkFileUpload) {
	'use strict'
	var goodsContentId;
	var goodsId;
	rkFormUtil.initForm($("#goodsForm"));
	rkFormUtil.initForm($("#goodsInfoForm"));
	var winParam=rkStore.rkTabParam.getParam();
	//初始化编辑器
	var rke=rkEditor.initEditor("content");
	
	$("#uploadThumbBtn").on("click",function(){
	rkWin.win("/views/sysManager/infoServicing/fileManage/chooseBack/file.list.html",{moduleCode:"cmsMallGoods",opareType:""},"选择文件",
		function(){
			var imgs=rkStore.rkChooseBcakParam.getParam();
			$("#goodsForm").find("#thumbnail").val(imgs[0]);
			$("#thumbnailImg").attr("src",imgs[0]);
		});
	});
	var programaCode="";
	var rkTreeObj=rkTree.initTree("goodsProgramaTree","/rk/admin/cms/mall/programa/queryTree",null,"radio",null,function(event, treeId, treeNode){
		programaCode=treeNode.code;
		$("#goodsForm").find("#programaCode").val(programaCode);
		showGoodsProperty(programaCode);
		
	});
	var btnObjs=[];
	btnObjs.push(rkTags.btn.btnDefault("goodsBtn", false, "保存", "glyphicon glyphicon-save", "btn-primary", function(){
		saveGoodsContent("0");
	}));
	btnObjs.push(rkTags.btn.btnDefault("goodsBtn", false, "保存并上架", "glyphicon glyphicon-yes", "btn-success", function(){
		saveGoodsContent("1");
	}));
	btnObjs.push(rkTags.btn.btnDefault("goodsBtn", false, "下架", "glyphicon glyphicon-off", "btn-default", function(){
		saveGoodsContent("0");
	}));
	var saveGoodsContent=function(isPutaway){
		if(isPutaway=="0"){//保存--等待上架
			$("#goodsInfoForm").find("#status").val("1");
		}else if(isPutaway=="1"){//保存并上架--已上架
			$("#goodsInfoForm").find("#status").val("2");
		}else{
			rkAlert.errAlertMsg("错误请求");
			return;
		}
		var $goodsForm=$("#goodsForm");
		//$goodsForm.find("#content").val(rkEditor.getContent(rke));
		if(rkUtil.rkStrUtil.isNull($goodsForm.find("#programaCode").val())){
			rkAlert.errAlertMsg("请选择商品分类");
			return;
		}
		rkFormUtil.submitForm("goodsForm","/rk/admin/cms/mall/content/save",function(data){
			goodsContentId=data.msg;
			$("#goodsInfoForm").find("#pubContentId").val(goodsContentId);
			saveGoodsInfo();
		});
	};
	var saveGoodsInfo=function(){
		var $goodsInfoForm=$("#goodsInfoForm");
		rkFormUtil.submitForm("goodsInfoForm","/rk/admin/cms/mall/goods/save",function(data){
			goodsId=data.msg;
			$("#goodsOtherProForm").find("#goodsId").val(goodsId);
			rkAlert.succAlertMsg("保存成功");
			saveGoodOther();
		});
	};
	var saveGoodOther=function(){
		var $goodsOtherForm=$("#goodsOtherProForm");
		rkFormUtil.submitForm("goodsOtherProForm","/rk/admin/cms/mall/ppValue/batchSave",function(data){
			rkAlert.succAlertMsg("保存成功");
		});
	};
	//附加商品属性的动态构建
	var showGoodsProperty=function(programaCode){
		$("#goodsOtherProForm").html("<input type='hidden' name='goodsId' id='goodsId'/>");
		rkAjax.postAjaxNoErr("/rk/admin/cms/mall/progProp/queryList",{programaCode:programaCode},function(data){
			if(!rkUtil.rkStrUtil.isNull(data)&&data.length>0){
				for(var i=0;i<data.length;i++){
					var dataType="text";
					var attribute={label:data[i].propertyName,dataType:dataType,fieldName:data[i].propertyCode,labelWidth:"100px",width:"240px"};
					rkTags.input.createInput($("#goodsOtherProForm"),attribute);
					rkFormUtil.initForm($("#goodsOtherProForm"));
				}
				loadData();
			}
		});
	}
	//填充数据
	var loadData=function(){
		var winParam=rkStore.rkParam.getParam();
		if(!rkUtil.rkStrUtil.isNull(goodsId)){
			rkAjax.postAjax("/rk/admin/cms/mall/ppValue/queryList",{goodsId:goodsId},
			function(data){
				if(!rkUtil.rkStrUtil.isNull(data)){
					rkFormUtil.fillFormData($("#goodsOtherProForm"),data);
				}
			},
			function(data){
				return;
			});
		}
	}
	
	if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.id)){
		goodsId=winParam.id;
		$("#goodsInfoForm").find("#id").val(goodsId);
		var loadGoodsUrl="/rk/admin/cms/mall/goods/query"
		rkAjax.postAjaxNoErr(loadGoodsUrl,{id:goodsId},function(data){
			goodsContentId=data.pubContentId;
			showGoodsProperty(data.programaCode);
			rkFormUtil.fillFormData($("#goodsInfoForm"),data);
			loadContentData(goodsContentId);
		});
		
	}
	//加载商品内容
	var loadContentData=function(contentId){
		var loadGoodsContentUrl="/rk/admin/cms/mall/content/query";
		rkAjax.postAjaxNoErr(loadGoodsContentUrl,{id:contentId},function(data){
			showGoodsProperty(data.programaCode);
			rkFormUtil.fillFormData($("#goodsForm"),data);
			if(!rkUtil.rkStrUtil.isNull(data.thumbnail)){
				$("#thumbnailImg").attr("src",data.thumbnail);
			}
		});
	}
});

