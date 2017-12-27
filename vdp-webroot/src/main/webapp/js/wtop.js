$(function () {


    var selectbluebtn = $(".selectbluebtn");

    selectbluebtn.hover(function () {
        $(this).addClass("bluebtnon");

        $(this).find(".btnselector").show();

    }, function () {

        $(this).removeClass("bluebtnon");

        $(this).find(".btnselector").hide();
    });
    $('.j-top-edit-user').click(editUserInfo);
    getPendingMsg();
    setInterval(function () {
        getPendingMsg();
    }, 1000 * 30);
});

function editUserInfo() {
    layer.open({
        title: '编辑用户信息',
        id: 'userinfo',
        type: 1,
        content: $('#userinfo-html').html(),
        btn: ['确定', '取消'],
        area: ['540px', '570px'],
        yes: function (index) {
            if ($('#userinfo form').valid()) {
                var postData = $('#userinfo form').serializeJSON();
                if (postData.newpwd && !postData.pwd) {
                    layer.msg('若要修改密码，请输入现有密码！', { icon: 0 });
                    return;
                }
                if (postData.pwd) {
                    postData.pwd = encrypt(postData.pwd).toUpperCase();
                    postData.newpwd = encrypt(postData.newpwd).toUpperCase();
                }
                postData.id=$('#top_user_name').attr('data-id');
                delete postData.newpwd_x;
                delete postData.roleTypeName;
                $.ajax({
                    type: 'post',
                    url: ctx + '/user/updateUser',
                    data: postData,
                    success: function (msg) {
                        if (msg == 'NoExistId') {
                            layer.msg('用户ID不存在！', { icon: 0 });
                        } else if (msg == 'OldPwdError') {
                            layer.msg('现有密码不正确！', { icon: 0 });
                        }
                        else if (msg == 'success') {
                            layer.msg('修改成功', { icon: 1 });
                            layer.close(index);
                        } else {
                            layer.msg('修改失败', { icon: 2 });
                        }
                    },
                    error: function () {
                        layer.msg('修改失败', { icon: 2 });
                    }
                })
            }
        },
        success: function () {
            if ($('#top_user_name').text().trim() != 'system') {
                $('#userinfo .u-row-first').removeClass('none');
            } else {
                $('#userinfo .j-label-role').text('真实姓名：');
            }
            $.ajax({
                type: 'get',
                url: ctx + '/user/getUser?id=' + $('#top_user_name').attr('data-id'),
                success: function (msg) {
                    $('#userinfo input[name=id]').val(msg.id);
                    $('#userinfo input[name=userName]').val(msg.userName);
                    $('#userinfo input[name=newpwd]').attr('id', 'userinfo-password');
                    if ($('#top_user_name').text().trim() != 'system') {
                        $('#userinfo input[name=roleTypeName]').val(msg.roleTypeName || '');
                    } else {
                        $('#userinfo input[name=roleTypeName]').val(msg.first || '');
                    }
                    $('#userinfo input[name=first]').val(msg.first);
                    $('#userinfo input[name=phone]').val(msg.phone == '--' ? '' : msg.phone);
                    $('#userinfo input[name=email]').val(msg.email == '--' ? '' : msg.email);
                    $('#userinfo form').validate({
                        rules: {
                            pwd: {
                                twentyfourChar: true,
                                minSixChar: true
                            },
                            newpwd: {
                                twentyfourChar: true,
                                minSixChar: true
                            },
                            newpwd_x: {
                                equalTo: '#userinfo-password',
                            },
                            first: {
                                specialCharNew: true,
                                twentyfourChar: true,
                                required: true
                            },
                            phone: {
                                checkPhoneAndNumber: true,
                            },
                            email: {
                                specialCharNew: true,
                                twentyfourChar: true,
                                email: true
                            }
                        },
                        messages: {
                            newpwd_x: {
                                equalTo: "两次密码不一致"
                            }
                        },
                    });
                },
                error: function (e) {
                    layer.msg('获取用户信息失败', { icon: 2 });
                }
            })
        }
    });
}

function getPendingMsg() {
    $.ajax({
        type: 'get',
        url: ctx + '/workOrder/pending/unRead',
        success: function (msg) {
            if (msg > 0) {
                $('#top_msg').text(msg).css('display', 'inline-block');
            } else {
                $('#top_msg').text('').css('display', 'none');
            }
        }
    })
}