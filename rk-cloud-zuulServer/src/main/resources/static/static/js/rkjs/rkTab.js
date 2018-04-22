/**
 * 带有可关闭的tab选项卡
 * @author Cavion(曹仁道)
 */
define(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js'], function($,bootstrap,rkUtil,rkStore) {
	'use strict'
	var addTabs = function (obj) {
		var id=obj.id;//读取的id，这是唯一的
		var tabId = "tab_" + id; //tab项id
		var tabContentId="content_"+id;//内容id
		var title=obj.title;
		var isClose=obj.isClose;
		var content=obj.content;
		var url=obj.url;
		var param=obj.param;
		if(!rkUtil.rkStrUtil.isNull(param)){//设置缓存参数
			rkStore.rkTabParam.setParam(param);
		}
		if(rkUtil.rkStrUtil.isNull(isClose)||isClose=="0"){
			isClose=false;
		}else if(isClose=="true"||isClose=="1"){
			isClose=true;
		}
		var $navTab=$("#indexTabHead");
		var $tabContent=$("#indexTabContent");
		if(rkUtil.rkStrUtil.isNull($navTab)||rkUtil.rkStrUtil.isNull($tabContent)){
			$navTab=$('#indexTabHead', window.parent.document);
			$tabContent=$('#indexTabContent', window.parent.document);
		}
		$navTab.find(".active").removeClass("active");
		$tabContent.find(".active").removeClass("active");
		if (rkUtil.rkStrUtil.isNull($navTab.find("#" + tabId))) {//如果TAB不存在，创建一个新的TAB
			//创建新TAB的title
			var tabTitle = '<li role="presentation" id="' + tabId + '"><a href="#' + tabContentId + '" aria-controls="' + tabId + '" role="tab" data-toggle="tab">' + title;
			//是否允许关闭
			if (isClose) {//占据一定空格使用关闭图标
				tabTitle += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="ti-close" tabclose="' + id + '" style="position:absolute;top:0px;right:2px;cursor: pointer;"></i>';
			}
			tabTitle += '</a></li>';
			//是否指定TAB内容
			var tabContent='<div role="tabpanel" class="tab-pane fade" id="' + tabContentId + '">';
			if (!rkUtil.rkStrUtil.isNull(content)) {
				tabContent += content + '</div>';
			} else { //没有内容，使用IFRAME打开链接
				//读取tab中iframe高度，目标页面的body 设置bgColor="transparent"
				var $body=$("body");
				if(rkUtil.rkStrUtil.isNull($body)){
					$body=$('body', window.parent.document);
				}
				var mainHeight = $body.height();
				tabContent += '<iframe src="' + url + '" width="100%" id="tab_iframe_'+tabContentId+'" style="min-height:'+mainHeight+'px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes">';
				tabContent+='</iframe></div>';
			}
			//加入TABS
			$navTab.append(tabTitle);
			$tabContent.append(tabContent);
			//重算iframe高度
			calcIframeHeight($tabContent.find("#tab_iframe_" + tabContentId));
		}
		//激活TAB
		$navTab.find("#" + tabId).addClass('active');
		$tabContent.find("#" + tabContentId).addClass(" in active");
		
		//最后添加关闭监听事件
		$navTab.on("click", "[tabclose]", function(e) {
			id = $(this).attr("tabclose");
			closeTab(id);
		});
	};
	var calcIframeHeight=function(iframe){
		iframe.on("load",function(){
			var mainheight=iframe.contents().find("body").height() + 300;
			var oldHeight=mainheight||300;
			var clearIntevalWin=setInterval(function(){
				var aHeight=iframe.contents().find("body").height();
				var newHeight=(aHeight+260)>oldHeight?aHeight:oldHeight;
				//iframe.css("height",newHeight+"px")
				iframe.height(newHeight);
				if(newHeight<=oldHeight){//不清除，实时检测
					clearTimeout(clearIntevalWin);
				}else{
					oldHeight=newHeight;
				}
			},200);
			/*var winWidth=$(window).width();
			var winHeight=$(window).height();
			var minWidth=winWidth*(5/7);
			var minHeight=winHeight*(2/5);
			$(this).css("min-width",minWidth+"px").css("min-height",minHeight+"px");*/
		});
	}
	var closeTab = function(id) {
		var $navTab=$("#indexTabHead");
		var $tabContent=$("#indexTabContent");
		if(rkUtil.rkStrUtil.isNull($navTab)||rkUtil.rkStrUtil.isNull($tabContent)){
			$navTab=$('#indexTabHead', window.parent.document);
			$tabContent=$('#indexTabContent', window.parent.document);
		}
		//如果关闭的是当前激活的TAB，激活他的前一个TAB
		if ($navTab.find("li.active").attr('id') == "tab_"+id) {
			$navTab.find("#tab_" + id).prev().addClass('active');
			$tabContent.find("#content_" + id).prev().addClass('active');
		}
		//关闭TAB
		$navTab.find("#tab_" + id).remove();
		$tabContent.find("#content_" + id).remove();
	};
	return {
		addTabs:addTabs,
		closeTab:closeTab
	}
});