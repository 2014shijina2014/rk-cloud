//datatables.net
var rkTableObj;
define(['jquery','dataTablesBootstrap',rkjsPath+'rkUtil.js',rkjsPath+'rkFormUtil.js',rkjsPath+'rkAlert.sweet.js',rkjsPath+'rkStore.js',rkjsPath+'rkAjax.js'],
		function($,dtb,rkUtil,rkFormUtil,rkAlert,rkStore,rkAjax) {
	'use strict'
	var $table;
	var init=function(tableElement, urlPath,params,selectType,searchBtn){
		$table=$("#"+tableElement);
		var table=$table.DataTable({
			language : { // 汉化
				decimal:        "",
			    emptyTable:     "没有找到您要的内容",
			    info:           "从_START_ 到 _END_ 条记录（共 _TOTAL_ 条）",
			    infoEmpty:      "显示 _START_ 到 _END_ 条记录",
			    infoFiltered:   "(全部记录数 _TOTAL_ 条)",
			    infoPostFix:    "",
			    thousands:      ",",
			    lengthMenu:     "显示_MENU_条",
			    loadingRecords: "正在加载数据...",
			    processing:     "正在加载数据...",
			    search:         "",
			    zeroRecords:    "没有找到您要的内容",
			    paginate: {
			        first:      "首页",
			        last:       "末页",
			        next:       "下一页",
			        previous:   "上一页"
			    },
			},
			responsive: true,//自适应
			searching:false,//关闭自动搜索
			autoWidth:true,//自适应宽度
			deferRender: true,//延迟加载
			select: true,//提供选择功能
			rocessing:true,
			jQueryUI: false,//会使得分页按钮显示不一样，不启用好看看些
			lengthChange : true,// 是否允许用户改变表格每页显示的记录数
			displayStart: 0,// 初始化显示的时候从第几条数据开始显示(一开始显示第几页)
			lengthMenu: [10,20,50,100,500],//定义每页显示数据数量
			info : true,// Showing 1 to 10 of 23 entries 控制是否显示表格左下角的信息
			dom: 'lfrtip',
			// scrollY: "62%",
			// scrollX: "210%",
			scrollCollapse : true,//当显示更少的记录时，是否允许表格减少高度
			pageLength: 10,//改变初始的页面长度(每页显示的记录数)
			pagingType: "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
			serverSide : true,
			destroy : true,
			orderCellsTop: true,
			//stripeClasses: ["odd", "even"],
			ordering: true,
			order: [],//关闭默认排序方式，阻止第一列排序
			rowId:"id",
			ajax : {
					url:urlPath,
					type:"POST",
					data:params //附加参数
				},
			columns :initColumns(),
			columnDefs: [ {
				className: "td-checkbox",//方便控制选择框
			    targets: 0,
			    orderable:false,//id列禁止排序功能
			    data: "id",
			    render: function ( data, type, full, meta ) {
			    	if(!rkUtil.rkStrUtil.isNull(selectType)){
			    		var tag="<input value='"+data+"' id='id_"+data+"' name='"+tableElement+"_name' ";
				    	if(selectType=="checkbox"){
				    		tag+="type='checkbox'/>"
				    	}else if(selectType=="radio"){
				    		tag+="type='radio'/>"
				    	}else{
				    		tag=data;
				    	}
			    	}else{
			    		tag="";
			    	}
			      return tag;
			    }
			  } ]
		});
		//tableApi=table.table();//获取api对象
		//绑定单选复选框
		if(!rkUtil.rkStrUtil.isNull(selectType)){
			tableSelectBind();
		}
		//绑定查询按钮
		if(!rkUtil.rkStrUtil.isNull(searchBtn)){
			tableSearchBtn(tableElement,urlPath,selectType,searchBtn);
		}
		return table;
	};
	var initColumns=function(){//从表头中取出相关属性项
		var columns=[];
		var tagPros=$table.find(" thead tr");
		tagPros.find("th").each(function(){
			var pro="";
			if(!rkUtil.rkStrUtil.isNull($(this).attr("dataField"))){
				if($(this).attr("dataField")==null||$(this).attr("dataField")==""){
					pro+="{data:"+$(this).attr("dataField");
				}else{
					pro+="{data:'"+$(this).attr("dataField")+"'";
				}
			}else{
				return;//如果这项没有后面不用添加了

			}
			if(!rkUtil.rkStrUtil.isNull($(this).attr("dataClass"))){
				if(pro==""){
					pro+="{className:'"+$(this).attr("dataClass")+"'";
				}else{
					pro+=",className:'"+$(this).attr("dataClass")+"'";
				}
			}
			if(!rkUtil.rkStrUtil.isNull($(this).attr("dataRender"))){
				var dataRender=$(this).attr("dataRender");
				if(pro==""){
					pro+="{render:"+dataRender;
				}else{
					pro+=",render:"+dataRender;
				}
			}
			if(pro==""){
				pro+="defaultContent:'-'";
			}else{
				pro+=",defaultContent:'-'";
			}
			pro+="}";
			columns.push(eval('(' + pro + ')'));
		});
		return columns;
	};
	//添加表格选择
	var tableSelectBind = function(selectType) { //selectType:选择类型，checkbox,多选；radio：单选
		$table.on("change", ":checkbox", function() {
			if ($(this).is("[id='all-checkbox']")) { //固定命名
				//全选
				$(":checkbox", $table).prop("checked", $(this).prop("checked"));
			} else {
				//一般复选
				var checkbox = $("tbody :checkbox", $table);
				$(":checkbox[id='all-checkbox']", $table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
			}
		});
		//点击单元格即点击复选框
		$table.on("click", ".td-checkbox", function(event) {
			!$(event.target).is(":checkbox") && $(":checkbox", this).trigger("click");
		});
		//点击行即点击复选框
		$table.on("click", "tr", function(event) {
			var $tr=$(this);
			$tr.find("td:eq(0)").each(function(){
				var $input=$(this).find("input");
				if(!$input.attr("checked")){
					$input.attr("checked","checked");
				}else{
					$input.removeAttr("checked");
				}
			});
		});
	};
	//单击行事件,返回行数据，行索引
	var clickRow=function(tableEleId,table,fnBack){
		$('#'+tableEleId+' tbody').on( 'click', 'tr', function () {
			var row=table.row( this );
			var index=row.index();
			var rowData=row.data();
			if(fnBack){
				fnBack(row,index,rowData);
			}
		});
	};
	//搜索
	var tableSearchBtn=function(tableElement,urlPath,selectType,searchBtn){
		var $searchBtn=$("#"+searchBtn);
		$searchBtn.on("click",function(e){
			var $searchForm=$(this).closest("form");
			var formJson=rkFormUtil.formToJson($searchForm);
			init(tableElement, urlPath, formJson, selectType);
		});
	};
	var reload=function(table){
		table.draw();
	};
	var getChecked=function(tableElement){
		var $tableEle=$("#"+tableElement);
		var ids=[];
		var $checkBox=$tableEle.find('td[class=" td-checkbox"]').each(function(i, element) {
			if($(this).find("input").prop("checked")){
				ids.push($(this).find("input").val());
			}
		});
		if(rkUtil.rkStrUtil.isNull(ids)||ids.length<1){
			rkAlert.errAlertMsg("请至少选择一条数据");
		}
		return ids;
	};
	var getCheckedData=function(table,tableElement){
		var ids=getChecked(tableElement);
		var datas=[];
		table.data().each(function(d){
			for(var i=0;i<ids.length;i++){
				if(d.id==ids[i]){
					datas.push(d);
					break;
				}
			}
		});
		return datas;
	};
	var tableRender={
			renderEnabled:function(data, type, row, meta){
				if(data=="1"){
					return "是";
				}else{
					return "否";
				}
			},
			renderYesNo:function(data, type, row, meta){
				if(data=="1"){
					return "是";
				}else{
					return "否";
				}
			},
			renderDic:function(value,row,meta,dicCode,dicCode1){
				if(rkUtil.rkStrUtil.isNull(value)){
					return "-";
				}
				rkStore.rkDic.getDic(dicCode,function(data){
					var dicArr=data;
					if(rkUtil.rkStrUtil.isNull(dicArr)){
						return "-";
					}
					for(var i=0;i<dicArr.length;i++){
						if(value==dicArr[i].code){
							$("#"+row.id).find("td").eq(meta.col).html(dicArr[i].name);
							break;
						}
					}
				},dicCode1);
			},
			renderByUrl:function(row,meta,url,param){//根据url渲染表格
				rkAjax.postAjaxNoErr(url,param,function(data){
					$("#"+row.id).find("td").eq(meta.col).html(data.name);;
				});
			}
			
		};
	
	rkTableObj={
			init:init,
			getChecked:getChecked,
			clickRow:clickRow,
			tableSearchBtn:tableSearchBtn,
			reload:reload,
			getCheckedData:getCheckedData,
			tableRender:tableRender
		};
	return rkTableObj;
});