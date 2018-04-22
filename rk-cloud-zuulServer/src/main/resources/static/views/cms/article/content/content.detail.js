require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkWin.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTree.js',
         rkjsPath+'rkEditor.js'], 
		function($,bt,rkUtil,rkWin,rkStore,rkTags,rkFormUtil,rkAlert,rkAjax,rkTree,rkEditor,rkFileUpload) {
	'use strict'
	var articleId;
	rkFormUtil.initForm($("#articleForm"));
	rkFormUtil.initForm($("#seoInfoForm"));
	$("#select_type_div").find("#type").on("change",function(){
		var type=$(this).val();
		if(type=="4"){
			$("#linkUrl_div").show("slow");
		}else{
			$("#linkUrl_div").hide("slow");
		}
	});
	$("#searchBtn").on("click",function(){
		rkWin.win("/views/sysManager/infoServicing/fileManage/chooseBack/file.list.html",{moduleCode:"ariticle",opareType:"",chooseType:"radio"},"选择文件",
			function(){
				var imgs=rkStore.rkChooseBcakParam.getParam();
				$("#thumbnail").val(imgs[0]);
				$("#thumbnailImg").attr("src",imgs[0]);
			}); 
	});
	//初始化图片上传组件
	/*rkFileUpload.initFileInput("thumbnail","/rk/admin/core/sys/file/CoreSysFile/uploadFile","articleCms","articleThumbnail",function(fileId){
		$("#thumbnail_path").val("/rk/admin/core/sys/file/CoreSysFile/showImage/"+fileId);
	});*/
	var programaCode="";
	var rkTreeObj=rkTree.initTree("programaTree","/rk/admin/cms/article/programa/queryTree",null,"checkbox",null,function(event, treeId, treeNode){
		programaCode=treeNode.code;
	});
	//初始化编辑器
	var rke=rkEditor.initEditor("articleContent");
	var btnObjs=[];
	btnObjs.push(rkTags.btn.btnDefault("articleBtn", false, "保存到草稿", "glyphicon glyphicon-save", "btn-primary", function(){
		saveArticle("1");
	}));
	btnObjs.push(rkTags.btn.btnDefault("articleBtn", false, "保存并发布", "glyphicon glyphicon-yes", "btn-success", function(){
		saveArticle("2");
	}));
	btnObjs.push(rkTags.btn.btnDefault("articleBtn", false, "移到垃圾箱", "glyphicon glyphicon-off", "btn-default", function(){
		saveArticle("3");
	}));
	var saveArticle=function(type){
		//保存
		var articleData={};
		//设置栏目
		var nodes=rkTree.getCheckNodes("programaTree");
		for(var i=0;i<nodes.length;i++){
			if(!rkUtil.rkStrUtil.isNull(programaCode)){
				programaCode=nodes[i].code;
			}else{
				programaCode+=","+nodes[i].code;
			}
		}
		articleData.programaCode=programaCode;
		//内容部分
		var $articleForm=$("#articleForm");
		articleData.title=$articleForm.find("#title").val();
		articleData.summary=$articleForm.find("#summary").val();
		articleData.type=$articleForm.find("#type").val();
		articleData.linkUrl=$articleForm.find("#linkUrl").val();
		articleData.content=rkEditor.getContent(rke);
		//seo信息
		var $seoInfoForm=$("#seoInfoForm");
		articleData.metaKeywords=$seoInfoForm.find("#metaKeywords");
		articleData.metaDescription=$seoInfoForm.find("#metaDescription");
		//缩略图
		articleData.thumbnail=$("#thumbnail_path").val();
		//保存
		if(type=="1"){//保存到草稿--审核状态，未提交
			articleData.verifyStatus="1"
		}else if(type=="2"){//保存并发布--审核状态为审核通过
			articleData.verifyStatus="3"
		}else if(type=="3"){//移到垃圾箱--是否启用为否,审核状态为审核通过
			articleData.verifyStatus="3";
			articleData.enabled="1";
		}else{
			rkAlert.errAlertMsg("错误请求");
		}
		rkAjax.postAjaxNoErr("/rk/admin/cms/article/content/save",articleData,function(data){
			articleId=data.msg;
			rkAlert.succAlertMsg("保存成功");
		});
	};
	
	
});

