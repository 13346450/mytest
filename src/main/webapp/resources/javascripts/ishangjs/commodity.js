// JavaScript Document
$(document).ready(function(){
  $("#p1").mouseenter(function(){
    alert("您的鼠标移到了 id=p1 的元素上!");
  });
       //more-parameter
       $("#more-parameter").click(function(){
	       $('.more-para').show();
		});	
		//btn
		$("#record01").click(function(){
	       $('#commodity-first').show();
		   $('#commodity-second').hide();
		   $('#commodity-third').hide();
		   $('#commodity-fourth').hide();
		});
		$("#record02").click(function(){
	       $('#commodity-first').hide();
		   $('#commodity-second').show();
		   $('#commodity-third').hide();
		   $('#commodity-fourth').hide();
		});
		$("#record03").click(function(){
	       $('#commodity-first').hide();
		   $('#commodity-second').hide();
		   $('#commodity-third').show();
		   $('#commodity-fourth').hide();
		});
		$("#record04").click(function(){
	       $('#commodity-first').hide();
		   $('#commodity-second').hide();
		   $('#commodity-third').hide();
		   $('#commodity-fourth').show();
		});
		$("#all-introduce").click(function(){
	       $('.all-introduce').show();
		   $('.good').hide();
		   $('.common').hide();
		   $('.bad').hide();
		});
		$("#good").click(function(){
	       $('.good').show();
		   $('.all-introduce').hide();
		   $('.common').hide();
		   $('.bad').hide();
		});
		$("#common").click(function(){
	       $('.common').show();
		   $('.good').hide();
		   $('.all-introduce').hide();
		   $('.bad').hide();
		});
		$("#bad").click(function(){
	       $('.bad').show();
		   $('.good').hide();
		   $('.common').hide();
		   $('.all-introduce').hide();
		});
		//top-pic
		$(".list-goods li").mouseenter(function(){
		   $(".list-goods li").removeClass("on-goods");
	        $(this).addClass("on-goods");
		});	
		$("#goods01").mouseenter(function(){
	       $('.pic-big').css("background-image","url(../resources/images/ishangimg/goods-item.jpg)");
		});	
		$("#goods02").mouseenter(function(){
	       $('.pic-big').css("background-image","url(../resources/images/ishangimg/goods02.jpg)");
		});	
		$("#goods03").mouseenter(function(){
	       $('.pic-big').css("background-image","url(../resources/images/ishangimg/goods-item.jpg)");
		});	
		$("#goods04").mouseenter(function(){
	       $('.pic-big').css("background-image","url(../resources/images/ishangimg/goods02.jpg)");
		});	
		//item-pic大小
		$(".item-pic li").click(function(){
		   $(".item-pic li").removeClass("onitem-pic");
	        $(this).addClass("onitem-pic");
		});
		$(".viewphoto01").click(function(){
		   $('.item-picbig').css("display","block");
		    $('.item-picbig').css("background-image","url(../resources/images/ishangimg/goods-item.jpg)");
		});
		$(".viewphoto02").click(function(){
		   $('.item-picbig').css("display","block");
		    $('.item-picbig').css("background-image","url(../resources/images/ishangimg/goods02.jpg)");
		});
		$(".item-picbig").click(function(){
		   $('.item-picbig').css("display","none");
		   $(".item-pic li").removeClass("onitem-pic");
		});
		//data
		$("#record div").click(function(){
	       $("#record div").removeClass("on-data");
	        $(this).addClass("on-data");
		});	
		$("#introduce div").click(function(){
	       $("#introduce div").removeClass("on-data");
	        $(this).addClass("on-data");
		});
	});	