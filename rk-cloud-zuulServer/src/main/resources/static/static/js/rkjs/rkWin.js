/**
 *弹出层 
 * @author:Cavion(曹仁道)
 * caorendao187@163.com
 */
var rkWinObj;
define(['jquery','layer',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js',rkjsPath+'rkAjax.js',rkjsPath+'rkAlert.sweet.js'], function($,layer,rkUtil,rkStore,rkAjax,rkAlert) {
	'use strict'
	var win=function(url,param,title,closeFnBack){
		//设置参数
		rkStore.rkParam.setParam(param);
		var index=layer.open({
			type: 2,
			maxmin: true,//最大化和最小化按钮
			resize:false,
			anim:1,
			scrollbar: false,//不允许浏览器滚动
			maxWidth:'1200px',//对大宽度,不超过浏览器一般宽度
			offset: ['50px', '100px'],
			title:title,
			content: url,
			end:closeFnBack,//关闭回调函数
		});
		rkStore.rkWinIndex.newWin(index);//新增窗口计数器
		//closeWin();
		var iframe=$("div.layui-layer-content").find("iframe[id^=layui-layer-]");
		iframe.on("load",function(){
			var mainheight=$(this).contents().find("body").height() + 100;
			var oldHeight=0;
			var clearIntevalWin=setInterval(function(){
				var aHeight=iframe.contents().find("body").height();
				iframe.height(aHeight+30);
				if(aHeight==oldHeight){
					clearTimeout(clearIntevalWin);
				}else{
					oldHeight=aHeight;
				}
			},200);
			var winWidth=$(window).width();
			var winHeight=$(window).height();
			var minWidth=winWidth*(5/7);
			var minHeight=winHeight*(2/5);
			$(this).css("min-width",minWidth+"px").css("min-height",minHeight+"px").css("max-height","700px");
		});
	};
	var closeWin=function(isLazy){
		var index=rkStore.rkWinIndex.closeWin();
		if(!rkUtil.rkStrUtil.isTrue(isLazy)){
			layer.close(index);
		}else{//默认延迟关闭
			setTimeout(function(){layer.close(index);},500);
		}
		//关闭窗口同时清除参数缓存
		rkStore.rkParam.clearParam();
	}
	rkWinObj={
			win:win,
			closeWin:closeWin
		};
	return rkWinObj;
});
function closeWin(isLazy){
	rkWinObj.closeWin(isLazy);
}