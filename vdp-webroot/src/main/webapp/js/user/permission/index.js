var permissionUserTable = null;//角色用户表
var tabPermissionTree = null;//页签权限树
$(function () {
    initTabPermissionTree();//初始化页签权限树
    getPermissionList('.left-list:first');//获取权限列表
    initEvents();//初始化页面事件
    validatePermission();//角色修改数据验
});

function initEvents() {
    $('body')
        //修改角色名称
        .on('click', '.list-edit', function () {
            var self = $(this);
            layer.open({
                id: 'openWind',
                type: 1,
                title: '编辑角色名称',
                content: $('#add_role_wind').html(),
                area: ['440px', '200px'],
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    if (!$("#openWind form").valid()) {
                        return;
                    }
                    var postData = {
                        id: $(self).attr('data-id'),
                        name: $('#openWind input[name=name]').val().trim(),
                        nav: ''
                    }
                    var flag = false;
                    $('.list-name').each(function (index, obj) {
                        if ($(obj).text() == postData.name && $(obj).closest('li').attr('data-id') != postData.id) {
                            flag = true;
                            layer.msg('角色名已存在！', { icon: 0 });
                            return false;
                        }
                    });
                    if (flag) {
                        return;
                    }
                    var permissionTreeNodes = tabPermissionTree.getCheckedNodes(true);
                    //获取选中节点的值
                    for (var i = 0; i < permissionTreeNodes.length; i++) {
                        if (i == permissionTreeNodes.length - 1) {
                            postData.nav += permissionTreeNodes[i].id;
                        } else {
                            postData.nav += permissionTreeNodes[i].id + ",";
                        }
                    }
                    if($(layero).find('.layui-layer-btn0').hasClass('btn-disabled')){
                        return;
                    }
                    $(layero).find('.layui-layer-btn0').addClass('btn-disabled');
                    $.ajax({
                        type: 'post',
                        url: ctx + '/permission/addOrUpdatePermission',
                        data: postData,
                        success: function (msg) {
                            if (msg === 'success') {
                                layer.close(index);
                                $(self).closest('li').find('.list-name').text(postData.name);
                                if ($(self).closest('li').hasClass('list-active')) {
                                    $('#permission_title').text(postData.name);
                                }
                                layer.msg('编辑成功！', { icon: 1 });
                            } else {
                                $(layero).find('.layui-layer-btn0').removeClass('btn-disabled');
                                layer.msg('编辑失败！', { icon: 2 });
                            }
                        },
                        error: function () {
                            $(layero).find('.layui-layer-btn0').removeClass('btn-disabled');
                            layer.msg('编辑失败！', { icon: 2 });
                        }
                    })
                },
                success: function (layero, index) {
                    $('#openWind input[name=name]').val($(self).closest('li').find('.list-name').text()).focus();
                    //校验
                    $('#openWind form').validate({
                        rules: {
                            name: {
                                required: true,
                                specialChar: true,
                                twentyChar: true
                            }
                        }
                    });
                }
            });
            return false;
        })
        //修改页签权限
        .on('click', '#save_tabpermission', function () {
            var postData = {
                id: $('.list-active').attr('data-id'),
                name: $('.list-active .list-name').text(),
                nav: []
            }
            var permissionTreeNodes = tabPermissionTree.getCheckedNodes(true);
            for (var i = 0; i < permissionTreeNodes.length; i++) {
                if (i == permissionTreeNodes.length - 1) {
                    postData.nav += permissionTreeNodes[i].id;
                } else {
                    postData.nav += permissionTreeNodes[i].id + ",";
                }
            }

            $.ajax({
                type: 'post',
                url: ctx + '/permission/addOrUpdatePermission',
                data: postData,
                success: function (msg) {
                    if (msg === 'success') {
                        $('.list-active').find('.list-name').text(postData.name);
                        layer.msg('保存成功！', { icon: 1 });
                    } else {
                        layer.msg('保存失败！', { icon: 2 });
                    }
                },
                error: function () {
                    layer.msg('保存失败！', { icon: 2 });
                }
            })
        })
        //添加用户
        .on('click', '.j-add-user', function () {
            if ($('.list-active').length == 0) {
                return;
            }
            layer.open({
                id: 'openWind',
                type: 1,
                title: '添加用户',
                content: $('#add_user_wind').html(),
                area: ['680px', '620px'],
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var postData = {};
                    postData.flag = 3;//1新增，2修改
                    postData.id = $('.list-active').attr('data-id');
                    postData.userId = [];
                    $('#openWind .j-wind-check-user').each(function (index, obj) {
                        if ($(obj).prop('checked')) {
                            postData.userId.push($(obj).attr('data-id'));
                        }
                    });
                    if (postData.userId.length == 0) {
                        layer.msg('请选择用户！', { icon: 0 });
                        return;
                    }
                    postData.userId = postData.userId.join(',');
                    postData[userKey] = token;
                    $.ajax({
                        type: 'post',
                        url: ctx + '/user/addOrUpdateUser',
                        data: postData,
                        success: function (msg) {
                            if (msg === 'success') {
                                permissionUserTable.ajax.reload(function () { }, true);
                                layer.close(index);
                                layer.msg('添加成功！', { icon: 1 });
                            } else {
                                layer.msg('用户名已存在！', { icon: 0 });
                            }
                        },
                        error: function () {
                            layer.msg('添加失败！', { icon: 2 });
                        }
                    })
                },
                success: function (layero, index) {
                    initUserListTable();
                    var html = '<div class="wind-top-search-box">';
                    html += '<input type="text" class="wind-top-search-input" placeholder="用户名">';
                    html += '<i class="icon-search wind-top-search-icon"></i>';
                    html += '</div>';
                    $(layero).append(html);
                }
            });
        })
        //删除用户
        .on('click', '.j-del-user,.del-user-all', function () {
            if ($('.list-active').length == 0) {
                return;
            }
            var userID = '';
            var infoStr = '';
            if ($(this).hasClass('del-user-all')) {
                if ($('.j-check-user:checked').length == 0) {
                    layer.msg('请先选中用户！', { icon: 0 });
                    return;
                }
                $('.j-check-user:checked').each(function (index, obj) {
                    if (index == 0) {
                        userID += $(obj).attr('data-id');
                    } else {
                        userID += ',';
                        userID += $(obj).attr('data-id');
                    }
                });
                infoStr = '确定要删除选中的用户吗？';
            }
            else {
                userID = $(this).attr('data-id');
                infoStr = '确定要删除该用户吗？';
            }
            layer.confirm(infoStr, {
                btn: ['确定', '取消']
            }, function () {
                var postData = {
                    id: userID,
                    flag: 3,
                    roleType: -1
                }
                postData[userKey] = token;
                $.ajax({
                    type: 'post',
                    url: ctx + '/user/addOrUpdateUser',
                    data: postData,
                    success: function (msg) {
                        if (msg === 'success') {
                            permissionUserTable.ajax.reload(function () { }, true);
                            layer.msg('删除成功！', { icon: 1 });
                            $('.j-check-user-all').prop('checked', false);
                        } else {
                            layer.msg('删除失败！', { icon: 2 });
                        }
                    },
                    error: function () {
                        layer.msg('删除失败！', { icon: 2 });
                    }
                })
            });
        })
        //添加角色
        .on('click', '.j-add-role', function () {
            layer.open({
                id: 'openWind',
                type: 1,
                title: '添加角色',
                content: $('#add_role_wind').html(),
                area: ['440px', '200px'],
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    if (!$("#openWind .j-add-role-form").valid()) {
                        return;
                    }
                    var postData = {
                        name: $('#openWind input[name=name]').val(),
                        flag: 1
                    }
                    var flag = false;
                    $('.list-name').each(function (index, obj) {
                        if ($(obj).text() == postData.name) {
                            flag = true;
                            layer.msg('角色名已存在！', { icon: 0 });
                            return false;
                        }
                    });
                    if (flag) {
                        return;
                    }
                    if($(layero).find('.layui-layer-btn0').hasClass('btn-disabled')){
                        return;
                    }
                    $(layero).find('.layui-layer-btn0').addClass('btn-disabled');
                    $.ajax({
                        type: 'post',
                        url: ctx + '/permission/addOrUpdatePermission',
                        data: postData,
                        success: function (msg) {
                            if (msg === 'success') {
                                getPermissionList('.left-list:last');
                                layer.close(index);
                                layer.msg('添加成功！', { icon: 1 });
                            } else {
                                $(layero).find('.layui-layer-btn0').removeClass('btn-disabled');
                                layer.msg('添加失败！', { icon: 2 });
                            }
                        },
                        error: function () {
                            $(layero).find('.layui-layer-btn0').removeClass('btn-disabled');
                            layer.msg('添加失败！', { icon: 2 });
                        }
                    })
                },
                success: function (layero, index) {
                    //校验
                    $('#openWind .j-add-role-form').validate({
                        rules: {
                            name: {
                                required: true,
                                specialChar: true,
                                twentyChar: true
                            }
                        }
                    });
                }
            });
        })
        //删除角色
        .on('click', '.list-del', function () {
            var self = $(this);
            layer.confirm('确定要删除该角色吗？', {
                btn: ['确定', '取消']
            }, function () {
                var postData = {
                    id: $(self).attr('data-id'),
                }
                postData[userKey] = token;
                $.ajax({
                    type: 'post',
                    url: ctx + '/permission/deletePermission',
                    data: postData,
                    success: function (msg) {
                        if (msg === 'success') {
                            $(self).closest('.left-list').remove();
                            if ($('.left-list').length == 0) {
                                $('#permission_title').text('');
                                $('#left_content_box').addClass('empty');
                                $('#permissionUserTable').DataTable({ "destroy": true }).clear().draw();
                                $('.tabperimssion-tree-box').addClass('none');
                            } else {
                                $('.left-list:first').click();
                            }
                            layer.msg('删除成功！', { icon: 1 });
                        } else {
                            layer.msg('删除失败！', { icon: 2 });
                        }
                    },
                    error: function () {
                        layer.msg('删除失败！', { icon: 2 });
                    }
                })
            });
            return false;
        })
        //权限列表点击切换
        .on('click', '.left-list', function () {
            if (!$(this).hasClass('list-active')) {
                $('.list-active').removeClass('list-active');
                $(this).addClass('list-active');
                $('#permission_title').text($(this).find('span').text());
                getPermissionInfo($(this).attr('data-id'));
                initPermissionUserTable($(this).attr('data-id'));
                $('.j-check-user-all').prop('checked', false);
                if ($(this).attr('data-id') == '1') {
                    $('.del-user-all').css('display', 'none');
                } else {
                    $('.del-user-all').css('display', 'inline-block');
                }
            }
        })
        //页签切换
        .on('click', '.tab', function () {
            $('.tab-active').removeClass('tab-active');
            $(this).addClass('tab-active');
            $('.tab-content').addClass('hide');
            $('.tab-content:eq(' + $(this).attr('tab-idx') + ')').removeClass('hide');
            if ($(this).hasClass('j-user-tab')) {
                $('.j-user-opt').removeClass('none');
            }
            else {
                $('.j-user-opt').addClass('none');
            }
        })
        //角色用户全选或取消全选
        .on('change', '.j-check-user-all', function () {
            $('.j-check-user').prop('checked', $(this).prop('checked'));
        })
        //角色用户单个选择或取消选择
        .on('change', '.j-check-user', function () {
            $('.j-check-user-all').prop('checked', $('.j-check-user').not(':checked').length == 0);
        })
        //用户列表全选或取消全选
        .on('change', '#openWind .j-wind-check-user-all', function () {
            $('#openWind .j-wind-check-user').prop('checked', $(this).prop('checked'));
        })
        //用户列表单个选择或取消选择
        .on('change', '#openWind .j-wind-check-user', function () {
            $('#openWind .j-wind-check-user-all').prop('checked', $('#openWind .j-wind-check-user').not(':checked').length == 0);
        })
        //用户列表关键词搜索
        .on('keydown', '.layui-layer .wind-top-search-input', function (e) {
            if (e.keyCode == 13) {
                userListTable.settings()[0].ajax.data.searchstr = $(this).val().trim();
                userListTable.ajax.reload();
            }

        })
        //用户列表关键词搜索
        .on('click', '.layui-layer .wind-top-search-icon', function (e) {
            userListTable.settings()[0].ajax.data.searchstr = $('.layui-layer .wind-top-search-input').val().trim();
            userListTable.ajax.reload();
        })
}
/**
 * 获取权限列表
 *
 */
