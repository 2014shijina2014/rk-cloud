require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',
         rkjsPath+'rkTab.js','masonry'], 
		
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkTab,Masonry) {
	'use strict'
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
						tag+="<a href='' class='thumbnail grid-item-content'> ";
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
				}
		});
	}
	loadFileList();
	$("#uploadFile").on("click",function(){
		rkWin.win("/views/sysManager/infoServicing/fileManage/file.upload.html",{moduleCode:"",opareType:""},"上传文件",
		function(){
			loadFileList();
		});
	});
});
