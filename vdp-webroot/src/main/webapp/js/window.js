/**********弹出窗口***********/

//打开info窗口
function openinfowindow(title, content, enturCallback){
	var warncontent =  "<div class='infowin' title=\""+content+"\">"+content+"</div>";
	openwindowcenter(title,500,200,warncontent,enturCallback);
}
//打开ok窗口
function openconfirmwindow(title, content, enturCallback){
	var warncontent =  "<div class='confirmwin' title=\""+content+"\">"+content+"</div>";
	openwindowcenter(title,500,200,warncontent,enturCallback);
}
//打开warn窗口
function openwarnwindow(title, content, enturCallback){
	var warncontent =  "<div class='warningwin' title=\""+content+"\">"+content+"</div>";
	openwindowcenter(title,500,200,warncontent,enturCallback);
}
//打开question窗口
function openquestionwindow(title, content, enturCallback){
	var warncontent =  "<div class='questionwin' title=\""+content+"\">"+content+"</div>";
	openwindowcenter(title,500,200,warncontent,enturCallback);
}


//打开窗口模板
/*
title:窗口标题
width:窗口宽度
height:窗口高度
content:窗口内容
enturCallback:回调方法
cancelCallback:关闭窗口和取消回调方法
*/
window.windowindex = 9000;//弹窗index

function openwindow(title, width, height, content, enturCallback, cancelCallback) {

var windowOpenTimes = new Date().getTime();//保证窗口唯一

var windowcontent = "<div class='openwindowback' id='openwindowback"+windowOpenTimes+"' style='z-index:" + window.windowindex +"'></div>";
	windowcontent += "<div class='openwindow' id='openwindow"+windowOpenTimes+"' style='z-index:" + (window.windowindex+1) +";width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
	windowcontent += "<div class='openwindowtitle' id='openwindowtitle"+windowOpenTimes+"'>";
	windowcontent += "<div class='openwindowtitlename'>" + title + "</div>";
	windowcontent += "<div class='openwindowtitleclose' id='openwindowtitleclose"+windowOpenTimes+"'></div>";
	windowcontent += "</div>";
	windowcontent += "<div class='openwindowcontent'>";
	windowcontent += content;
	windowcontent += "</div>";
	windowcontent += "<div class='openwindowbtns'>";
	windowcontent += "<div class='cancelbtn' id='cancelbtn"+windowOpenTimes+"' style='float:right;margin-top:20px;margin-right:30px;'>取消</div>";
	windowcontent += "<div class='enturebtn' id='enturebtn"+windowOpenTimes+"' style='float:right;margin-top:20px;margin-right:10px;'>确定</div>";
	windowcontent += "</div>";
	windowcontent += "</div>";
	$("body").append(windowcontent);
	window.windowindex++;

	//
	$(".openwindowtitleclose").hover(function() {
		$(this).addClass("openwindowtitleclosehover");
	}, function() {
		$(this).removeClass("openwindowtitleclosehover");
	});

	// 关闭按钮单击
	$("#openwindowtitleclose"+windowOpenTimes).click(function() {
		if(cancelCallback && typeof cancelCallback=='function'){
			cancelCallback();
//			closeWindow();
		}else if(cancelCallback && typeof cancelCallback=='string'){
			try{
				var func=eval(cancelCallback);
				new func();
//				closeWindow();
			}catch(err){
				console.log(err)
			}
		}else{
			closeWindow();
		}
	});

	$("#enturebtn"+windowOpenTimes).bind("click", function(){
		if(enturCallback && typeof enturCallback=='function'){
			enturCallback();
//			closeWindow();
		}else if(enturCallback && typeof enturCallback=='string'){
			try{
				var func=eval(enturCallback);
				new func();
//				closeWindow();
			}catch(err){
				console.log(err)
			}
		}else{
			closeWindow();
		}
	});

	$("#cancelbtn"+windowOpenTimes).bind("click", function(){
		if(cancelCallback && typeof cancelCallback=='function'){
			cancelCallback();
//			closeWindow();
		}else if(cancelCallback && typeof cancelCallback=='string'){
			try{
				var func=eval(cancelCallback);
				new func();
//				closeWindow();
			}catch(err){
				console.log(err)
			}
		}else{
			closeWindow();
		}
	});

	var winWidth = $(window).width();
	var winHeight = $(window).height();

	var _move=false;//移动标记
    var _x,_y;//鼠标离控件左上角的相对位置
    $("#openwindowtitle"+windowOpenTimes).click(function(){
        //console.log("click");
        }).mousedown(function(e){
        _move=true;
        _x=e.pageX-parseInt($("#openwindow"+windowOpenTimes).css("left"));
        _y=e.pageY-parseInt($("#openwindow"+windowOpenTimes).css("top"));
        $("#openwindow"+windowOpenTimes).fadeTo(20, 0.8);//点击后开始拖动并透明显示
		$(this).css("cursor","move");
    });

    $(document).mousemove(function(e){
        if(_move){
            var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置
            var y=e.pageY-_y;

			if( x <( width / 2)){//窗口靠左不出边
				x =( width / 2);
			}
			if( x > (winWidth-width / 2)){//窗口靠右不出边
			   x =(winWidth-width / 2);
			}
			if(y <( height / 2)){//窗口靠上不出边
			  y =( height / 2)
			}
			if( y > (winHeight-height / 2)){//窗口靠下不出边
			   y =(winHeight-height / 2);
			}

            $("#openwindow"+windowOpenTimes).css({top:y,left:x});//控件新位置
        }
    }).mouseup(function(){
       _move=false;
      $("#openwindow"+windowOpenTimes).fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明
    });


}


