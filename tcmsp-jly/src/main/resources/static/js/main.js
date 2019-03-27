
//三级菜单开关变量
var itemStatus =0;
(function () {
	"use strict";

	var userId = localStorage.getItem("key1");
	var userName = localStorage.getItem("key2");
	var headImg = localStorage.getItem("key3");
	$(".app-sidebar__user-name").html(userName);
	$(".app-sidebar__user-designation").html(userId);
	
	var treeviewMenu = $('.app-menu');

	// Toggle Sidebar
	$('[data-toggle="sidebar"]').click(function(event) {
		event.preventDefault();
		$('.app').toggleClass('sidenav-toggled');
	});

	// Activate sidebar treeview toggle
	$("[data-toggle='treeview']").click(function(event) {
		event.preventDefault();
		if(!$(this).parent().hasClass('is-expanded')) {
			treeviewMenu.find("[data-toggle='treeview']").parent().removeClass('is-expanded');
		}
		$(this).parent().toggleClass('is-expanded');
	});

	// Set initial active toggle
	$("[data-toggle='treeview.'].is-expanded").parent().toggleClass('is-expanded');

	//Activate bootstrip tooltips
	$("[data-toggle='tooltip']").tooltip();

})();

/*  将当前所在url高亮 */
$(function(){
   var urlStr = location.href;
    $("#menu li a").each(function () {
    var currentUrl = $(this).attr('href');
    if(urlStr.indexOf(currentUrl)>-1 && currentUrl!="#" &&currentUrl !=""){
        //获取到当前的对象的Class属性
        var currentClass = $(this).attr('class');
        if(currentClass == "treeview-item"){
         var CurrentGigMenu= $(this).parent().parent().parent().parent();
         $(CurrentGigMenu).addClass('cur');
		 $(this).addClass('cur');
        }
        else{
          $(this).addClass('cur');
        }
    }
  })
  //三级菜单
  $("#treeview-menu-select").click(function(){
	   itemStatus = ! itemStatus;
	   if(itemStatus){
	     $("#treeview-item-select").show();
	     $("#icon").replaceWith("<i class='iconfont icon-xia' id='icon'></i>");
	    }else{
	      $("#treeview-item-select").hide();
	     $("#icon").replaceWith("<i class='iconfont icon-you' id='icon'></i>");
	    }	
	  });
})