function getPermissionList(el) {
    //el为要选中的元素选择器
    $.ajax({
        type: 'get',
        url: ctx + '/permission/getPermissionList',
        success: function (msg) {
            if (msg.length > 0) {
                $('#left_content_box').removeClass('empty');
                $('#permission_list_box').html(template('temp_permission_list_box', msg));
                $('.tabperimssion-tree-box').removeClass('none');
                $(el).click();
            }
            else {
                $('#left_content_box').addClass('empty');
                $('#permissionUserTable').DataTable({ "destroy": true }).clear().draw();
                $('.tabperimssion-tree-box').addClass('none');
            }
        },
        error: function () {
            layer.msg('获取权限列表失败！', { icon: 2 });
        }
    })
}
/**
 * 获取权限信息
 *
 */
function getPermissionInfo(id) {
    $.ajax({
        type: 'get',
        url: ctx + '/permission/getPermission?id=' + id,
        success: function (msg) {
            $('#form_permission_info input[name=name]').val(msg.name);
            $('#form_permission_info input[name=remark]').val(msg.remark);
            //选中角色下的页签权限
            tabPermissionTree.checkAllNodes(false);
            for (var i = 0; i < msg.list.length; i++) {
                var node = tabPermissionTree.getNodeByParam("id", msg.list[i].navigationId, null);
                if (msg.list[i].isParent == 'y' && msg.list[i].selectType == 'half') {
                    //对于半选的不做操作
                } else if (msg.list[i].isParent == 'y' && msg.list[i].selectType == 'all') {
                    tabPermissionTree.checkNode(node, true, true);
                    tabPermissionTree.updateNode(node);
                } else {
                    tabPermissionTree.checkNode(node, true, true);
                    tabPermissionTree.updateNode(node);
                }
            }
        },
        error: function () {
            layer.msg('获取权限信息失败！', { icon: 2 });
        }
    })
}
function validatePermission() {
    //校验角色修改数据
    $('#form_permission_info').validate({
        rules: {
            name: {
                required: true,
                twentyChar: true,
                specialChar: true,
            },
            remark: {
                specialChar: true,
                required: true,
                fiftyChar: true,
            },
        },
    });
}
/**
 * 初始化页签权限树
 *
 */
