require(['jquery',rkjsPath+'rkStore.js',rkjsPath+'rkUtil.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',
         rkjsPath+'rkTab.js','masonry'], 
		
		function($,rkStore,rkUtil,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkTab,Masonry) {
	'use strict'
	
	var winParam=rkStore.rkParam.getParam();
	var moduleCode="commonFileUpload";
	if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.moduleCode)){
		moduleCode=winParam.moduleCode;
	}
	var opareType="defaultOpare";
	if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.opareType)){
		opareType=winParam.opareType;
	}
	
	var chooseType="radio";//默认只能选择一个
	if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.chooseType)){
		chooseType=winParam.chooseType;
	}
	
	rkFormUtil.initForm($("#searchForm"));
	var loadFileList=function(){
		//执行之前先清除
		//$("#imageListDiv").find(".row").html("");
		rkTags.rkPage.createPage({eleId:"imagePagination",listEleId:"imageListDiv",pageMethod:"scoreLoad",pageSize:30,
				ajax:{url:"/rk/admin/core/sys/file/CoreSysFile/queryPage",param:{simpleType:""}}},
			function(list){
				if(list.length>0){
					for(var i=0;i<list.length;i++){
						//var tag="<div class='col-xs-6 col-sm-4 col-md-3 col-lg-2 grid-sizer'></div>";
						var tag="<div class='col-xs-6 col-sm-4 col-md-3 col-lg-2 grid-item'>";
						tag+="<a style='cursor: pointer;' class='thumbnail grid-item-content'> ";
						tag+="<img src='"+list[i].urlPath+"' alt='"+list[i].sourceName+"'>";
						tag+="</a>";
						tag+="</div>";
						$("#imageListDiv").append(tag);
						//msnry.appended(tag);
					}
					$('#imageListDiv').imagesLoaded( function() {
						var msnry =new Masonry("#imageListDiv",{itemSelector: '.grid-item'});
					});
					//msnry.layout();
					$("#imageListDiv").find(".thumbnail").each(function(){
						var $this=$(this);
						$this.on("click",function(){
							if(chooseType=="radio"){
								$("#imageListDiv").find(".thumbnail").each(function(){
									if($(this).hasClass("imgCheck")){
										$(this).removeClass("imgCheck");
									}
								});
							}
							if($(this).hasClass("imgCheck")){
								$(this).removeClass("imgCheck");
							}else{
								$(this).addClass("imgCheck");
							}
						});
					});
				}
		});
	}
	loadFileList();
	$("#uploadFile").on("click",function(){
		rkWin.win("/views/sysManager/infoServicing/fileManage/file.upload.html",{moduleCode:moduleCode,opareType:opareType},"上传文件",
		function(){
			loadFileList();
		});
	});
	var btns=rkTags.btn.yesCloseBtn("containerFluid", false, function(){
		var imgs=[]
		$("a.imgCheck").find("img").each(function(){
			imgs.push($(this).attr("src"));
		});
		if(!rkUtil.rkStrUtil.isNull(imgs)){
			rkStore.rkChooseBcakParam.setParam(imgs);
			parent.closeWin(true);
		}
	}, function(){
		//关闭窗口
		parent.closeWin(false);
	});
});
