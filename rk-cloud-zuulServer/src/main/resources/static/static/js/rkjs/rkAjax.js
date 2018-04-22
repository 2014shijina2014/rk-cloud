/**
 * Ajax请求js工具库
 */
define(['jquery',rkjsPath+'rkUtil.js',rkjsPath+'rkAlert.sweet.js'], function($,rkUtil,rkAlert) {
	//这是一个总方法，也是所有ajax请求的最终入口，一定会经过这里
	var ajaxStatusCode={
			status200:"200",
			status302:"302",
			status400:"400",
			status500:"500",
			status404:"404"
	}
	var ajax=function(url,type,dataType,param,isNoDataTip,fnBack,errFnBack){
		$.ajax({
		    type: type,
		    url: url ,
		    data: param ,
		    success: function(data){
		    	if(rkUtil.rkStrUtil.isNull(data)&&isNoDataTip){
		    		rkAlert.alert("没有找到数据");
		    	}else{
		    		//未登录处理
		    		if(data.statusCode==ajaxStatusCode.status302&&data.msg=="login"){
		    			rkAlert.errAlertMsg("您没有登陆或登陆超时，请重新登陆！");
		    			return;
		    		}
		    		if(!rkUtil.rkStrUtil.isNull(data.statusCode)&&data.statusCode!=ajaxStatusCode.status200){
		    			rkAlert.errAlertMsg(data.msg);
		    			return;
		    		}
		    		fnBack(data);
		    	}
		    },
		    dataType:dataType,
		    beforeSend:function(XHR){},
		    complete:function(XHR, TS){},
		   /* dataFilter:function(data,type){
		    	//前置过滤
		    	return data;
		    },*/
		    error:function(XHR,err,ex){
		    	//有以下三个参数：XMLHttpRequest 对象、错误信息、（可选）捕获的异常对象。
		    	//如果发生了错误，错误信息（第二个参数）除了得到 null 之外，还可能是 "timeout", "error", "notmodified" 和 "parsererror"
		    	console.log(XHR);
		    	var respText=XHR.responseText;
		    	console.log(respText);
		    	if(!rkUtil.rkStrUtil.isNull(respText)){
		    		if(respText.indexOf("statusCode:'302'")>0&&respText.indexOf("msg:'login'")>0){
		    			rkAlert.errAlertMsg("您没有登陆或登陆超时，请重新登陆！");
		    			setTimeout(function(){location.herf="/views/login/login_signin.html";},2000);
		    			return;
		    		}else{
		    			rkAlert.errAlertMsg(respText.substring((respText.indexOf("msg:'")+5),respText.indexOf("',succUrl")));
		    			return;
		    		}
		    	}
		    	if(errFnBack==null){
		    		rkAlert.errAlertMsg("请指定错误处理方案！");
		    	}else{
		    		/*if(err=="parsererror"){
		    			err="数据转换错误";
		    		}
		    		errFnBack(err);*/
		    	}
		    }
		});
	};
	
	var postAjax=function(url,param,isNoDataTip,fnBack,errFnBack){
		ajax(url,"POST",'json',param,isNoDataTip,fnBack,errFnBack);
	};
	var getAjax=function(url,param,isNoDataTip,fnBack,errFnBack){
		ajax(url,"GET",'json',param,isNoDataTip,fnBack,errFnBack);
	};
	var htmlAjax=function(url,fnBack,errFnBack){
		ajax(url,"GET",'html',{},false,fnBack,errFnBack);
	};
	/**
	 * post请求自动处理出错
	 */
	var postAjaxNoErr=function(url,param,fnBack){
		postAjax(url,param,true,fnBack,function(err){
			rkAlert.errAlertMsg(err);
		});
	}
	return {
		ajax:ajax,
		postAjax:postAjax,
		getAjax:getAjax,
		htmlAjax:htmlAjax,
		postAjaxNoErr:postAjaxNoErr,
		ajaxStatusCode:ajaxStatusCode
	}
})