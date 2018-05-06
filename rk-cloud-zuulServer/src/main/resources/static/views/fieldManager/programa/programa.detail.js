require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkWin.js'], 
		function($,bt,rkUtil,rkStore,rkTags,rkFormUtil,rkAlert,rkWin) {
	'use strict'
	rkFormUtil.initForm($("#programaForm"),function(){
		changeModule();
	});
	var changeModule=function(){
		$("#module").on("change",function(){
			var module=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(module)){
				var submitUrl="";
				if(module=="cmsArticle"){
					submitUrl="/rk/admin/cms/article/programa/save";
				}else if(module=="cmsMall"){
					submitUrl="/rk/admin/cms/mall/programa/save";
				}else{
					return;
				}
				var btns=rkTags.btn.saveCloseBtn("programaBtn", false, 
						function(){
							//保存
							rkFormUtil.submitForm("programaForm", submitUrl, function(data){
								rkAlert.succAlertMsg(data.msg);
								//关闭窗口
								parent.closeWin(true);
							});
						}, 
						function(){//关闭
							parent.closeWin(false);
						});
			}
		});
	}
});

