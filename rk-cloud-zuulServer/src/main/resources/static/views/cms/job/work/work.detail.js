require(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkWin.js',rkjsPath+'rkStore.js',rkjsPath+'rkTags.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkTree.js',
         rkjsPath+'rkEditor.js'], 
		function($,bt,rkUtil,rkWin,rkStore,rkTags,rkFormUtil,rkAlert,rkAjax,rkTree,rkEditor,rkFileUpload) {
	'use strict'
	var workContentId;
	var workId;
	rkFormUtil.initForm($("#workContentForm"));
	rkFormUtil.initForm($("#workForm"));
	var winParam=rkStore.rkParam.getParam();
	//初始化编辑器
	var rke=rkEditor.initEditor("content");
	
	$("#uploadThumbBtn").on("click",function(){
	rkWin.win("/views/sysManager/infoServicing/fileManage/chooseBack/file.list.html",{moduleCode:"cmsJobWork",opareType:""},"选择文件",
		function(){
			var imgs=rkStore.rkChooseBcakParam.getParam();
			$("#workContentForm").find("#thumbnail").val(imgs[0]);
			$("#thumbnailImg").attr("src",imgs[0]);
		});
	});
	var programaCode="";
	var rkTreeObj=rkTree.initTree("workProgramaTree","/rk/admin/cms/job/programa/queryTree",null,"radio",null,function(event, treeId, treeNode){
		programaCode=treeNode.code;
		$("#workContentForm").find("#programaCode").val(programaCode);
		showChoosePosition(programaCode);
		
	});
	var btnObjs=[];
	btnObjs.push(rkTags.btn.btnDefault("workBtn", false, "保存", "glyphicon glyphicon-save", "btn-primary", function(){
		saveWorkContent("3");
	}));
	btnObjs.push(rkTags.btn.btnDefault("workBtn", false, "保存并公开", "glyphicon glyphicon-yes", "btn-success", function(){
		saveWorkContent("1");
	}));
	btnObjs.push(rkTags.btn.btnDefault("workBtn", false, "结束", "glyphicon glyphicon-off", "btn-default", function(){
		saveWorkContent("2");
	}));
	var saveWorkContent=function(status){
		$("#workForm").find("#status").val(status);
		if(rkUtil.rkStrUtil.isNull(status)){
			rkAlert.errAlertMsg("错误请求");
			return;
		}
		var $workContentForm=$("#workContentForm");
		if(rkUtil.rkStrUtil.isNull($workContentForm.find("#programaCode").val())){
			rkAlert.errAlertMsg("请选择行业");
			return;
		}
		//保存内容
		rkFormUtil.submitForm("workContentForm","/rk/admin/cms/mall/content/save",function(data){
			workContentId=data.msg;
			$("#workForm").find("#pubContentId").val(workContentId);
			$("#workContentForm").find("#id").val(workContentId);
			saveWorkInfo();
		});
	};
	var saveWorkInfo=function(){
		var $workForm=$("#workForm");
		rkFormUtil.submitForm("workForm","/rk/admin/cms/job/work/save",function(data){
			workId=data.msg;
			$("#workForm").find("#id").val(workId);
			rkAlert.succAlertMsg("保存成功");
		});
	};
	if(!rkUtil.rkStrUtil.isNull(winParam)&&!rkUtil.rkStrUtil.isNull(winParam.id)){
		workContentId=winParam.id;
		var loadWorkUrl="/rk/admin/cms/job/work/queryByContentId"
		rkAjax.postAjaxNoErr(loadWorkUrl,{workContentId:workContentId},function(data){
			workId=data.id;
			$("#workForm").find("#id").val(workId);
			fillFormData($("#workForm"),data);
		});
		
		//加载工作内容
		var loadWorkContentUrl="/rk/web/cms/job/content/query";
		rkAjax.postAjaxNoErr(loadWorkContentUrl,{id:workContentId},function(data){
			fillFormData($("#workContentForm"),data);
			if(!rkUtil.rkStrUtil.isNull(data.thumbnail)){
				$("#thumbnailImg").attr("src",data.thumbnail);
			}
		});
	}
	
	//显示职位选择
	var showChoosePosition=function(programaCode){
		var $workPositionChoose=$("#workPositionChoose");
		$workPositionChoose.html("");
		var loadPragPosiUrl="/rk/admin/cms/job/pragPosi/queryList";
		rkAjax.postAjaxNoErr(loadPragPosiUrl,{programaCode:programaCode},function(data){
			if(!rkUtil.rkStrUtil.isNull(data)&&data.length>0){
				for(var i=0;i<data.length;i++){
					var positionCode=data[i].positionCode;
					var tag='<div class="input-group" id="div_'+positionCode+'">';
					tag+='<span class="input-group-addon">';
			        tag+='<input type="radio" aria-label="" value="'+data[i].positionCode+'" name="positionId_code" id="positionId_code">';
			        tag+='</span>';
			        tag+='<input type="text" class="form-control" aria-label="" value="'+data[i].positionName+'" disabled="disabled">';
			        tag+='</div>';
			        $workPositionChoose.append(tag);
			        //设置单击事件
					$workPositionChoose.on("click","#div_"+positionCode,function(){
						var $this=$(this);
						$workPositionChoose.find(":radio").each(function(){
							$(this).attr("checked",false);
						});
						$this.find(":radio").attr("checked",true);
						$("#workForm").find("#positionCode").val(positionCode);;
					});
				}
			}
		});
	}
	
	
	//区域选择
	var areaUrl="/rk/admin/core/sys/area/queryChildByCode";
	rkTags.select.createNoSearchSelectByUrl($("#province_div"),"province","省/直辖市/自治区",areaUrl,{code:"11A"},function(){
		rkFormUtil.initForm($("#workForm"));
		provinceChange();
	});
	var provinceChange=function(){
		$("#province").on("change",function(){
			var province=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(province)){
				$("#state_div").show("slow");
				rkTags.select.createNoSearchSelectByUrl($("#state_div"),"state","市/州",areaUrl,{code:province},function(){
					rkFormUtil.initForm($("#workForm"));
					stateChange();
				});
				$("#area").val(province);
			}
		});
	}
	var stateChange=function(){
		$("#state").on("change",function(){
			var province=$("#province").val();
			var state=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(state)){
				$("#prefecture_div").show("slow");
				rkTags.select.createNoSearchSelectByUrl($("#prefecture_div"),"prefecture","县/区",areaUrl,{code:state},function(){
					rkFormUtil.initForm($("#workForm"));
					prefectureChange();
				});
				$("#area").val(province+","+state);
			}
		});
	}
	var prefectureChange=function(){
		$("#prefecture").on("change",function(){
			var province=$("#province").val();
			var state=$("#state").val();
			var prefecture=$(this).val();
			if(!rkUtil.rkStrUtil.isNull(prefecture)){
				$("#workForm").find("#area").val(province+","+state+","+prefecture);
			}
		});
	}
});