function openwindowcenter(title, width, height, content, enturCallback) {

var windowOpenTimes = new Date().getTime();//保证窗口唯

var windowcontent = "<div class='openwindowback' id='openwindowback"+windowOpenTimes+"' style='z-index:" + window.windowindex +"'></div>";
	windowcontent += "<div class='openwindow' id='openwindow"+windowOpenTimes+"' style='z-index:" + (window.windowindex+1) +";width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
	windowcontent += "<div class='openwindowtitle' id='openwindowtitle"+windowOpenTimes+"'>";
	windowcontent += "<div class='openwindowtitlename'>" + title + "</div>";
	windowcontent += "<div class='openwindowtitleclose'></div>";
	windowcontent += "</div>";
	windowcontent += "<div class='openwindowcontentcenter'>";
	windowcontent += content;
	windowcontent += "</div>";
	windowcontent += "<div class='openwindowbtnscenter'>";
	windowcontent += "<div class='cancelbtn' id='cancelbtn"+windowOpenTimes+"' style='float:right;margin-top:20px;'>取消</div>";
	windowcontent += "<div class='enturebtn' id='enturebtn"+windowOpenTimes+"' style='float:right;margin-top:20px;margin-right:10px;'>确定</div>";
	windowcontent += "</div>";
	windowcontent += "</div>";
	$("body").append(windowcontent);
	window.windowindex++;
	//
	$(".openwindowtitleclose").hover(function() {
		$(this).addClass("openwindowtitleclosehover");
	}, function() {
		$(this).removeClass("openwindowtitleclosehover");
	});

	// 关闭按钮单击
	$(".openwindowtitleclose").click(function() {
		closeWindow();
	});




	$(".enturebtn").bind("click", function(){
		if(enturCallback && typeof enturCallback=='function'){
			enturCallback();
//			closeWindow();
		}else if(enturCallback && typeof enturCallback=='string'){
			try{
				var func=eval(enturCallback);
				new func();
//				closeWindow();
			}catch(err){
				console.log(err)
			}
		}else{
			closeWindow();
		}
	});

	$(".cancelbtn").bind("click", function(){
		closeWindow();
	});



	var winWidth = $(window).width();
	var winHeight = $(window).height();

	var _move=false;//移动标记
    var _x,_y;//鼠标离控件左上角的相对位置
    $("#openwindowtitle"+windowOpenTimes).click(function(){
        //console.log("click");
        }).mousedown(function(e){
        _move=true;
        _x=e.pageX-parseInt($("#openwindow"+windowOpenTimes).css("left"));
        _y=e.pageY-parseInt($("#openwindow"+windowOpenTimes).css("top"));
        $("#openwindow"+windowOpenTimes).fadeTo(20, 0.8);//点击后开始拖动并透明显示
		$(this).css("cursor","move");
    });

    $(document).mousemove(function(e){
        if(_move){
            var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置
            var y=e.pageY-_y;

			if( x <( width / 2)){//窗口靠左不出边
				x =( width / 2);
			}
			if( x > (winWidth-width / 2)){//窗口靠右不出边
			   x =(winWidth-width / 2);
			}
			if(y <( height / 2)){//窗口靠上不出边
			  y =( height / 2)
			}
			if( y > (winHeight-height / 2)){//窗口靠下不出边
			   y =(winHeight-height / 2);
			}

            $("#openwindow"+windowOpenTimes).css({top:y,left:x});//控件新位置
        }
    }).mouseup(function(){
       _move=false;
      $("#openwindow"+windowOpenTimes).fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明
    });
}