function initTabPermissionTree() {
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            showIcon: false
        },
        check: {
            enable: true,
            nocheckInherit: false,
            autoCheckTrigger: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    //页签权限树
    tabPermissionTree = $.fn.zTree.init($("#tabpermission_tree"), setting, zNodes);
}
/**
 * 获取角色下的成员
 *
 */
function initPermissionUserTable(id) {
    $('#permissionUserTable').DataTable().destroy();
    permissionUserTable = $('#permissionUserTable').DataTable({ //表格初始化
        "searching": true,//关闭Datatables的搜索功能:
        "destroy": true,//摧毁一个已经存在的Datatables，然后创建一个新的
        "retrieve": true, //检索已存在的Datatables实例,如果已经初始化了，则继续使用之前的Datatables实例
        "autoWidth": true,//自动计算列宽
        "processing": false,//是否显示正在处理的状态
        "stateSave": false, //开启或者禁用状态储存。当你开启了状态储存，Datatables会存储一个状态到浏览器上， 包含分页位置，每页显示的长度，过滤后的结果和排序。当用户重新刷新页面，表格的状态将会被设置为之前的设置。
        "serverSide": true,//服务器端处理模式——此模式下如：过滤、分页、排序的处理都放在服务器端进行。
        "scrollY": "auto",//控制表格的垂直滚动。
        /*l - Length changing 改变每页显示多少条数据的控件
        f - Filtering input 即时搜索框控件
        t - The Table 表格本身
        i - Information 表格相关信息控件
        p - Pagination 分页控件
        r - pRocessing 加载等待显示信息*/
        "dom": 'rlfrtip',
        "stateLoadParams": function (settings, data) { //状态加载完成之后，对数据处理的回调函数
        },
        "lengthMenu": [
            [20, 30, 50, 100],
            ["20条", "30条", "50条", "100条"]
        ],//定义在每页显示记录数的select中显示的选项
        "ajax": {
            "beforeSend": function () {
            },
            "url": ctx + "/user/datalist1",
            //改变从服务器返回的数据给Datatable
            "dataSrc": function (json) {
                return json.data;
            },
            //将额外的参数添加到请求或修改需要被提交的数据对象
            "data": {
                "value": id,
                "searchstr": '',
                "type": 'permission'
            },
        },
        "columnDefs": [{
            "targets": [0],
            "orderable": false,
            "width": "35px",
            "visible": id != 1,
            "render": function (data, type, full) {
                return '<input type="checkbox" class="j-check-user" data-id="' + data + '">';
            }
        }, {
            "targets": [1],
            "orderable": false,
            "class": "text-ellipsis",
        }, {
            "targets": [2],
            "orderable": false,
            "class": "text-ellipsis"
        }, {
            "targets": [3],
            "orderable": false,
            "class": "text-ellipsis  displayNone"
        }, {
            "targets": [4],
            "orderable": false,
            "class": "text-ellipsis"
        }, {
            "targets": [5],
            "orderable": false,
            "width": "180px",
            "class": "text-ellipsis displayNone"
        }, {
            "targets": [6],
            "orderable": false,
            "width": "180px",
            "class": "text-ellipsis"
        }, {
            "targets": [7],
            "orderable": false,
            "width": "80px",
            "class": "center-text",
            "visible": id != 1,
            "render": function (data, type, full) {
                return '<div class="table-opt-box">' +
                    '<i class="icon-shanchu table-opt-icon j-del-user" data-id="' + data + '"></i>' +
                    ' </div>'
            }
        }],
        //当每次表格重绘的时候触发一个操作，比如更新数据后或者创建新的元素
        "drawCallback": function (oTable) {
            var oTable = $("#permissionUserTable").dataTable();
            //设置每一列的title
            $("table").find("tr td:not(:last-child)").each(function (index, obj) {
                $(obj).attr("title", $(obj).text());
            })
            //添加跳转到指定页
            $(".dataTables_paginate").append("<span style='margin-left: 10px;font-size: 14px;'>到第 </span><input style='height:20px;line-height:28px;width:28px;margin-top: 5px;' class='margin text-center' id='changePage' type='text'> <span style='font-size: 14px;'>页</span>  <a class='shiny' href='javascript:void(0);' id='dataTable-btn'>确认</a>");
            $('#dataTable-btn').click(function (e) {
                if ($("#changePage").val() && $("#changePage").val() > 0) {
                    var redirectpage = $("#changePage").val() - 1;
                } else {
                    var redirectpage = 0;
                }
                oTable.fnPageChange(redirectpage);
            });

            //键盘事件  回车键 跳页
            $("#changePage").keydown(function () {
                var e = event || window.event;
                if (e && e.keyCode == 13) {
                    if ($("#changePage").val() && $("#changePage").val() > 0) {
                        var redirectpage = $("#changePage").val() - 1;
                    } else {
                        var redirectpage = 0;
                    }
                    oTable.fnPageChange(redirectpage);
                }
            })
        }
    }).on('xhr.dt', function (e, settings, json, xhr) {
        //登陆超时重定向
        if (xhr.getResponseHeader('isRedirect') == 'yes') {
            location.href = "/oms/login";
        }
    });
}

