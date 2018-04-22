/**
 * js相关操作工具
 * @author Cavion(曹仁道)
 */
var rkUtilObj;
define(['jquery'], function($) {
	'use strict'
	var rkConst={
			selectSplitStr:"|,|"
	};
	var rkStrUtil={
		isNull:function(valuex){//判断内容是否为空
			if(!valuex||valuex.length<1||valuex === "[]"||valuex === "{}"||typeof (valuex) == "undefined"||""==valuex||null==valuex||"null"==valuex||"undefined"==valuex||undefined==valuex||""==$.trim(valuex)){
				return true;
			}
			return false;
		},
		isTrue:function(valuex){
			if(typeof (valuex)=="boolean"&&valuex){
				return true;
			}
			return false;
		}
	};
	var getDataType=function(obj){
		if(obj instanceof Array){
	        return 'Array';
	    }else if(obj instanceof Number){ 
	        return 'Number';
	    }else if(!isNaN(obj)){ 
	        return 'NaN';
	    }else if(obj instanceof Object ){ 
	        return 'Object'; 
	    }else{ 
	        return 'String'; 
	    } 
	};
	
	rkUtilObj={
			rkConst:rkConst,
			rkStrUtil:rkStrUtil,
			getDataType:getDataType
	}
	return rkUtilObj;
})