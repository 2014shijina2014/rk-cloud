require([ 'jquery', 'bootstrap', rkjsPath + 'rkUtil.js', rkjsPath + 'rkStore.js', rkjsPath + 'rkTags.js', rkjsPath + 'rkFormUtil.js', rkjsPath + 'rkAlert.sweet.js', rkjsPath + 'rkWin.js', rkjsPath + 'rkAjax.js' ],
	function($, bt, rkUtil, rkStore, rkTags, rkFormUtil, rkAlert, rkWin, rkAjax) {
		'use strict'
		//动态构建表单
		rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfoKey/queryList", {}, function(data) {
			if (!rkUtil.rkStrUtil.isNull(data) && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var dataType = "text";
					if (data[i].dataType == "2") {
						dataType = "number";
					}
					var attribute={label:data[i].keyName,dataType:dataType,fieldName:data[i].keyCode};
					rkTags.input.createInput($("#userInfoForm"),attribute);
					rkFormUtil.initForm($("#userInfoForm"));
				}
				loadData();
			}
		});
		//填充数据
		var winParam = rkStore.rkParam.getParam();
		var loadData = function() {
			if (!rkUtil.rkStrUtil.isNull(winParam)) {
				if (!rkUtil.rkStrUtil.isNull(winParam.userId)) {
					rkAjax.postAjaxNoErr("/rk/admin/core/user/userInfo/query", {
						userId : winParam.userId
					}, function(data) {
						if (!rkUtil.rkStrUtil.isNull(data)) {
							rkFormUtil.fillFormData($("#userInfoForm"), data);
						}
					});
				}
				if (!rkUtil.rkStrUtil.isNull(winParam.method) && winParam.method == "show") { //查看，禁止编辑和提交
					$("#userInfoForm").find("input").each(function() {
						$(this).attr("disabled", "disabled");
					});
					$("#userInfoForm").find("select").each(function() {
						$(this).attr("disabled", "disabled");
					});
					//按钮
					var btns = rkTags.btn.closeBtn("userInfoBtn", false,
						function() { //关闭
							parent.closeWin(false);
						});
				} else {
					//按钮
					var btns = rkTags.btn.saveCloseBtn("userInfoBtn", false,
						function() { //保存
							rkFormUtil.submitForm("userInfoForm", "/rk/admin/core/user/userInfo/save", function(data) {
								rkAlert.succAlertMsg(data.msg);
								//关闭窗口
								parent.closeWin(true);
							});
						},
						function() { //关闭
							parent.closeWin(false);
						});
				}
			}
		}

	});