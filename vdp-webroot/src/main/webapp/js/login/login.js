// JavaScript Document
//支持Enter键登录
document.onkeydown = function (e) {
    if ($(".bac").length == 0) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            $('#submit_btn').click();
        }
    }
}
$(function () {
    $('#name').focus();
    //提交表单
    $('#submit_btn').click(function () {
        var name = $("#name").val();
        name = $.trim(name);
        var password = $("#password").val();
        password = $.trim(password);
        if (name == '' || password == '') {
            $("#errorMessage").html("用户名或密码不正确")
            return;
        }
        $("#password").val(encrypt(password).toUpperCase());
        $("#loginForm").submit();
    });
    //更换验证码
    $('#img_code').click(function () {
        $(this).attr('src', $(this).attr('data-src') + '?' + Math.random());
    });
});
