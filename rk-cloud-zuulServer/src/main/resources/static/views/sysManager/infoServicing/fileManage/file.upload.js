require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTree.js',
         rkjsPath+'rkEditor.js',rkjsPath+'rkFileUpload.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkAjax,rkTree,rkEditor,rkFileUpload) {
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
	//初始化图片上传组件
	rkFileUpload.initFileInput("commonFileUpload","/rk/admin/core/sys/file/CoreSysFile/uploadFile",moduleCode,opareType,function(fileId){
		var fileUrl="/rk/admin/core/sys/file/CoreSysFile/showImage/"+fileId;
		rkStore.rkChooseBcakParam.setParam({fileId:"fileId",fileUrl:fileUrl});
	});	
});

