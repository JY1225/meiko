/**
 * 
 */
var LANGUAGE_Index = "zh_CN"; //标识语言  
  
/*jQuery(document).ready(function () { 	
	//initLan()
	//loadProperties(LANGUAGE_Index); 
	//LANGUAGE_Index = jQuery.i18n.normaliseLanguageCode({}); //获取浏览器的语言 
	//alert(LANGUAGE_Index);
	loadProperties(LANGUAGE_Index);
});  */
  
function initLan(){
    jQuery.i18n.properties({// 加载properties文件  
    	name: 'strings_init_lan', // 资源文件名称  
    	path: '../plugins/Languages/', // 资源文件所在目录路径  
    	mode: 'map', // 模式：变量或 Map  
    callback: function() { 
    	LANGUAGE_Index = $.i18n.prop("init_lan")
    }  
    });  
}  
  
$(".lan_select").change(function () {  
  
  
    if (($(".lan_select").val() === "英文") || ($(".lan_select").val() === "English")) {  
        LANGUAGE_Index = "en_US";  
  } else {  
        LANGUAGE_Index = "zh_CN";  
  }  
  
    loadProperties(LANGUAGE_Index);  
  
});  
  
  
function loadProperties(type) {  
    jQuery.i18n.properties({  
        name: 'strings', // 资源文件名称  
        path: '../plugins/Languages/', // 资源文件所在目录路径  
        mode: 'map', // 模式：变量或 Map  
        language: type, // 对应的语言  
        cache: false,  
        encoding: 'UTF-8',  
        callback: function () { // 回调方法  
            $('.lan_zh').html($.i18n.prop('lan_zh'));
            $('.lan_en').html($.i18n.prop('lan_en'));            
            $('.username').html($.i18n.prop('username'));  
            $('.password').html($.i18n.prop('password'));
            $('.language').html($.i18n.prop('language'));  
            $('.login_title').html($.i18n.prop('login_title'));
            $('.remember_me').html($.i18n.prop('remember_me'));
            $('.login').html($.i18n.prop('login'));
            $('.update_password').html($.i18n.prop('update_password'));
            $('.logout').html($.i18n.prop('logout'));
            $('.bulletin_board').html($.i18n.prop('bulletin_board'));
            $('.welcome_system').html($.i18n.prop('welcome_system'));
            $('.online').html($.i18n.prop('online'));
            $('.menu').html($.i18n.prop('menu'));
            $('.home_page').html($.i18n.prop('home_page'));
            $('.report_system').html($.i18n.prop('report_system'));
            $('.report_list').html($.i18n.prop('report_list'));
            $('.system_manage').html($.i18n.prop('system_manage'));
            $('.user_manage').html($.i18n.prop('user_manage'));
            $('.role_manage').html($.i18n.prop('role_manage'));
            $('.login_log').html($.i18n.prop('login_log'));
            $('.download_log').html($.i18n.prop('download_log'));
            $('.source_url').html($.i18n.prop('source_url'));
            $('.list').html($.i18n.prop('list'));
            $('.batch_download').html($.i18n.prop('batch_download'));
        }  
    });  
}