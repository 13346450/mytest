// JavaScript Document

var my_section1= {
    init : function(){
        $(".cod_rom img").each(function(){
            var left = 400*(Math.random()-0.5);
            var top = 100*(Math.random()-0.5);
            $(this).css({ 'transform': 'translate3d('+left+'px,'+top+'px,0)'}).removeClass('animSect1')
        })
    },
    anim : function(){
        $(".cod_rom img").each(function(i){
            var _this = $(this);
            var _i = i+(0.1*i*i)
            setTimeout(function(){
               _this.addClass("animSect1");
            },_i*50+100)
        })
    }
}
var my_section2= {
    init : function(){
        $(".shouhuanImg img").css({ 'transform': 'translate3d('+200+'px,'+0+'px,0)'}).removeClass('animSect2')
    },
    anim : function(){

        setTimeout(function(){
              $(".shouhuanImg img").addClass('animSect2');
         },100)

    }
}

//header
function topshow(){
    $("#nav li").click(function(){
	       $("#nav li").find("a").removeClass("active");
	       var $aobj=$(this).find("a");
	       $aobj.addClass("active");
	    });

	 var box = document.getElementById("box");
	$(".loginbtn1").click(function(){
		$('.box').show();
		$(".close").show();
		$("#logincont").show();
		$("#logincont3").hide();
		});		
	$(".close span").click(function(){
		$('.box').hide();
		$(".close").hide();
		$("#logincont").hide();
		$("#logincont2").hide();
		$("#logincont3").hide();
		});
	$(".loginbtn2").click(function(){
		$('.box').show();
		$(".close").show();
		$("#logincont2").show();
		$("#logincont3").hide();
		});	
	$(".loginbtn3").click(function(){
		$("#logincont2").hide();
		$("#logincont3").hide();
		$(".close").show();
		$("#logincont").show();
		});
	$(".loginbtn4").click(function(){
		$("#logincont").hide();
		$("#logincont2").hide();
		$(".close").show();
		$("#logincont3").show();
		});		
	$(".loginbtn5").click(function(){
		$("#logincont").hide();
		$("#logincont3").hide();
		$(".close").show();
		$("#logincont2").show();
		});
	$(".closeboy").click(function(){
		$(this).parent().parent().parent(".boybg").hide();
		});
		//manage
	$("#repairs").click(function(){
		$(".close").hide();
		$('.box-manage').show();
		$("#logincont-manage").show();
		});
	$("#manage-reset").click(function(){
		$(".close").hide();
		$('.box-manage').hide();
		$("#logincont-manage").hide();
		});	
	$(".complain").click(function(){
		$(".close").hide();
		$('.box-complain').show();
		});
	$("#complain-reset").click(function(){
		$(".close").hide();
		$('.box-complain').hide();
		});	
    $("#lgstyle div").click(function(){
	       $("#lgstyle div").removeClass("lgactive");
	        $(this).addClass("lgactive");
	    });
	$(".delete-pic").click(function(){
	        $(this).parent(".goodstop-item").hide();
	    });	
	//property
	$(".property-draftsbtn").click(function(){
		$(".close").show();
		$('.box-manage').show();
		$("#logincont-manage").show();
		});
	$(".close").click(function(){
		$(".close").hide();
		$('.box-manage').hide();
		$("#logincont-manage").hide();
		})	
		//cart
	$("#cart-check").click(function(){
	       $(".cart-ico-input").css("background-position","0");
	      
	    });	
	$("#more-add").click(function(){
		$("#more-addshow").show();
		});
	$(".pay-border li").click(function(){
	        $(".pay-border li").removeClass("on-paytype");
	        $(this).addClass("on-paytype");
			$(".pay-border li em").removeClass("cart-ico-inputon");
	        $(this).children("em").addClass("cart-ico-inputon");
			$(".pay-money").css("display","none");
	        $(this).children(".pay-money").css("display","block");
	    });
		
	$("#comminuty-name").click(function(){
		$("#areashow").toggle();
		});
	/*personal*/
	$(".history-listitempic").click(function(){
	$(this).parent(".history-listitem").hide();
	});
		
          var topleft2 = document.getElementById("top-left2");
		  var codeshow = document.getElementById("codeshow");
          topleft2.onmouseover = function(){
            codeshow.style.display = "block";
            };
			codeshow.onmouseover = function(){
            codeshow.style.display = "block";
            };
          topleft2.onmouseout = function(){
            codeshow.style.display = "none";
            };
			 codeshow.onmouseout = function(){
            codeshow.style.display = "none";
            };
           
          /**var topleft1 = document.getElementById("top-left1");
		  var areashow = document.getElementById("areashow");
          topleft1.onmouseover = function(){
            areashow.style.display = "block";
            };
			areashow.onmouseover = function(){
            areashow.style.display = "block";
            };
          topleft1.onmouseout = function(){
            areashow.style.display = "none";
            };
			 areashow.onmouseout = function(){
            areashow.style.display = "none";
            };*/
			
           }
	      
//cart03-04
/* function myFunction() {
        y = document.getElementById("cart-nextbtn1");
        y.innerHTML = "商品评价";
	       $("#cart03").hide();
		   $("#cart04").show();
		   $(".pay-border li em").removeClass("cart-ico-inputon");
		   $("#cart0304-blue1").css("color","#000");
		   $("#cart0304-blue2").css("color","#00a0e9");
		   $("#cart0304-gray").show();
		   $("#cart0304-pic").css("background-position","287px -32px");
	    
    }*/
//about-us-height
function $equal(id){ 
	return document.getElementById(id)
} 
function getHeight() { 
	if ($equal("about-rt").offsetHeight>=$equal("about-lt").offsetHeight){
		$equal("about-lt").style.height=$equal("about-rt").offsetHeight + "px";
	}
	else{
		$equal("about-rt").style.height=$equal("about-lt").offsetHeight + "px";
	}	
}

//cart
