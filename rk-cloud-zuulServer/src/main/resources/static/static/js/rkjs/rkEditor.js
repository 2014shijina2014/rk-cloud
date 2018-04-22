/**
 * @author:Cavion(曹仁道)
 * caorendao187@163.com
 */
define(['jquery','ueditorCh','ZeroClipboard',rkjsPath+'rkUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkStore.js'], 
		function($,uEditor,ZeroClipboard,rkUtil,rkAlert,rkAjax,rkStore) {
	'use strict'
	window.ZeroClipboard=ZeroClipboard;
	var initEditor=function(eleId){
		var ue=UE.getEditor(eleId,{
			initialFrameHeight:300,
			scaleEnabled:true,
			minFrameWidth:800,
			minFrameHeight:320
		});
		return ue;
	};
	var initMiniEditor=function(eleId){
		var ue=UE.getEditor(eleId,{
			initialFrameHeight:150,
			scaleEnabled:true,
			minFrameWidth:300,
			minFrameHeight:100,
			toolbars:[['redo','bold','horizontal','link','unlink','simpleupload','emotion','forecolor','insertorderedlist']]
		});
		return ue;
	};
	var getContent=function(ue){
		return ue.getContent();
	}
	return {
		initEditor:initEditor,
		initMiniEditor:initMiniEditor,
		getContent:getContent
	};
});