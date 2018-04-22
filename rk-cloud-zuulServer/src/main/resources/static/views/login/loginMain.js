require(['perfectScrollbar','switchery','modernizr',rkjsPath+'main.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',
         'css!/static/css/styles.css','css!/static/css/plugins.css','css!/static/css/themes/theme-1.css'], 
function(perfect,switchery,modernizr,main,rkFormUtil,rkAlert,rkAjax) {
	'use strict'
	main.init();
	$("#btn_userLogin").on("click", function(e) {
		rkFormUtil.submitForm("loginForm","/rk/web/core/user/user/login",function(data){
			if(data.statusCode==rkAjax.ajaxStatusCode.status200){
				rkAlert.succAlertMsg(data.msg);
				setTimeout(function(){
					location.href="/views/index/index.html";
				},1000);
			}else{
				rkAlert.errAlertMsg(data.msg);
			}
		});
	});
	$("#btn_register").on("click", function(e) {
		rkFormUtil.submitForm("registerForm","/rk/web/core/user/user/register",function(data){
			if(data.statusCode==rkAjax.ajaxStatusCode.status200){
				location.href="login_signin.html";
			}else{
				rkAlert.errAlertMsg(data.msg);
			}
		});
	})
	
});