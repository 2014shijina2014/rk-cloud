require(['jquery',rkjsPath+'rkTable.js',rkjsPath+'rkTags.js',rkjsPath+'rkWin.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTab.js'], 
		function($,rkTable,rkTags,rkWin,rkFormUtil,rkAlert,rkAjax,rkTab) {
	'use strict'
	var goodsList=rkTable.init("goodsList", "/rk/admin/cms/mall/goods//queryPage",{},"radio","searchBtn");
	var btns=rkTags.btn.addEditDelBtn("tableDiv", true, function(){
		var tabObj={id:"menuTab_goods_add",title:"新增商品",url:"/views/cms/mall/goods/goods.detail.html",isClose:true,param:{}};
		rkTab.addTabs(tabObj);
	}, function(){
		var ids=rkTable.getChecked("goodsList");
		if(ids.length<1||ids.length>1){
			rkAlert.errAlert("请选择一条数据");
		}
		var tabObj={id:"menuTab_goods_edit",title:"编辑商品",url:"/views/cms/mall/goods/goods.detail.html",isClose:true,param:{id:ids[0]}};
		rkTab.addTabs(tabObj);
	}, function(){
		var ids=rkTable.getChecked("goodsList");
		if(ids.length>0){
			rkAlert.confirmDelAlert(function(){
				rkAjax.postAjaxNoErr("/rk/admin/cms/mall/goods/delete",{idArr:ids},function(data){
					rkAlert.succAlert(data.msg);
				});
			});
		}
	});
});
function renderVerifyStatus(data, type, row, meta){
	rkTableObj.tableRender.renderDic(data,row,meta,"TYSHJGM");
}
function renderStatus(data, type, row, meta){
	rkTableObj.tableRender.renderDic(data,row,meta,"SPZTM");
}
function renderTradeType(data, type, row, meta){
	rkTableObj.tableRender.renderDic(data,row,meta,"SPJYLXM");
}
function renderPayType(data, type, row, meta){
	rkTableObj.tableRender.renderDic(data,row,meta,"ZFFSM");
}
function renderProgramaCode(data, type, row, meta){
	var url="/rk/admin/cms/mall/programa/queryProgByCode";
	rkTableObj.tableRender.renderByUrl(row,meta,url,{code:data});
}
