/**
 * @author:Cavion(曹仁道)
 * caorendao187@163.com
 * 缓存记录器
 */
define(['jquery',rkjsPath+'rkUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js'], function($,rkUtil,rkAlert,rkAjax) {
	'use strict'
	var local=window.localStorage;
	var session=window.sessionStorage;
	var paramObj=new Object();
	var rkParam={
			paramKey:"rk_win_param_store",
			setParam:function(paramObj){
				var objStr=JSON.stringify(paramObj);
				local.setItem(rkParam.paramKey,objStr);
			},
			getParam:function(){
				var result=local.getItem(rkParam.paramKey);
				return JSON.parse(result);
			},
			clearParam:function(){
				local.removeItem(rkParam.paramKey);
			}
	};
	var rkTabParam={
			paramKey:"rk_tab_param_store",
			setParam:function(paramObj){
				var objStr=JSON.stringify(paramObj);
				local.setItem(rkTabParam.paramKey,objStr);
			},
			getParam:function(){
				var result=local.getItem(rkTabParam.paramKey);
				return JSON.parse(result);
			},
			clearParam:function(){
				local.removeItem(rkTabParam.paramKey);
			}
	};
	var rkChooseBcakParam={
			paramKey:"rk_win_chooseBack_param_store",
			setParam:function(paramObj){
				var objStr=JSON.stringify(paramObj);
				local.setItem(rkChooseBcakParam.paramKey,objStr);
			},
			getParam:function(){
				var result=local.getItem(rkChooseBcakParam.paramKey);
				return JSON.parse(result);
			}
	};
	var rkTreeRmenuParam={
			paramKey:"rk_win_treeRmenu_param_store",
			setParam:function(paramObj){
				var objStr=JSON.stringify(paramObj);
				local.setItem(rkTreeRmenuParam.paramKey,objStr);
			},
			getParam:function(){
				var result=local.getItem(rkTreeRmenuParam.paramKey);
				return JSON.parse(result);
			}
	};
	var rkWinIndex={
			winIndex:"rk_win_index_store",
			newWin:function(index){
				var winIndexs=[];
				if(!rkUtil.rkStrUtil.isNull(local.getItem(rkWinIndex.winIndex))){
					winIndexs=JSON.parse(local.getItem(rkWinIndex.winIndex));
				}
				winIndexs.push(index);
				local.setItem(rkWinIndex.winIndex,JSON.stringify(winIndexs));
				return winIndexs;
			},
			closeWin:function(){
				var winIndexs=JSON.parse(local.getItem(rkWinIndex.winIndex));
				var index=winIndexs.pop();
				local.setItem(rkWinIndex.winIndex,JSON.stringify(winIndexs));
				return index;
			}
	};
	var rkDic={
			rkDicKey:"rk_dic_store",
			dicUrl:"/rk/web/core/sys/dictionary/queryDicListByValue",
			//dicObj:{code:"SF",name:"是否",childData:[{code:"1",name:"是"},{code:"0",name:"否"}]},
			setDic:function(dicObj){
				var allDicObj=[];
				//先读取原来的
				allDicObj=JSON.parse(local.getItem(rkDic.rkDicKey));
				if(rkUtil.rkStrUtil.isNull(allDicObj)){
					allDicObj=[];
				}
				//校验是否重复
				var isExit=false;
				for(var i=0;i<allDicObj.length;i++){
					var currDicObj=allDicObj[i];
					if(currDicObj.code==dicObj.code){
						isExit=true;
						allDicObj.splice(i,1);//已经存在先删除
						break;
					}
				}
				allDicObj.push(dicObj);
				var objStr=JSON.stringify(allDicObj);
				local.setItem(rkDic.rkDicKey,objStr);
			},
			getDicByUrl:function(code){
				rkAjax.postAjaxNoErr(rkDic.dicUrl,{value:code},function(data){
					if(!rkUtil.rkStrUtil.isNull(data.msg)){
						rkAlert.errAlertMsg(data.msg);
					}else{
						var dicObj={};
						for(var i=0;i<data.length;i++){//第一个循环，构建第一层
							var obj=data[i];
							if(obj.value==code){
								dicObj.code=obj.value;
								dicObj.tCode=obj.code;
								dicObj.name=obj.name;
								data.splice(i,1);
								dicObj.childData=rkDic.genDicNode(data, dicObj.tCode);
								break;
							}
						}
						rkDic.setDic(dicObj);
						return dicObj;
					}
				});
			},
			genDicNode:function(data,dicTcode){
				var dicChild=[];
				for(var i=0;i<data.length;i++){
					var obj=data[i];
					if(obj.fatherCode==dicTcode){
						var dicObj={};
						dicObj.code=obj.value;
						dicObj.tCode=obj.code;
						dicObj.name=obj.name;
						dicObj.childData=rkDic.genDicNode(data,obj.code);
						dicChild.push(dicObj);
						
					}
				}
				return dicChild;
			},
			getDic:function(code,fnBack,code1,code2,code3){
				var allDicObj=JSON.parse(local.getItem(rkDic.rkDicKey));
				if(rkUtil.rkStrUtil.isNull(allDicObj)||allDicObj.length<1){
					var dicObj=rkDic.getDicByUrl(code);
					if(!rkUtil.rkStrUtil.isNull(code1)){
						fnBack(rkDic.analyDic(dicObj.childData, code1, code2, code3, null));
					}
				}else{
					var dicChild=rkDic.analyDic(allDicObj, code, code1, code2, code3);
					if(!rkUtil.rkStrUtil.isNull(dicChild)){
						fnBack(dicChild);
					}else{
						var dicObj=rkDic.getDicByUrl(code);
						if(!rkUtil.rkStrUtil.isNull(code1)){
							fnBack(rkDic.analyDic(dicObj.childData, code1, code2, code3, null));
						}
					}
				}
			},
			analyDic:function(allDicObj,code,code1,code2,code3){
				for (var i = 0; i < allDicObj.length; i++) {
					var dicObj=allDicObj[i];
					if(code==dicObj.code){
						if(!rkUtil.rkStrUtil.isNull(code1)){
							var dicObjChild=dicObj.childData;
							for(var j=0;j<dicObjChild.length;j++){
								var dicObj1=dicObjChild[j];
								if(code1==dicObj1.code){
									if(!rkUtil.rkStrUtil.isNull(code2)){
										var dicObj1Child=dicObj1.childData;
										for(var k=0;k<dicObj1Child.length;k++){
											var dicObj2=dicObj1Child[k];
											if(code2==dicObj2.code){
												if(!rkUtil.rkStrUtil.isNull(code3)){
													var dicObj2Child=dicObj2.childData;
													for(var m=0;m<dicObj2Child.length;m++){
														var dicObj3=dicObj2Child[m];
														if(code3==dicObj3.code){
															return dicObj3.childData;
														}
													}
												}else{
													return dicObj2.childData;
												}
											}
										}
									}else{
										return dicObj1.childData;
									}
								}
							}
						}else{
							return dicObj.childData
						}
					}
				}
			}
	};
	
	return {
		rkParam:rkParam,
		rkTabParam:rkTabParam,
		rkWinIndex:rkWinIndex,
		rkChooseBcakParam:rkChooseBcakParam,
		rkTreeRmenuParam:rkTreeRmenuParam,
		rkDic:rkDic
	}
});