function splitComma(str) {
    var result = str.split(",");
    return result;
}

function _compare_ip(begin, end) {
    var temp1 = begin.split(".")
    var temp2 = end.split(".")

    for (var j = 0; j < 4; j++) {
        if (parseInt(temp1[j]) > parseInt(temp2[j])) {
            return 1;
        } else if (parseInt(temp1[j]) < parseInt(temp2[j])) {
            return -1;
        }
    }

    return 0;

}

function valid_ip(ip_range) {
    var re = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    var ips = ip_range.split("-")
    if (ips.length == 1) {
        if (re.test(ips[0])) {
            return true
        }
    } else if (ips.length == 2) {
        if (re.test(ips[0]) && re.test(ips[1])) {
            if (_compare_ip(ips[0], ips[1]) <= 0) {
                return true;
            }
        }
    }

    return false;
}

function checkIp(nameip) {
    var val = nameip;
    var array = val.split(";")
    for (var i = 0; i < array.length; i++) {
        if (!valid_ip(array[i])) {
            return false
        }
    }
    return true
}

//下拉框
$(document).on("mouseover", '.table-caozuo-box', function () {
    var fixedFull = $(this).closest(".dataTables_scrollBody").height();
    var fixedY = $(this).offset().top - $(this).closest(".dataTables_scrollBody").offset().top;
    var conheight = $(this).find(".table-caozuo-con").height();
    var outconheight = -4 - conheight;
    if (fixedY + conheight + 50 > fixedFull) {
        $(this).find(".table-caozuo-con").css({
            'top': outconheight,
            'left': '-85px'
        }).removeClass('triangle-top').addClass('triangle-bottom');
    } else {
        $(this).find(".table-caozuo-con").css({
            'top': "35px",
            'left': '-85px'
        }).removeClass('triangle-bottom').addClass('triangle-top');
    }
    $(this).find(".table-caozuo-con").show();
}).on("mouseout", '.table-caozuo-box', function () {
    $(this).find(".table-caozuo-con,.arrow-div").hide();
});


//登陆超时重定向
$.ajaxSetup({
    complete: function (xhr, textStatus) {
        if (xhr.getResponseHeader('isRedirect') == 'yes') {
            location.href = "/drs/login";
        }
    }
});

//获取cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    }
    else {
        return null;
    }
}
//删除cookie
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) {
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString() + ';path=/';
    }
}

var csrftoken_ = getCookie("XSRF-TOKEN");
var csrfHeader_ = getCookie("XSRF-HEADER-NAME");

if (csrftoken_ != null && csrfHeader_ != null) {
    $.ajaxSetup({
        beforeSend: function (xhr) {
            if (csrfHeader_ && csrftoken_) {
                xhr.setRequestHeader(csrfHeader_, csrftoken_);
            }
        }
    }
    );
}

//layer配置
$(function () {
    layer.config({
        success: function (layero, index) {
            $(layero).find('.layui-layer-ico:not(.layui-layer-close)').css({
                'background': 'none',
                'height': 'auto',
                'width': 'auto',
                'left': '46px',
                'top': '15px'
            }).closest('.layui-layer-dialog').css({
                'min-width': '270px',
                'border': 'none',
                'box-shadow': '0 0 5px 1px lightgrey',
                'color': '#1E8FA5'
            }).find('.layui-layer-content').css({
                'padding': '13px 70px'
            });

            //提示
            $(layero).find('.layui-layer-ico0').addClass('icon-tanhao-circle').addClass('icon-tanhao-circle').closest('.layui-layer-dialog').css({
                'background-color': '#E1EBF9',
                'color': '#347AD5'
            });
            //成功
            $(layero).find('.layui-layer-ico1').addClass('icon-duihao-circle').addClass('icon-duihao-circle').closest('.layui-layer-dialog').css({
                'background-color': '#ABE7ED',
                'color': '#1E8FA5'
            });
            //失败
            $(layero).find('.layui-layer-ico2').addClass('icon-cuohao-circle').addClass('icon-cuohao-circle').closest('.layui-layer-dialog').css({
                'background-color': '#FFCDD1',
                'color': '#E83C4B'
            });

        }
    });
});

$(document)
    //弹窗input回车点确定
    .on('keydown', '.layui-layer-page input:not([enter])', function (e) {
        if (e.keyCode == 13) {
            $(this).closest('.layui-layer-page').find('.layui-layer-btn0').click();
            return false;
        }
    })
    //工单描述点击小图显示大图
    .on('click', '.detail-order-description img[title],.history-row img[title]', function (e) {
        var src = $(this).attr('src');
        var image = new Image();
        image.src = src;
        image.onload = function () {
            var imgWidth = image.width;
            var imgHeight = image.height;
            var scaleX = 800 / imgWidth;
            var scaleY = 600 / imgHeight;
            var scale = Math.min(scaleX, scaleY);
            if (scale > 1) {
                scale = 1;
            }
            layer.open({
                type: 1,
                title: null,
                shade: 0,
                content: '<img src="' + src + '" style="width:100%;height:100%;display:block";/>',
                area: [imgWidth * scale + 'px', imgHeight * scale + 'px'],
            });
        };
    });