//打开只有标题的窗口模板
/*
title:窗口标题
width:窗口宽度
height:窗口高度
content:窗口内容
cancelCallback:回调方法
*/
function opentitlewindow(title, width, height, content, cancelCallback){

var windowOpenTimes = new Date().getTime();//保证窗口唯

var windowcontent = "<div class='openwindowback' id='openwindowback"+windowOpenTimes+"' style='z-index:" + window.windowindex +"'></div>";
	windowcontent += "<div class='openwindow' id='openwindow"+windowOpenTimes+"' style='z-index:" + (window.windowindex+1) +";width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
	windowcontent += "<div class='openwindowtitle' id='openwindowtitle"+windowOpenTimes+"'>";
	windowcontent += "<div class='openwindowtitlename'>" + title + "</div>";
	windowcontent += "<div class='openwindowtitleclose'></div>";
	windowcontent += "</div>";
	windowcontent += "<div class='openwindowcontentnobottom'>";
	windowcontent += content;
	windowcontent += "</div>";

	windowcontent += "</div>";

	$("body").append(windowcontent);
	window.windowindex++;
	//
	$(".openwindowtitleclose").hover(function() {
		$(this).addClass("openwindowtitleclosehover");
	}, function() {
		$(this).removeClass("openwindowtitleclosehover");
	});

	// 关闭按钮单击
	$(".openwindowtitleclose").click(function() {
		if(cancelCallback && typeof cancelCallback=='function'){
			cancelCallback();
//			closeWindow();
		}else if(cancelCallback && typeof cancelCallback=='string'){
			try{
				var func=eval(cancelCallback);
				new func();
//				closeWindow();
			}catch(err){
				console.log(err)
				closeWindow();
			}
		}else{
			closeWindow();
		}
	});


	var winWidth = $(window).width();
	var winHeight = $(window).height();

	var _move=false;//移动标记
    var _x,_y;//鼠标离控件左上角的相对位置
    $("#openwindowtitle"+windowOpenTimes).click(function(){
        //console.log("click");
        }).mousedown(function(e){
        _move=true;
        _x=e.pageX-parseInt($("#openwindow"+windowOpenTimes).css("left"));
        _y=e.pageY-parseInt($("#openwindow"+windowOpenTimes).css("top"));
        $("#openwindow"+windowOpenTimes).fadeTo(20, 0.8);//点击后开始拖动并透明显示
		$(this).css("cursor","move");
    });
	// 关闭按钮单击

    $(document).mousemove(function(e){
        if(_move){
            var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置
            var y=e.pageY-_y;

			if( x <( width / 2)){//窗口靠左不出边
				x =( width / 2);
			}
			if( x > (winWidth-width / 2)){//窗口靠右不出边
			   x =(winWidth-width / 2);
			}
			if(y <( height / 2)){//窗口靠上不出边
			  y =( height / 2)
			}
			if( y > (winHeight-height / 2)){//窗口靠下不出边
			   y =(winHeight-height / 2);
			}

            $("#openwindow"+windowOpenTimes).css({top:y,left:x});//控件新位置
        }
    }).mouseup(function(){
       _move=false;
      $("#openwindow"+windowOpenTimes).fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明
    });

}



