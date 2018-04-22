/**
 * alert,提示框
 */
define(["jquery","sweetalert"], function($,swal) {
	var alertPro={
			title: "",
			text: "仁科系统，让你专注自己的事情",
			type: "success",
			confirmButtonColor: "#AEDEF4",
			confirmButtonText: "确定",
			showCancelButton: false,
			cancelButtonText: "取消",
			closeOnConfirm: false,
			closeOnCancel: false,
			imageUrl:""
	};
	var comAlert=function(fnConfirm,fnCancel){//type:warning/
		if(fnConfirm||fnCancel){
			swal(alertPro,function(isConfirm){
				if (isConfirm){
					if(fnConfirm){
						fnConfirm;
					}
				} else {
					if(fnConfirm){
						fnCancel;
					}
				}
			});
		}else{
			swal(alertPro);
		}
	};
	var alert=function(msg){
		alertPro.text=msg;
		alertPro.type="success";
		comAlert(null, null);
	};
	var succAlert=function(title,msg){
		alertPro.title=title;
		alertPro.text=msg;
		alertPro.type="success";
		comAlert(null, null);
	};
	var succAlertMsg=function(msg){
		alertPro.title="成功";
		alertPro.text=msg;
		alertPro.type="success";
		comAlert(null, null);
	};
	var errAlert=function(title,msg){
		alertPro.title=title;
		alertPro.text=msg;
		alertPro.type="error";
		comAlert(null, null);
	};
	var errAlertMsg=function(msg){
		alertPro.title="发生错误";
		alertPro.text=msg;
		alertPro.type="error";
		comAlert(null, null);
	};
	var confirmAlert=function(title,msg,fnConfirm,fnCancel){
		alertPro.title=title;
		alertPro.text=msg;
		alertPro.type="warning";
		comAlert(fnConfirm, fnCancel);
	};
	var confirmDelAlert=function(fnConfirm){
		confirmAlert("删除", "确定删除选中记录吗？", fnConfirm, null);
	};
	return {
		alert:alert,
		errAlert:errAlert,
		errAlertMsg:errAlertMsg,
		succAlert:succAlert,
		succAlertMsg:succAlertMsg,
		confirmAlert:confirmAlert,
		confirmDelAlert:confirmDelAlert
	}
})