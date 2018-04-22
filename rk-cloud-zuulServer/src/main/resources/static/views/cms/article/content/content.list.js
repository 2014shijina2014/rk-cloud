require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTab.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkTab) {
	'use strict'
	var articleContentList=rkTable.init("articleContentList", "/rk/admin/cms/article/content/queryPage",{},"checkbox","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		var tabObj={id:"menuTab_article_add",title:"新增文章",url:"/views/cms/article/content/content.detail.html",isClose:true,param:{}};
		rkTab.addTabs(tabObj);
	}, function(){
		var ids=rkTable.getChecked("articleContentList");
		if(ids.length<1||ids.length>1){
			rkAlert.errAlert("请选择一条数据");
		}
		var tabObj={id:"menuTab_article_edit",title:"编辑文章",url:"/views/cms/article/content/content.detail.html",isClose:true,param:{id:ids[0]}};
		rkTab.addTabs(tabObj);
	}, function(){
		var ids=rkTable.getChecked("articleContentList");
		if(ids.length>0){
			rkAlert.confirmDelAlert(function(){
				rkAjax.postAjaxNoErr("/rk/admin/cms/article/content/delete",{idArr:ids},function(data){
					rkAlert.succAlert(data.msg);
				});
			});
		}
	});
});

function renderVerifyStatus(data, type, row, meta){
	return rkTableObj.tableRender.renderDic(data,row,meta,"TYSHJGM");
}