//打开窗口模板
function openblankwindow(width,height,content) {

var windowOpenTimes = new Date().getTime();//保证窗口唯

	var windowcontent = "<div class='openwindowback'></div>";
	windowcontent += "<div class='openwindow' style='width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
	windowcontent += "<div class='openwindowcontent'>";
	windowcontent += content;
	windowcontent += "</div>";
	windowcontent += "<div class='openwindowbtns' style='border:0px'>";
	windowcontent += "<div class='cancelbtn' id='cancelbtn"+windowOpenTimes+"' style='margin-left:auto;margin-right:auto;margin-top:20px;'>取消</div>";

	windowcontent += "</div>";
	windowcontent += "</div>";
	$("body").append(windowcontent);
	window.windowindex++;

	// 取消按钮
	$(".cancelbtn").hover(function() {
		$(this).addClass("btnon");
	}, function() {
		$(this).removeClass("btnon");
	});

	$(".cancelbtn").bind("click", function(){
		closeWindow();
	});

	var winWidth = $(window).width();
	var winHeight = $(window).height();

	var _move=false;//移动标记
    var _x,_y;//鼠标离控件左上角的相对位置
    $("#openwindowtitle"+windowOpenTimes).click(function(){
        //console.log("click");
        }).mousedown(function(e){
        _move=true;
        _x=e.pageX-parseInt($("#openwindow"+windowOpenTimes).css("left"));
        _y=e.pageY-parseInt($("#openwindow"+windowOpenTimes).css("top"));
        $("#openwindow"+windowOpenTimes).fadeTo(20, 0.8);//点击后开始拖动并透明显示
		$(this).css("cursor","move");
    });

    $(document).mousemove(function(e){
        if(_move){
            var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置
            var y=e.pageY-_y;

			if( x <( width / 2)){//窗口靠左不出边
				x =( width / 2);
			}
			if( x > (winWidth-width / 2)){//窗口靠右不出边
			   x =(winWidth-width / 2);
			}
			if(y <( height / 2)){//窗口靠上不出边
			  y =( height / 2)
			}
			if( y > (winHeight-height / 2)){//窗口靠下不出边
			   y =(winHeight-height / 2);
			}

            $("#openwindow"+windowOpenTimes).css({top:y,left:x});//控件新位置
        }
    }).mouseup(function(){
       _move=false;
      $("#openwindow"+windowOpenTimes).fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明
    });

}
//打开loading窗口
function loadingwindow(rootPath, width, height, content) {
	var windowcontent = "<div class='openloadingwindowback'></div>";
	windowcontent += "<div class='openloadingwindow' style='width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
	windowcontent += "<img class='loadingimg' src='"+rootPath+"/skin/default/images/loading.gif'/>";
    windowcontent += "<div class='loadingword'>";
	windowcontent += content;
	windowcontent += "</div>";
	windowcontent += "</div>";
	$("body").append(windowcontent);
}

function closeWindow(){
	$(".openwindowback").last().find("*").unbind();
	$(".openwindowback").last().html("");
	$(".openwindowback").last().remove();
	$(".openwindow").last().find("*").unbind();
	$(".openwindow").last().html("");
	$(".openwindow").last().remove();
}

function closeLodingWindow(){
	$(".openloadingwindowback").last().find("*").unbind();
	$(".openloadingwindowback").last().html("");
	$(".openloadingwindowback").last().remove();
	$(".openloadingwindow").last().find("*").unbind();
	$(".openloadingwindow").last().html("");
	$(".openloadingwindow").last().remove();
}


/**********提示框***********/
//success
function successtip(title){
	showtip(title, width=465, height=50, "successtip");
}
//info
function infotip(title){
	showtip(title,width=465,height=50,"infotip");
}
//error
function errortip(title){
	showtip(title,width=465,height=50,"errortip");
}
// 提示条
function showtip(title, width, height, type) {
	var downspeed = 1000;
	var backspeed = 1000;
	var delaytime = 2000;

	if($(window).scrollTop() > 0){

	    var tipcontent = "<div class='tipbox " + type + "' style='bottom:-100px;width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
		tipcontent += "<div class='tipicon'></div>";
		tipcontent += "<div class='tipwords'>" + title + "</div>";
		tipcontent += "</div>";

	    $("body").append(tipcontent);

		$(".tipbox").animate({
		    bottom : "100px",
		}, downspeed).delay(delaytime).animate({
			bottom : "-100px"
		}, backspeed, function() {
			$(".tipbox").remove();
		});

	}else{

		var tipcontent = "<div class='tipbox " + type + "' style='top:-100px;width:" + width + "px;height:" + height + "px;margin-left:" + (-width / 2) + "px;margin-top:" + (-height / 2) + "px;'>";
		tipcontent += "<div class='tipicon'></div>";
		tipcontent += "<div class='tipwords'>" + title + "</div>";
		tipcontent += "</div>";

	    $("body").append(tipcontent);

		$(".tipbox").animate({
		    top : "100px"
		}, downspeed).delay(delaytime).animate({
			top : "-100px"
		}, backspeed, function() {
			$(".tipbox").remove();
		});
	}

}
// 提示条