/**
 * 获取用户列表
 *
 */
function initUserListTable() {
    userListTable = $('#openWind .j-user-list-table').DataTable({ //表格初始化
        "searching": true,//关闭Datatables的搜索功能:
        "destroy": true,//摧毁一个已经存在的Datatables，然后创建一个新的
        "retrieve": true, //检索已存在的Datatables实例,如果已经初始化了，则继续使用之前的Datatables实例
        "autoWidth": true,//自动计算列宽
        "processing": false,//是否显示正在处理的状态
        "stateSave": false, //开启或者禁用状态储存。当你开启了状态储存，Datatables会存储一个状态到浏览器上， 包含分页位置，每页显示的长度，过滤后的结果和排序。当用户重新刷新页面，表格的状态将会被设置为之前的设置。
        "serverSide": true,//服务器端处理模式——此模式下如：过滤、分页、排序的处理都放在服务器端进行。
        "scrollY": "auto",//控制表格的垂直滚动。
        /*l - Length changing 改变每页显示多少条数据的控件
        f - Filtering input 即时搜索框控件
        t - The Table 表格本身
        i - Information 表格相关信息控件
        p - Pagination 分页控件
        r - pRocessing 加载等待显示信息*/
        "dom": 'rfrt',
        "stateLoadParams": function (settings, data) { //状态加载完成之后，对数据处理的回调函数
        },
        // "paging": false,//禁用分页
        "ajax": {
            "beforeSend": function () {
            },
            "url": ctx + "/user/datalist1",
            //改变从服务器返回的数据给Datatable
            "dataSrc": function (json) {
                var temp = [];
                $.each(json.data, function (index, obj) {
                    temp.push([obj[0], obj[0], obj[2], obj[4]]);

                });
                return temp;
            },
            //将额外的参数添加到请求或修改需要被提交的数据对象
            "data": {
                "length": '1000000',
                "searchstr": ""
            },
        },
        "columnDefs": [{
            "targets": [0],
            "orderable": false,
            "width": "35px",
            "render": function (data, type, full) {
                return '<input type="checkbox" class="j-wind-check-user" data-id="' + data + '">';
            }
        }, {
            "targets": [1],
            "orderable": false,
            "class": "text-ellipsis",
        }, {
            "targets": [2],
            "orderable": false,
            "class": "text-ellipsis"
        }, {
            "targets": [3],
            "orderable": false,
            "class": "text-ellipsis"
        }],
        //当每次表格重绘的时候触发一个操作，比如更新数据后或者创建新的元素
        "drawCallback": function (oTable) {
            var oTable = $("#openWind .j-user-list-table").dataTable();
            //设置每一列的title
            $(".j-user-list-table ").find("tr td:not(:last-child)").each(function (index, obj) {
                $(obj).attr("title", $(obj).text());
            })
        }
    }).on('xhr.dt', function (e, settings, json, xhr) {
        //登陆超时重定向
        if (xhr.getResponseHeader('isRedirect') == 'yes') {
            location.href = "/oms/login";
        }

    })
}