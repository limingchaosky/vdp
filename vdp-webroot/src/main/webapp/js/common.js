
/**
 * 判断字符串是否为空
 */
String.prototype.isEmpty = function () {
    return this.trim() === '';
}
/**
 * 字符串格式化
 */
String.prototype.format = function () {
    if (arguments.length == 0) return this;
    for (var str = this, i = 0; i < arguments.length; i++)
        str = str.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
    return str;
};
/**
 * 取数组最后一个元素
 */
Array.prototype.last = function () {
    return this[this.length - 1];
}

/**
 * 获取url传参
 */
function query(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var param = window.location.search.substr(1).match(reg);
    if (param !== null) {
        return unescape(param[2]);
    } else {
        return '';
    }
}
/**
 * 日期格式化
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
/**
 * 表单序列化为JSON
 */
$.fn.serializeJSON = function () {
    var serializeObj = {};
    var array = this.serializeArray();
    var str = this.serialize();
    $(array).each(function () {
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        } else {
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};
/**
 * 数组与对象的深复制
 *
 */
function deepCopy(obj) {
    if (typeof obj == 'object') {
        return JSON.parse(JSON.stringify(obj));
    }
    else {
        return obj
    };
}
/**
 * checkbox美化
 */
function beaytyCheckbox(selector, callback) {
    $('body').off('click', selector).on('click', selector, function () {
        var target = $(this).find('input[type="checkbox"]').not('.disabled');
        if (target.length > 0) {
            $(target).prop('checked', !$(target).prop('checked')).removeClass('half');
            /**回调，方便更改选中状态后做一些事情 */
            if (callback) {
                callback($(this));
            }
        }
    })
}
/**
 * 通用Get Ajax
 */
function getAjax(url, data, callback) {
    $.ajax({
        type: 'get',
        url: url,
        data: data,
        success: function (response, status, xhr) {
            callback(response, status, xhr);
        },
        error: function (xhr, errorText, errorStatus) {
            callback({
                resultCode: 0,
                resultMsg: errorText
            }, errorStatus, xhr);
        }
    })
}
/**
 * 通用Post Ajax
 */
function postAjax(url, data, callback, isDebug) {
    $.ajax({
        type: 'post',
        url: url,
        data: data,
        success: function (response, status, xhr) {
            callback(response, status, xhr);
        },
        error: function (xhr, errorText, errorStatus) {
            callback({
                resultCode: 0,
                resultMsg: errorText
            }, errorStatus, xhr);
        }
    })
}