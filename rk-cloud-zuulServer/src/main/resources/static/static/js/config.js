/**
 * 文件加载入口类
 */
var libPath="/static/lib/";
var rkjsPath="/static/js/rkjs/";
var viewPath="/views/";
require.config({
    baseUrl: '',
    map: {
        '*': {
            'css': libPath+'require/requireCss.js',
        }
    },
    paths: {
    	jquery: libPath+'jquery/jquery-3.2.0.min',
        jqmigrate: libPath+'jquery/jquery-migrate-3.0.0.min',
        jqueryCookie:libPath+'jquery-cookie/jquery.cookie',
        jqueryValid:libPath+'jquery-validation/jquery.validate.min',
        jqueryValidCh:libPath+'jquery-validation/localization/messages_zh.min',
        bootstrap: libPath+'bootstrap/js/bootstrap.min',
        responsive:libPath+'responsivenav/responsive-nav',
        perfectScrollbar:libPath+'perfect-scrollbar/perfect-scrollbar.min',
        switchery:libPath+'switchery/switchery.min',
        modernizr:libPath+'modernizr/modernizr',
        ChartJs:libPath+'Chart.js/Chart.min',
        jquerySparkline:libPath+'jquery.sparkline/jquery.sparkline.min',
        sweetalert:libPath+'sweetalert/sweetalert.min',
        amazeUi:libPath+'amazeUI/assets/js/amazeui.min',
        dataTables:libPath+'DataTables/jquery.dataTables.min',
        dataTablesBT:libPath+'DataTables/extensions/Buttons/js/dataTables.buttons.min',
        dataTablesBTHtml5:libPath+'DataTables/extensions/Buttons/js/buttons.html5.min',
        dataTablesBTColVis:libPath+'DataTables/extensions/Buttons/js/buttons.colVis.min',
        dataTablesBootstrap:libPath+'DataTables/dataTables.bootstrap.min',
        dataTablesBootstrapBT:libPath+'DataTables/extensions/Buttons/js/buttons.bootstrap.min',
        layui:libPath+'layui/layui',
        layer:libPath+'layui/lay/modules/layer',
        laypage:libPath+'layui/lay/modules/laypage',
        ztree:libPath+'zTree_v3/js/jquery.ztree.all.min',
        bSelect:libPath+'bootstrap-select/js/bootstrap-select.min',
        bSelectCh:libPath+'bootstrap-select/js/i18n/defaults-zh_CN.min',
        ueditor:libPath+'ueditor/ueditor.all',
        ueditorConfig:libPath+'ueditor/ueditor.config',
        neditorConfig:libPath+'ueditor/neditor.config',
        ueditorCh:libPath+'ueditor/lang/zh-cn/zh-cn',
        ZeroClipboard:libPath+'ueditor/third-party/zeroclipboard/ZeroClipboard',
        bInputFile:libPath+'bootstrap-fileinput/js/fileinput.min',
        bInputFileCh:libPath+'bootstrap-fileinput/js/locales/zh',
        masonry:libPath+'masonry/masonry.min',
        imagesloaded:libPath+'masonry/imagesloaded'
    },
    shim: {
        bootstrap: {
            deps: ['jquery','jqueryCookie','css!'+libPath+'/bootstrap/css/bootstrap.min.css','css!'+libPath+'/fontawesome/css/font-awesome.min.css','css!'+libPath+'/animate.css/animate.min.css',
                   'css!'+libPath+'/themify-icons/themify-icons.min.css']
        },
        responsive:{
        	deps:['bootstrap','css!'+libPath+'/responsivenav/responsive-nav.css']
        },
        perfectScrollbar:{
        	deps:['bootstrap','css!'+libPath+'/perfect-scrollbar/perfect-scrollbar.min.css']
        },
        switchery:{
        	deps:['bootstrap','css!'+libPath+'/switchery/switchery.min.css']
        },
        sweetalert:{
        	deps:['css!'+libPath+'/sweetalert/sweetalert.css']
        },
        amazeUi:{deps:['jquery','css!'+libPath+'/amazeUI/assets/css/amazeui.min.css']},
        //dataTables:{deps:['jquery','css!'+libPath+'/DataTables/css/jquery.dataTables.min.css']},
        dataTablesBootstrap:{
        	deps:['bootstrap','css!'+libPath+'/DataTables/css/dataTables.bootstrap.min.css']
        },
        dataTablesBootstrapBT:{deps:['bootstrap','css!'+libPath+'/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css']},
        layui:{deps:['jquery','css!'+libPath+'/layui/css/layui.css']},
        layer:{deps:['jquery','css!'+libPath+'/layui/css/modules/layer/default/layer.css']},
        laypage:{deps:['jquery','css!'+libPath+'/layui/css/modules/layer/default/layer.css']},
        ztree:{deps:['jquery','css!'+libPath+'/zTree_v3/css/metroStyle/metroStyle.css']},
        bSelect:{deps:['bootstrap','css!'+libPath+'/bootstrap-select/css/bootstrap-select.min.css']},
        bSelectCh:{deps:['bSelect']},
        ueditorCh:{deps:['ueditorConfig','ueditor']},
        bInputFileCh:{deps:['bInputFile','css!'+libPath+'/bootstrap-fileinput/css/fileinput.min']},
        masonry:{deps:['imagesloaded']}
    },
    waitSeconds:0
});
