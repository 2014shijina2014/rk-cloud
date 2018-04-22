require(['perfectScrollbar','switchery',rkjsPath+'main.js',rkjsPath+'index.js',rkjsPath+'rkTab.js',rkjsPath+'rkAjax.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkUtil.js',
         'css!/static/css/styles.css','css!/static/css/plugins.css','css!/static/css/themes/theme-2.css'], 
function(perfect,switchery,main,index,rkTab,rkAjax,rkAlert,rkUtil) {
	'use strict'
	main.init();
	index.init();
	$("header.navbar").css("position","absolute");
	
	//加载模块
	var loadModule=function(){
		rkAjax.postAjax("/userservice/rk/web/core/domain/module/queryList",null,true,
				function(data){
					for(var i=0;i<data.length;i++){
						var tag="<li><a href='javascript:void(0)' url='"+data[i].indexUrl+"' class='menu-toggler' id='module_"+data[i].code+"'>"+data[i].name+"</a></li>";
						$("#moduleMenu").append(tag);
						//绑定模块切换事件
						var urlA=$("#module_"+data[i].code);
						urlA.on("click", function(e) {
							//切换的时候先清空原来的
							$("#indexTabHead").html("");
							$("#indexTabContent").html("");
							//rkTab.closeTab("moduleIndex");
							var target=e.currentTarget;
							if(!rkUtil.rkStrUtil.isNull($(target).attr("url"))){
								var tabObj={id:"moduleIndex",title:"首页",url:$(target).attr("url"),isClose:false};
							}else{//如果没有指定链接
								var tabObj={id:"moduleIndex",title:"首页",content:"这是模块首页，请设置内容地址",isClose:false};
							}
							rkTab.addTabs(tabObj);
							var moduleCode=$(target).attr("id").split("_")[1];
							loadMenu(moduleCode);
						});
						//第一次执行第一个模块
						if(i==0){
							if(!rkUtil.rkStrUtil.isNull(urlA.attr("url"))){
								var tabObj={id:"moduleIndex",title:"首页",url:urlA.attr("url"),isClose:false};
							}else{//如果没有指定链接
								var tabObj={id:"moduleIndex",title:"首页",content:"这是模块首页，请设置内容地址",isClose:false};
							}
							rkTab.addTabs(tabObj);
							loadMenu(data[i].code);
						}
					}
				},function(err){
				rkAlert.errAlertMsg(err);
			});
	};
	
	var loadMenu=function(moduleCode){
		//清空原来的菜单
		$("#adminMenu").html("");
		//加载菜单
		if(rkUtil.rkStrUtil.isNull(moduleCode)){
			moduleCode="";
		}
		rkAjax.postAjax("/userservice/rk/admin/core/domain/adminMenu/queryMenu",{moduleCode:moduleCode},true,
			function(data){
				for(var i=0;i<data.length;i++){
					if(data[i].fatherCode=="0A"){
						continue;
					}
					var tag="";
					if(i==0){
						tag+="<li class='open' id='menu_"+data[i].code+"'>";
					}else{
						tag+="<li id='menu_"+data[i].code+"'>";
					}
					//区分是否有子节点的菜单
					if(!rkUtil.rkStrUtil.isNull(data[i].hUrl)){
						tag+="<a href='#' url='"+data[i].hUrl+"'>";
					}else{
						tag+="<a href='javascript:void(0)' url=''>";
					}
					if(data[i].fatherCode.split("A").length==3){
						tag+="<div class='item-content'>";
						if(!rkUtil.rkStrUtil.isNull(data[i].iconClass)){
							tag+="<div class='item-media'><i class='"+data[i].iconClass+"'></i></div>";
						}else{
							tag+="<div class='item-media'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>";
						}
						tag+="<div class='item-inner'><span class='title'>"+data[i].name+"</span></div>";
						tag+="</div></a></li>";
						$("#adminMenu").append(tag);
					}else{//如果不是第一级
						tag+="<span class='title'>"+data[i].name+"</span>";
						tag+="</a></li>";
						
						var fatherTag=$("#menu_"+data[i].fatherCode);
						var childTag=fatherTag.find(".sub-menu");//看看是否有子节点，
						if(!rkUtil.rkStrUtil.isNull(childTag)){//已经存在直接追加
							childTag.append(tag);
						}else{
							var childTagEle="<ul class='sub-menu'>"+tag+"</ul>";
							fatherTag.append(childTagEle);
							//同时给父节点的菜单添加一个展开和收缩的标志(在首次出现子节点的时候添加)
							fatherTag.find(".item-inner").append("<i class='icon-arrow'></i>");
						}
					}
					//绑定点击事件
					$("#menu_"+data[i].code).on("click", function(e) {
						var target=e.currentTarget;
						//先移除前后已经选中的项
						$(target).nextAll("li").removeClass("active").removeClass("open");
						$(target).prevAll("li").removeClass("active").removeClass("open");
						//添加激活样式
						//$(target).addClass("active").addClass("open");
						//$(target).find("ul.sub-menu").css("display","block");
					});
					var urlA=$("#menu_"+data[i].code).find("a");
					urlA.on("click", function(e) {
						e.preventDefault();
						var target=e.currentTarget;
						var parentTarget=$(target).parent();
						if(!rkUtil.rkStrUtil.isNull($(target).attr("url"))){
							var menuCode=parentTarget.attr("id").split("_")[1];
							var menuName=$(target).find("span.title").html();
							var tabObj={id:"menuTab_"+menuCode,title:menuName,url:$(target).attr("url"),isClose:true};
							rkTab.addTabs(tabObj);
						}
					});
				}
			},
			function(err){
				rkAlert.errAlertMsg(err);
		});
	};
	
	
	loadModule();
});