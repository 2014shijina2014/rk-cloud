/**
 * @author:Cavion(曹仁道)
 * caorendao187@163.com
 */
define(['jquery','bInputFileCh',rkjsPath+'rkUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], 
		function($,bInputFile,rkUtil,rkAlert,rkAjax) {
	'use strict'
	var initFileInput=function(EleId, uploadUrl,moduleCode,opareType,fnBack){
		 var control = $('#' + EleId); 
		    control.fileinput({
		        language: 'zh', //设置语言
		        uploadUrl: uploadUrl, //上传的地址
		        uploadAsync: true,
		        uploadExtraData:{moduleCode:moduleCode,opareType:opareType},
		        allowedFileTypes:['image', 'html', 'text', 'video', 'audio', 'flash', 'object'],
		        allowedFileExtensions : ['jpg', 'png','gif','bmp'],//接收的文件后缀
		        showUpload: true, //是否显示上传按钮
		        showCaption: false,//是否显示标题
		        browseClass: "btn btn-primary", //按钮样式             
		        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
		    });
		    control.on('fileuploaded', function(event, data, previewId, index) {
		        var form = data.form;
		        var files = data.files;
		        var extra = data.extra;
		        var response = data.response;
		        var reader = data.reader;
		        if(response.statusCode!=rkAjax.ajaxStatusCode.status200){
		        	rkAlert.errAlert(response.msg);
		        }else if(fnBack){
		        	fnBack(response.msg);
		        }
		    });
	}
	
	return {
		initFileInput:initFileInput
	}
})