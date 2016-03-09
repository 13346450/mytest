$(function(){	
	
	$(".Div1_main div span").mouseover(function(){
		$(this).addClass("Div1_main_span1").siblings("span").removeClass("Div1_main_span1");
	}).mouseout(function(){
		$(this).removeClass("Div1_main_span1").siblings("span");
	})
	
	
	var 
		 index = 0 ;
		Swidth = 1125 ;
		 timer = null ;
		   len = $(".Div1_title span a").length ; 
		
		function NextPage()
		{	
			if(index>1)
			{
				index = 0 ;
			}
			$(".Div1_main").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		function PrevPage()
		{	
			if(index<0)
			{
				index = 1 ;
			}
			$(".Div1_main").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		$(".Div1_title span a").each(function(a){
            $(this).mouseover(function(){
				index = a ;
				NextPage();
				
			});
        });
		//next
		$(".Div1_next").click(function(){
			 index++ ;
			 NextPage();
			
		});
		//prev
		$(".Div1_prev").click(function(){
			 index-- ;
			 PrevPage();
		});
		
		var timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);
			
		$(".Div1_next, .Div1_main , .Div1_prev, .Div1_title span").mouseover(function(){
			clearInterval(timer);
		});
		$(".Div1_next, .Div1_main , .Div1_prev, .Div1_title span").mouseleave(function(){
			timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);	
		});
			
})

//manage
$(function(){	
	 $(".manage2-tb").click(function(){
	       $(".manage2-tb").removeClass("onmanage2-tb");
	       var $aobj=$(this);
	       $aobj.addClass("onmanage2-tb");
	    });
	var 
		 index = 0 ;
		Swidth = 880 ;
		 timer = null ;
		
		function NextPage()
		{	
			if(index>1)
			{
				index = 0 ;
			}
			$(".Div1_manage").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		function PrevPage()
		{	
			if(index<0)
			{
				index = 1 ;
			}
			$(".Div1_manage").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		//next
		$(".Div1_next01").click(function(){
			 index++ ;
			 NextPage();
			
		});
		//prev
		$(".Div1_prev01").click(function(){
			 index-- ;
			 PrevPage();
		});
		
		var timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);
			
		$(".Div1_next01, .Div1_manage , .Div1_prev01, .Div1_title span").mouseover(function(){
			clearInterval(timer);
		});
		$(".Div1_next01, .Div1_manage , .Div1_prev01, .Div1_title span").mouseleave(function(){
			timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);	
		});
			
})//

//home
$(function(){	
	$(".manage3-tb").click(function(){
	       $(".manage3-tb").removeClass("onmanage3-tb");
	       var $aobj=$(this);
	       $aobj.addClass("onmanage3-tb");
	    });
	var 
		 index = 0 ;
		Swidth = 875 ;
		 timer = null ;
		
		function NextPage()
		{	
			if(index>2)
			{
				index = 0 ;
			}
			$(".Div1_home").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		function PrevPage()
		{	
			if(index<0)
			{
				index = 1 ;
			}
			$(".Div1_home").stop(true, false).animate({left: -index*Swidth+"px"},600)		
		}
		
		//next
		$(".Div1_next02").click(function(){
			 index++ ;
			 NextPage();
			
		});
		//prev
		$(".Div1_prev02").click(function(){
			 index-- ;
			 PrevPage();
		});
		
		var timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);
			
		$(".Div1_next02, .Div1_home , .Div1_prev02, .Div1_title span").mouseover(function(){
			clearInterval(timer);
		});
		$(".Div1_next02, .Div1_home , .Div1_prev02, .Div1_title span").mouseleave(function(){
			timer = setInterval(function(){
				index++ ;
				NextPage();
			},4000);	
		});
			
})//

//business
$(function(){	
var _index1=0;
$('.bannerBut ul li').mouseover(function(){
	$(this).addClass('hover').siblings('li').removeClass('hover');
	var _index1=$(this).index();
	$('.bannerCon .scroll').stop().animate({left:-_index1*918},500);
});

//next
function NextPage()
		{	
			if(_index1>3)
			{
				_index1 = 0 ;
			}
			$(".scroll").stop().animate({left:-_index1*918},500);
			$('.bannerBut ul li').eq(_index1).addClass('hover').siblings('li').removeClass('hover');		
		}



var timer = setInterval(function(){
				_index1++;
				NextPage();
			},4000);
			
		$(".bannerBut ").mouseover(function(){
			clearInterval(timer);
		});
		$(".bannerBut ").mouseleave(function(){
			timer = setInterval(function(){
				_index1++;
				NextPage();
			},4000);	
		});
})