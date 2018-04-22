/**
 * @author:Cavion(曹仁道)
 * caorendao187@163.com
 * 自封装后台标签库
 */
define(['jquery','bootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkAjax.js',rkjsPath+'rkWin.js',rkjsPath+'rkStore.js',rkjsPath+'rkEditor.js'], 
		function($,bs,rkUtil,rkAlert,rkAjax,rkWin,rkStore,rkEditor) {
	'use strict'
	var tagIndex=0;//计数器
	/**
	 * button按钮组
	 */
	var btn={
		/**
		 * btnObj:[{btnEleId:"btn_add",btnClass:"btn-primary",name:"新增",iconClass:"glyphicon glyphicon-plus"},{btnEleId:"btn_del",btnClass:"btn-danger",name:"删除",iconClass:"glyphicon glyphicon-remove"}]
		 */
		createBtn:function(peleId,btnObjs,isRight){
			if(rkUtil.rkStrUtil.isNull(peleId)){
				rkAlert.errAlertMsg("父容器id不可为空");
			}
			//扫描按钮定义区
			var $rkBtn=$("#"+peleId).find("rk-btn");
			var groupClass="";
			if(rkUtil.rkStrUtil.isNull($rkBtn)){//自定义区不存在，采用配置参数
				if(isRight){
					groupClass=" pull-right";
				}
			}else{
				groupClass=$rkBtn.attr("groupClass");
			}
			var btnTpl="<div class='btn-group "+groupClass+"'></div>";//按钮公共模板
			if($("#"+peleId).find(".btn-group").length<1){//已经有了就不再创建了
				if(!rkUtil.rkStrUtil.isNull($rkBtn)){//有占位模版的话直接替换，没有的话在父容器内前面追加
					$rkBtn.replaceWith(btnTpl);
				}else{
					$("#"+peleId).prepend(btnTpl);
				}
			}
			
			//获取按钮定义
			for(var i=0;i<btnObjs.length;i++){
				var btnObj=btnObjs[i];
				var btnEleId=btnObj.btnEleId;
				var btnClass=btnObj.btnClass;
				var name=btnObj.name;
				var iconClass=btnObj.iconClass;
				if(rkUtil.rkStrUtil.isNull(peleId)||rkUtil.rkStrUtil.isNull(btnEleId)){
					rkAlert.errAlertMsg("生成的按钮id不可为空");
				}
				btnTpl="<button type='button' class='btn btn-sm "+btnClass+"' id='"+btnEleId+"'><i class='"+iconClass+"'></i><span class='hidden-xs'>"+name+"</span></button>";
				$("#"+peleId).find(".btn-group").append(btnTpl);
			}
			
			
		},
		btnDefault:function(peleId,isRight,name,iconClass,btnClass,fnClick){
			if(rkUtil.rkStrUtil.isNull(btnClass)){
				btnClass=" btn-default";
			}
			if(rkUtil.rkStrUtil.isNull(iconClass)){
				iconClass=" glyphicon-plus";
			}
			if(rkUtil.rkStrUtil.isNull(name)){
				name="无名";
			}
			var btnEleId=peleId+"_btn_"+tagIndex;
			tagIndex++;//计数器叠加
			var btnObjs=[{btnEleId:btnEleId,btnClass:btnClass,name:name,iconClass:iconClass}];
			btn.createBtn(peleId, btnObjs,isRight);
			//为该按钮添加监听事件
			$("#"+peleId).find("#"+btnEleId).on("click",fnClick);
			//返回该对象供用户其他操作
			return $("#"+peleId).find("#"+btnEleId);
		},
		addBtnByName:function(peleId,isRight,name,fnClick){
			var btnClass=" btn-info";
			return btn.btnDefault(peleId, isRight, name, "glyphicon glyphicon-plus", btnClass, fnClick);
		},
		addBtn:function(peleId,isRight,fnClick){
			return btn.addBtnByName(peleId, isRight, "新增", fnClick);
		},
		delBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-danger";
			return btn.btnDefault(peleId, isRight, "删除", "glyphicon glyphicon-remove", btnClass, fnClick);
		},
		editBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-primary";
			return btn.btnDefault(peleId, isRight, "编辑", "glyphicon glyphicon-edit", btnClass, fnClick);
		},
		addEditDelBtn:function(peleId,isRight,addFnClick,editFnClick,DelFnClick){
			var btnObjs=[];
			btnObjs.push(btn.btnDefault(peleId, isRight, "新增", "glyphicon glyphicon-plus", "btn-info", addFnClick));
			btnObjs.push(btn.btnDefault(peleId, isRight, "删除", "glyphicon glyphicon-remove", "btn-danger", DelFnClick));
			btnObjs.push(btn.btnDefault(peleId, isRight, "编辑", "glyphicon glyphicon-edit", "btn-primary", editFnClick));
			return btnObjs;
		},
		saveBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-success";
			return btn.btnDefault(peleId, isRight, "保存", "glyphicon glyphicon-save", btnClass, fnClick);
		},
		showBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-primary";
			return btn.btnDefault(peleId, isRight, "查看", "glyphicon glyphicon-eye-open", btnClass, fnClick);
		},
		cancelBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-default";
			return btn.btnDefault(peleId, isRight, "取消", "glyphicon glyphicon-floppy-remove", btnClass, fnClick);
		},
		closeBtn:function(peleId,isRight,name,fnClick){
			var btnClass=" btn-warning";
			return btn.btnDefault(peleId, isRight, "关闭", "glyphicon glyphicon-off", btnClass, fnClick);
		},
		saveCloseBtn:function(peleId,isRight,saveFnClick,closeFnClick){
			var btnObjs=[];
			btnObjs.push(btn.btnDefault(peleId, isRight, "保存", "glyphicon glyphicon-save", "btn-success", saveFnClick));
			btnObjs.push(btn.btnDefault(peleId, isRight, "关闭", "glyphicon glyphicon-off", "btn-warning", closeFnClick));
			return btnObjs;
		},
		importBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-primary";
			return btn.btnDefault(peleId, isRight, "导入", "glyphicon glyphicon-import", btnClass, fnClick);
		},
		exportBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-info";
			return btn.btnDefault(peleId, isRight, "导出", "glyphicon glyphicon-export", btnClass, fnClick);
		},
		importExportBtn:function(peleId,isRight,importFnClick,exportFnClick){
			var btnObjs=[];
			btnObjs.push(btn.btnDefault(peleId, isRight, "导入", "glyphicon glyphicon-import", "btn-primary", importFnClick));
			btnObjs.push(btn.btnDefault(peleId, isRight, "导出", "glyphicon glyphicon-export", "btn-info", exportFnClick));
			return btnObjs;
		},
		rightHandBtn:function(peleId,name,isRight,fnClick){
			var btnClass=" btn-info";
			return btn.btnDefault(peleId, isRight, name, "glyphicon glyphicon-hand-right", btnClass, fnClick);
		},
		yesBtn:function(peleId,isRight,fnClick){
			var btnClass=" btn-success";
			return btn.btnDefault(peleId, isRight, "确定", "glyphicon glyphicon-ok-circle", btnClass, fnClick);
		},
		yesCloseBtn:function(peleId,isRight,saveFnClick,closeFnClick){
			var btnObjs=[];
			btnObjs.push(btn.btnDefault(peleId, isRight, "确定", "glyphicon glyphicon-ok-circle", "btn-success", saveFnClick));
			btnObjs.push(btn.btnDefault(peleId, isRight, "关闭", "glyphicon glyphicon-off", "btn-warning", closeFnClick));
			return btnObjs;
		}
	};
	/**
	 * select
	 */
	var select={
		attribute:{},
		genSelectTag:function(attribute,arr){
			if(rkUtil.rkStrUtil.isNull(attribute.name)){
				attribute.name=attribute.field;
			}
			if(rkUtil.rkStrUtil.isNull(attribute.id)){
				attribute.id=attribute.name;
			}
			var tags="";
			if(!rkUtil.rkStrUtil.isNull(attribute.isDiv)&&attribute.isDiv==true){
				tags+="<div class='form-group' id='select_"+attribute.id+"_div'>";
			}
			if(!rkUtil.rkStrUtil.isNull(attribute.label)){
				tags+="<label class='control-label' ";
				if(!rkUtil.rkStrUtil.isNull(attribute.labelWidth)){
					tags+=" style='width:"+attribute.labelWidth+"' ";
				}
				tags+=" >"+attribute.label+"</label>";
			}
			tags+="<select class='selectpicker' name='"+attribute.name+"' id='"+attribute.id+"'";
			if(!rkUtil.rkStrUtil.isNull(attribute.isSearch)&&attribute.isSearch){
				tags+=" data-live-search='true' ";
			}
			if(!rkUtil.rkStrUtil.isNull(attribute.disabled)){
				tags+=" disabled='disabled' ";
			}
			if(!rkUtil.rkStrUtil.isNull(attribute.multiple)){
				tags+=" multiple='multiple' ";
			}
			
			if(!rkUtil.rkStrUtil.isNull(attribute.width)){
				tags+=" style='width:"+attribute.width+"' ";
			}
			tags+=" >";
			tags+="<option value=''>请选择...</option>";
			for(var i=0;i<arr.length;i++){
				var codeValue=arr[i].code;
				if(rkUtil.rkStrUtil.isNull(codeValue)){
					codeValue=arr[i].id;
				}
				tags+="<option value='"+codeValue+"'>"+arr[i].name+"</option>";
			}
			tags+="</select>";
			if(!rkUtil.rkStrUtil.isNull(attribute.isDiv)&&attribute.isDiv){
				tags+="</div>";
			}
			return tags;
			/*$("#"+pEleId).html(tags);
			if(fnBack){
				fnBack();
			}*/
		},
		genNoSearchSelect:function(attr,arr){
			attr.isSearch=false;
			if(rkUtil.rkStrUtil.isNull(attr.isDiv)&&attr.isDiv!=false){
				attr.isDiv=true;
			}
			return select.genSelectTag(attr, arr);
		},
		genSearchSelect:function(attr,arr){
			attr.isSearch=true;
			if(rkUtil.rkStrUtil.isNull(attr.isDiv)&&attr.isDiv!=false){
				attr.isDiv=true;
			}
			return select.genSelectTag(attr, arr);
		},
		genDicSelect:function(code,attr,fnBack){
			rkStore.rkDic.getDic(code,function(data){
				var arr=data;
				var selectTag=select.genSearchSelect(attr, arr);
				if(fnBack){
					fnBack(selectTag);
				}
			});
		},
		genNoSearchDicSelect:function(code,attr,fnBack){
			attr.isSearch=false;
			rkStore.rkDic.getDic(code,function(data){
				var arr=data;
				var selectTag=select.genNoSearchSelect(attr, arr);
				if(fnBack){
					fnBack(selectTag);
				}
			});
		},
		genSelectByUrl:function(attr,url,param,fnBack){
			rkAjax.postAjaxNoErr(url,param,function(data){
				var arr=data;
				var selectTag=select.genSearchSelect(attr, arr);
				if(fnBack){
					fnBack(selectTag);
				}
			});
		},
		genSelectByUrlNoSearch:function(attr,url,param,fnBack){
			rkAjax.postAjaxNoErr(url,param,function(data){
				var arr=data;
				var selectTag=select.genNoSearchSelect(attr, arr);
				if(fnBack){
					fnBack(selectTag);
				}
			});
		},
		createSelectNoSearchByUrl:function($pEleId,attr,url,param,fnBack){
			attr.isDiv=false;
			select.genSelectByUrlNoSearch(attr,url,param,function(selectTag){
				$pEleId.html(selectTag);
				if(fnBack){
					fnBack();
				}
			});
		},
		createNoSearchSelectByUrl:function($pEleId,field,label,url,param,fnBack){
			var attr={field:field,label:label};
			select.createSelectNoSearchByUrl($pEleId, attr, url, param, function(){
				if(fnBack){
					fnBack();
				}
			});
		},
		rkSelect:function($pEleId,fnBack){//根据页面标签构建
			$pEleId.find("rk-select").each(function(){
				var $this=$(this);
				var attr={};
				var id=$this.attr("id");
				var label=$this.attr("label");
				var field=$this.attr("field");
				var name=$this.attr("name");
				var disabled=$this.attr("disabled");
				var multiple=$this.attr("multiple");
				var required=$this.attr("required");
				var width=$this.attr("width");
				var labelWidth=$this.attr("labelWidth");
				var isSearch=$this.attr("isSearch");
				var dicCode=$this.attr("dicCode");
				var url=$this.attr("url");
				attr={id:id,label:label,field:field,name:name,disabled:disabled,required:required,multiple:multiple,width:width,labelWidth:labelWidth};
				if(!rkUtil.rkStrUtil.isNull(dicCode)){//字典
					if(!rkUtil.rkStrUtil.isNull(isSearch)&&isSearch){
						select.genDicSelect(dicCode,attr,function(selectTag){
							$this.replaceWith(selectTag);
							if(fnBack){
								fnBack();
							}
						});
					}else{
						select.genNoSearchDicSelect(dicCode,attr,function(selectTag){
							$this.replaceWith(selectTag);
							if(fnBack){
								fnBack();
							}
						});
					}
				}else if(!rkUtil.rkStrUtil.isNull(url)){
					if(!rkUtil.rkStrUtil.isNull(isSearch)&&isSearch){
						select.genDicSelect(attr,url,{},function(selectTag){
							$this.genSelectByUrl(selectTag);
							if(fnBack){
								fnBack();
							}
						});
					}else{
						select.genSelectByUrlNoSearch(attr,url,{},function(selectTag){
							$this.replaceWith(selectTag);
							if(fnBack){
								fnBack();
							}
						});
					}
				}
			});
		}
		
	};
	
	var choseBack={
			createChoseBack:function(pEleId,label,field,openUrl,param,winTitle,fnBack){
				var tag="<label class='control-label'>"+label+"</label>";
				tag+="<input type='hidden' name='"+field+"' id='"+field+"' />";
				tag+="<div class='input-group'><input type='text' id='"+field+"_show' disabled='disabled' class='form-control'><span class='input-group-btn'>";
				tag+="<button class='btn btn-default' id='"+field+"_choseBtn' type='button'>选择</button>";
				tag+="</span></div>";
				$("#"+pEleId).html(tag);
				$("#"+field+"_choseBtn").on("click",function(){
					rkWin.win(openUrl,param,winTitle,fnBack);
				});
			},
			noParamChoseBack:function(pEleId,label,field,openUrl,winTitle,fnBack){
				choseBack.createChoseBack(pEleId, label, field, openUrl, null, winTitle, fnBack);
			},
			simpleChoseBack:function(pEleId,label,field,openUrl,fnBack){
				choseBack.noParamChoseBack(pEleId, label, field, openUrl, "选择回调", fnBack);
			}
	};
	var input={
			attribute:{},
			genInputTag:function(attribute){//生成标签
				if(rkUtil.rkStrUtil.isNull(attribute.id)){
					attribute.id=attribute.fieldName;
				}
				var inputTag="<div class='form-group' id='input_"+attribute.id+"_div'>";
				if(!rkUtil.rkStrUtil.isNull(attribute.label)){
					inputTag+="<label class='control-label' ";
					if(!rkUtil.rkStrUtil.isNull(attribute.labelWidth)){
						inputTag+=" style='width:"+attribute.labelWidth+"' ";
					}
					inputTag+=" >"+attribute.label+"</label>";
				}
				inputTag+="<input class='form-control' type='"+attribute.dataType+"' name='"+attribute.fieldName+"' id='"+attribute.id+"' ";
				if(!rkUtil.rkStrUtil.isNull(attribute.disabled)){
					inputTag+=" disabled='disabled' ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.required)){
					inputTag+=" required ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.minlength)){
					inputTag+=" minlength='"+attribute.minlength+"' ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.maxlength)){
					inputTag+=" maxlength='"+attribute.maxlength+"' ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.width)){
					inputTag+=" style='width:"+attribute.width+"' ";
				}
				inputTag+="/></div>";
				return inputTag;
			},
			createInput:function($pEleId,attribute){//直接构建
				var inputTag=input.genInputTag(attribute);
				$pEleId.append(inputTag);
			},
			rkInput:function($pEleId){//根据页面标签构建
				$pEleId.find("rk-input").each(function(){
					var id=$(this).attr("id");
					var label=$(this).attr("label");
					var dataType=$(this).attr("type");
					var fieldName=$(this).attr("name");
					var disabled=$(this).attr("disabled");
					var required=$(this).attr("required");
					var minlength=$(this).attr("minlength");
					var maxlength=$(this).attr("maxlength");
					var width=$(this).attr("width");
					var labelWidth=$(this).attr("labelWidth");
					input.attribute={id:id,label:label,dataType:dataType,fieldName:fieldName,disabled:disabled,required:required,
							minlength:minlength,maxlength:maxlength,width:width,labelWidth:labelWidth};
					var inputTag=input.genInputTag(input.attribute);
					$(this).replaceWith(inputTag);
				});
			}
	};
	var textarea={
			attribute:{},
			genTextareaTag:function(attribute){//生成标签
				if(rkUtil.rkStrUtil.isNull(attribute.id)){
					attribute.id=attribute.fieldName;
				}
				var textareaTag="<div class='form-group'>";
				if(!rkUtil.rkStrUtil.isNull(attribute.label)){
					textareaTag+="<label class='control-label'>"+attribute.label+"</label>";
				}
				textareaTag+="<textarea  name='"+attribute.fieldName+"' id='"+attribute.id+"' ";
				if(rkUtil.rkStrUtil.isNull(attribute.render)||attribute.render=="false"||attribute.render==false){
					textareaTag+=" class='form-control'  ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.disabled)){
					textareaTag+=" disabled='disabled' ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.required)){
					textareaTag+=" required ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.rows)){
					textareaTag+=" rows='"+attribute.rows+"' ";
				}
				if(!rkUtil.rkStrUtil.isNull(attribute.cols)){
					textareaTag+=" cols='"+attribute.cols+"' ";
				}
				textareaTag+="></textarea></div>";
				return textareaTag;
			},
			createTextarea:function($pEleId,attribute){//直接构建
				if(rkUtil.rkStrUtil.isNull(attribute.id)){
					attribute.id=attribute.fieldName;
				}
				var textareaTag=textarea.genTextareaTag(attribute);
				$pEleId.append(textareaTag);
				//渲染智能编辑框
				if(!rkUtil.rkStrUtil.isNull(render)){
					if(render=="all"||render=="true"){
						//在此渲染智能编辑框
						setTimeout(function(){
							var rke=rkEditor.initEditor(id);
						},1000);
					}else if(render=="mini"){
						setTimeout(function(){
							var rke=rkEditor.initMiniEditor(id);
						},1000);
					}
				}
			},
			rkTextarea:function($pEleId){//根据页面标签构建
				$pEleId.find("rk-textarea").each(function(){
					var fieldName=$(this).attr("name");
					var id=$(this).attr("id");
					if(rkUtil.rkStrUtil.isNull(id)){
						id=fieldName;
					}
					var label=$(this).attr("label");
					var render=$(this).attr("render");//渲染方式：默认false(不渲染)，可选：false、all(true)、mini
					var disabled=$(this).attr("disabled");
					var required=$(this).attr("required");
					var rows=$(this).attr("rows");
					var cols=$(this).attr("cols");
					textarea.attribute={id:id,label:label,render:render,fieldName:fieldName,disabled:disabled,required:required,cols:cols,rows:rows};
					var textareaTag=textarea.genTextareaTag(textarea.attribute);
					$(this).replaceWith(textareaTag);
					//渲染智能编辑框
					if(!rkUtil.rkStrUtil.isNull(render)){
						if(render=="all"||render=="true"||render==true){
							//在此渲染智能编辑框
							setTimeout(function(){
								var rke=rkEditor.initEditor(id);
							},1000);
						}else if(render=="mini"){
							setTimeout(function(){
								var rke=rkEditor.initMiniEditor(id);
							},1000);
						}
					}
				});
			}
	};
	var dynamicCreateForm=function($form,listUrl,listParam,fnBack){
		rkAjax.postAjaxNoErr(listUrl,{},function(data){
			if(!rkUtil.rkStrUtil.isNull(data)&&data.length>0){
				for(var i=0;i<data.length;i++){
					var attribute={label:data[i].keyName,fieldName:data[i].keyCode};
					var dataType="text";
					if(rkUtil.rkStrUtil.isNull(data[i].dataType)||data[i].dataType=="1"){
						dataType="text";
						attribute.dataType=dataType;
						input.createInput($form,attribute);
					}else if(data[i].dataType=="2"){
						dataType="number";
						attribute.dataType=dataType;
						input.createInput($form,attribute);
					}else if(data[i].dataType=="3"){
						attribute.isDiv=true;
						if(data[i].selectDataType=="1"){//字典方式
							if(data[i].selectType=="2"){//多选
								attribute.multiple="multiple";
							}
							select.genNoSearchDicSelect(data[i].selectCode,attribute,function(selectTag){
								$form.append(selectTag);
							});
						}else if(data[i].selectDataType=="2"){//附加情况
							if(!rkUtil.rkStrUtil.isNull(data[i].choseUrl)&&!rkUtil.rkStrUtil.isNull(data[i].choseParam)){
								select.genSelectByUrlNoSearch(attribute,data[i].choseUrl,data[i].choseParam,function(selectTag){
									$form.append(selectTag);
								});
							}
						}
					}
				}
				if(fnBack){
					fnBack();
				}
			}
		});
	}
	
	/**
	 * 基于boostrap的分页
	 */
	var rkPage={
			isFirstInit:true,
			pageOptions:{//初始化情况
				eleId:"",//容器id
				listEleId:"",//list集合id
				ajax:{url:"",param:{}},
				pageSize : 10,//每页显示数据条数，server：length
				pageTotal : 0,//总页数
	        	currPage : 1,//当前页数
	        	recordsTotal : 0,//数据总记录数
	        	pageType : ["prevAndNext","startAndEnd"],//分页插件类型数组，包含类型说明："prevAndNext"：显示上一页和下一页；"startAndEnd"：显示开始页和结束页
	        	prevAndNextText:["上一页","下一页"],
	        	startAndEndText:["首页","末页"],
	        	maxPageButton: 4,//显示页码的按钮个数
	        	pageMethod:"compage",//分页方式，滚动加载："scoreLoad"；普通分页："compage"
	        	onPageClicked : null//单击分页按钮事件
			},
			createPage:function(options,fnBack){
				if(!rkUtil.rkStrUtil.isNull(options)){
					if(rkUtil.rkStrUtil.isNull(options.eleId)){
						rkAlert.alert("必须指定一个分页插件容器！");
						return;
					}
					if(rkUtil.rkStrUtil.isNull(options.ajax)||rkUtil.rkStrUtil.isNull(options.ajax.url)){
						rkAlert.alert("请指定一个请求的url");
						return;
					}
				}else{
					rkAlert.alert("必须指定一个分页插件容器！");
					return;
				}
				if(options.recordsTotal==0){//没有数据
					return;
				}
				if(options){
					options=$.extend(rkPage.pageOptions, options);
				}
				//清除已有的分页li,然后重新构建
				var $ele=$("#"+options.eleId);
				if($ele.attr("class")!="pagination"){//如果类不对，先替换
					$ele.attr("class","pagination");
				}
				$ele.html("");
				if(rkPage.isFirstInit){
					rkPage.ajaxPage(options,fnBack);
					rkPage.isFirstInit=false;
				}else{
					/*init($ele,options);
					$ele.find("li").each(function(){
						rkPage.bindClickPage($(this), options,fnBack);
					});*/
				}
			},
			/*onClickPage:function($ele){//单击分页
				var firstBtn = $ele.find('li span.start').parent();
				var preBtn = $ele.find('li span.prev').parent();
				var lastBtn = $ele.find('li span.end').parent();
				var nextBtn = $ele.find('li span.next').parent();
				
			},*/
			bindClickPage:function($pageBtn,options,fnBack){//绑定翻页
				$pageBtn.on("click",function(){
					var $this=$(this);
					if($this.attr("class")=="disabled"){
						return;
					}
					var $span=$this.find("span");
					var spanClass=$span.attr("class");
					if(spanClass=="start"){
						options.currPage=1;
					}else if(spanClass=="end"){//待处理
						options.currPage=options.pageTotal;
					}else if(spanClass=="prev"){
						options.currPage=(options.currPage-1);
					}else if(spanClass=="next"){
						options.currPage=(options.currPage+1);
					}else{
						var pNum=$span.attr("pNum");
						if(!rkUtil.rkStrUtil.isNull(pNum)){
							options.currPage=parseInt(pNum);
						}else{
							return;
						}
					}
					rkPage.ajaxPage(options,fnBack);
				});
			},
			scoreLoad:function(options,fnBack){
				$(document).ready(function (){
			        var nScrollHeight = 0; //滚动距离总长(注意不是滚动条的长度)
			        var nScrollTop = 0;   //滚动到的当前位置
			        $(window).scroll(function(){
			        	var currScrollHeight = $(this).height();
			        	nScrollTop = $(this).scrollTop();
			        	if(nScrollTop + currScrollHeight >= nScrollHeight){
			        		nScrollHeight=nScrollTop + currScrollHeight;
			        		options.currPage=options.currPage+1;
			        		rkPage.ajaxPage(options,fnBack);
			        	}
			         });
			     });
			},
			ajaxPage:function(options,fnBack){
				var $ele=$("#"+options.eleId);
				//var pageOptions=rkPage.pageOptions;
				var url=options.ajax.url;
				var param=options.ajax.param;
				var listEleId=options.listEleId;
				var currPage=options.currPage;
				var prevPage=currPage>0?(currPage-1):0;
				param.start=prevPage*options.pageSize;
				param.length=options.pageSize;
				rkAjax.postAjaxNoErr(url,param,function(data){
					if(!rkUtil.rkStrUtil.isNull(data)&&!rkUtil.rkStrUtil.isNull(data.data)&&data.data.length>0){
						options.recordsTotal=data.recordsTotal;
						options.pageTotal=data.pageTotal;
						//rkPage.createPage(options,fnBack);
						rkPage.init(options);
						if(options.pageMethod=="compage"){//普通分页
							$("#"+listEleId).html("");
							$ele.find("li").each(function(){
								rkPage.bindClickPage($(this), options,fnBack);
							});
						}else if(options.pageMethod=="scoreLoad"){//滚动加载
							rkPage.scoreLoad(options,fnBack);
						}
						if(fnBack){
							fnBack(data.data);
						}
					}
				});
			},
			init:function(options){
				//初始化分页页数
				if(options.pageTotal==0){
					options.pageTotal=Math.ceil(options.recordsTotal/options.pageSize);
				}
				if(options.pageTotal==0){
					return;
				}
				if(options.pageMethod=="compage"){//普通分页
					rkPage.genPage(options);
				}else if(options.pageMethod=="scoreLoad"){//滚动加载
					
				}
				
			},
			genPage:function(options){//生成分页码
				var $ele=$("#"+options.eleId);
				$ele.html("");
				//开始页和上一页
				var pageType=options.pageType;
				var prevAndNextText=options.prevAndNextText;
				var startAndEndText=options.startAndEndText;
				for(var i=0;i<pageType.length;i++){
					if(pageType[i]=="startAndEnd"){//开始页
						$ele.prepend('<li class="disabled"><span class="start"><span aria-hidden="true">'+startAndEndText[0]+'</span></span></li>');
					}else if(pageType[i]=="prevAndNext"){
						$ele.append('<li class="disabled"><span class="prev"><span aria-hidden="true">'+prevAndNextText[0]+'</span></span></li>');
					}
				}
				//页码部分
				var currPage=options.currPage;
				var pageTotal=options.pageTotal;
				var pageCount = pageTotal < options.maxPageButton ? pageTotal : options.maxPageButton;
        		var pNum = currPage<2?1:(currPage-1);
        		for(var index = 1; index <= pageCount&&pNum<=pageTotal; index++) {
        			if(index==1){
        				if(pNum>1){
        					$ele.append('<li class="disabled"><span class="page" >...</span></li>');
        				}
        			}
        			if(pNum==currPage){//当前页选中
        				$ele.append('<li class="active"><span class="page" pNum="'+pNum+'">'+pNum+'<span class="sr-only">(current)</span></span></li>');
        			}else{
        				$ele.append('<li><span class="page" pNum="'+pNum+'">'+pNum+'</span></li>');
        			}
        			if(index == pageCount){
        				if(pNum<pageTotal){
        					$ele.append('<li class="disabled"><span class="page" >...</span></li>');
        				}
        			}
        			pNum++;
        		}
        		//结束页
        		for(var i=0;i<pageType.length;i++){
					if(pageType[i]=="startAndEnd"){//结束页
						$ele.append('<li class="disabled"><span class="end"><span aria-hidden="true">'+startAndEndText[1]+'</span></span></li>');
					}else if(pageType[i]=="prevAndNext"){
						$ele.append('<li class="disabled"><span class="next"><span aria-hidden="true">'+prevAndNextText[1]+'</span></span></li>');
					}
				}
        		
        		if(currPage>1){//如果当前页大于1，首页和上一页可操作
        			$ele.find("li span.start").parent().removeClass("disabled");
        			$ele.find("li span.prev").parent().removeClass("disabled");
        		}else{
        			$ele.find("li span.start").parent().addClass("disabled");
        			$ele.find("li span.prev").parent().addClass("disabled");
        		}
        		if(currPage<=options.pageTotal){//如果当前页小于最大页数，末页和下一页可操作
        			$ele.find("li span.end").parent().removeClass("disabled");
        			$ele.find("li span.next").parent().removeClass("disabled");
        		}else{
        			$ele.find("li span.end").parent().addClass("disabled");
        			$ele.find("li span.next").parent().addClass("disabled");
        		}
        		$ele.find("li span").css("cursor","pointer");
			}
	}
	
	return {
		btn:btn,
		select:select,
		choseBack:choseBack,
		rkPage:rkPage,
		input:input,
		textarea:textarea,
		dynamicCreateForm:dynamicCreateForm
	}
});