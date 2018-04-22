require([ 'jquery', rkjsPath + 'rkTable.js', rkjsPath + 'rkTags.js', rkjsPath + 'rkWin.js', rkjsPath + 'rkFormUtil.js', rkjsPath + 'rkAlert.sweet.js', rkjsPath + 'rkAjax.js',rkjsPath+'rkUtil.js' ],
	function($, rkTable, rkTags, rkWin, rkFormUtil, rkAlert, rkAjax, rkUtil) {
		'use strict'
		var calcRecordList = rkTable.init("calcRecordList", "/rk/tool/cacl/record/web/queryPage", {}, "radio", "searchBtn");
		//初始化表单
		rkFormUtil.initForm($("#calcRecordForm"));
		var btns = rkTags.btn.yesBtn("calcRecordBtn", false,
			function() {//保存
				rkFormUtil.submitForm("calcRecordForm", "/rk/tool/cacl/record/web/insertBid", function(data) {
					rkAlert.succAlertMsg(data.msg);
					rkTable.reload(calcRecordList);
				});
			});
		$("#genRandomBtn").on("click",function(e){
			rkAjax.postAjaxNoErr("/rk/tool/cacl/record/genRandom",{},function(data){
				$("#calcRecordForm").find("#calcId").val(data.calcId);
				$("#calcRecordForm").find("#bidNum").val(data.random);
				rkTable.reload(calcRecordList);
			});
		});
		
		$("#batchBidCheckBtn").on("click",function(e){
			$("#repeatList_div").html("");
			rkFormUtil.submitForm("calcRecordBatchForm", "/rk/tool/cacl/record/web/batchBidCheck", function(data) {
				if(rkUtil.rkStrUtil.isNull(data)||data.length<1){
					rkAlert.succAlertMsg("没有重复的出价，可以使用！");
					return;
				}
				$("#calcRecordBatchForm").find("#calcId").val(data[0].calcId);
				for(var i=0;i<data.length;i++){
					var tag=" <button type='button' class='btn btn-danger' disabled='disabled'>"+data[i].repeatBidNum+"</button>";
					$("#repeatList_div").append(tag);
				}
				rkTable.reload(calcRecordList);
			});
		});
		var btnsBatch = rkTags.btn.yesBtn("calcRecordBatchBtn", false,
			function() {//保存
				$("#repeatList_div").html("");
				rkFormUtil.submitForm("calcRecordBatchForm", "/rk/tool/cacl/record/web/insertBatchBid", function(data) {
					rkAlert.succAlertMsg(data.msg);
					for(var i=0;i<data.length;i++){
						var tag=" <button type='button' class='btn btn-danger' disabled='disabled'>"+data[i].repeatBidNum+"</button>";
						$("#repeatList_div").append(tag);
					}
					rkTable.reload(calcRecordList);
				});
			});
	});