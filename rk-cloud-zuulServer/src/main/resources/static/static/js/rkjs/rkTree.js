/**
 * @author:Cavion(曹仁道)
 * caorendao187@163.com
 */
define(['jquery','ztree',rkjsPath+'rkUtil.js',rkjsPath+'rkStore.js'], function($,ztree,rkUtil,rkStore) {
	'use strict'
	var tree = {
			treeId: '',
			rMenuId:'',
			rMenuLiIds:[],
	        pNode: '',
	        genRMenuTag:function(menuObjs){//生成右键菜单
	        	if(rkUtil.rkStrUtil.isNull(tree.rMenuId)){
	        		tree.rMenuId="rMenu_"+tree.treeId;
	        	}
	        	var rMenuDom=$("body").find("#"+tree.rMenuId);
	        	if(!rkUtil.rkStrUtil.isNull(rMenuDom)){
	        		return;
	        	}
	        	var tag="<div id='"+tree.rMenuId+"' class='list-group col-md-2'>";
	        	for(var i=0;i<menuObjs.length;i++){
	        		var rMenuLiId="#"+tree.rMenuId+"_"+menuObjs[i].id;
            		tree.rMenuLiIds.push(rMenuLiId);
            		var className="";
            		if(menuObjs[i].icon=="add"){
            			className="glyphicon glyphicon-plus-sign";
            		}else if(menuObjs[i].icon=="delete"){
            			className="glyphicon glyphicon-remove-sign";
            		}else if(menuObjs[i].icon=="edit"){
            			className="glyphicon glyphicon-edit";
            		}else if(menuObjs[i].icon=="show"){
            			className="glyphicon glyphicon-eye-open";
            		}else if(menuObjs[i].icon=="setting"){
            			className="glyphicon glyphicon-cog";
            		}
	        		tag+="<a id='"+tree.rMenuId+"_"+menuObjs[i].id+"' class='list-group-item'>"+"&nbsp;&nbsp;&nbsp;<i class='"+className+"'></i>"+menuObjs[i].name+"&nbsp;&nbsp;&nbsp;</a>";
	        	}
	        	tag+="</div>";
	        	$("body").append(tag);
	        },
	        initRMenu:function(menuObjs){
	        	//生成右键菜单区
	        	tree.rMenuLiIds.push("#"+tree.rMenuId);
	        	tree.genRMenuTag(menuObjs);
                $("#"+tree.rMenuId).hover(function(){//设置进入右键菜单事件
                	for(var i=0;i<menuObjs.length;i++){
                		var rMenuLiId="#"+tree.rMenuId+"_"+menuObjs[i].id;
                		tree.bindClick($(rMenuLiId),menuObjs[i].clickEvent);
                	}
                },function(){//设置离开右键菜单事件
                    tree.hideItem();
                });                    
            },
            showRightMenu:function(postionJson,menuObjs){
            	//如果是根节点阻止右键菜单生成
            	//if(tree.pNode.code=="-1"){
            		//return;
            	//}
            	tree.rMenuId="rMenu_"+tree.treeId;
            	tree.initRMenu(menuObjs);//加载菜单选项的事件
                $("#"+tree.rMenuId).css({//设置右键菜单的位置
                    top:postionJson.y + "px",
                    left:postionJson.x+2 + "px",
                    display:"block",
                    position:"absolute"
                });
                $("#"+tree.rMenuId).find("a").each(function(){
                	$(this).css({cursor:"pointer"});
                });
                tree.showItem(tree.rMenuLiIds);
            },
            showItem:function(itemArray){//显示某些域
                for(var i = 0; i<itemArray.length; i++){
                    $(itemArray[i]).show();
                }
            },
            hideItem:function(itemArray){//隐藏某些域
                if(rkUtil.rkStrUtil.isNull(itemArray)){//如果为传入值，则禁用缺省的域
                    tree.hideItem(["#"+tree.rMenuId]);
                    return;
                }
                for(var i = 0; i<itemArray.length; i++){
                    $(itemArray[i]).hide();
                }
            },
            bindClick:function(obj,fn){//绑定click事件
                obj.unbind("click");
                obj.bind("click",fn);
            }
	};
	/**
	 * fnClick的参数：event, treeId, treeNode
	 */
	var initTree=function(eleId,url,param,checkType,menuObjs,fnClick){
		var isCheck=true;
		if(!rkUtil.rkStrUtil.isNull(checkType)){
			if(checkType!="checkbox"&&checkType!="radio"){
				checkType="checkbox";//避免异常的
				isCheck=false;
			}
		}else{
			checkType="checkbox";//避免异常的
			isCheck=false;
		}
		var setting={
				async:{
					enable: true,
					autoParam:[],
					otherParam:[],
					url:url,
					type:"post"
				},
				data:{
					simpleData: {
						enable: true,
						idKey: 'code',
						pIdKey: 'fatherCode',
						rootPId: '-1'
					}
				},
				check: {
					enable: isCheck,
					chkStyle: checkType,
					radioType: "all"
				},
				callback:{
					onClick:fnClick,
					onRightClick:function(event,treeId,treeNode){
						if(rkUtil.rkStrUtil.isNull(menuObjs)){
							return;
						}
						tree.treeId=treeId;
	                    tree.pNode = treeNode;
	                    //记录缓存节点信息
	                    rkStore.rkTreeRmenuParam.setParam(treeNode);
	                    //显示右键菜单
	                   tree.showRightMenu({x:event.clientX,y:event.clientY},menuObjs);
	                }
				}
		};
		var zTreeObj = $.fn.zTree.init($("#"+eleId), setting, null);
		return zTreeObj;
	};
	var getCheckNode=function(eleId){
		var nodes = getCheckNodes(eleId);
		return nodes[0];
	};
	var getCheckNodes=function(eleId){
		var treeObj = $.fn.zTree.getZTreeObj(eleId);
		var nodes = treeObj.getCheckedNodes(true);
		return nodes;
	};
	var getSelectedNodes=function(eleId){
		var treeObj = $.fn.zTree.getZTreeObj(eleId);
		var nodes = treeObj.getSelectedNodes();
		return nodes;
	}
	var getSelectedNode=function(eleId){
		var nodes = getSelectedNodes(eleId);
		if(!rkUtil.rkStrUtil.isNull(nodes)){
			return nodes[0];
		}else{
			return null;
		}
	}
	var refreshCurr=function(eleId){
		var treeObj = $.fn.zTree.getZTreeObj(eleId);
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length>0) {
			treeObj.reAsyncChildNodes(nodes[0], "refresh");
		}
	};
	var refreshAll=function(eleId){
		var treeObj = $.fn.zTree.getZTreeObj(eleId);
		treeObj.reAsyncChildNodes(null, "refresh");
	};
	return {
		initTree:initTree,
		getCheckNodes:getCheckNodes,
		refreshCurr:refreshCurr,
		refreshAll:refreshAll,
		getCheckNode:getCheckNode
	}
});