var deptUserTable = null;//部门用户表
var deptTree = null;//部门树
$(function () {
    initDeptTree(1);//初始化部门树,1是选中节点的id
    initEvents();//初始化页面事件
});

function initEvents() {
    $('body')
        //编辑部门名称
        .on('click', '.j-edit-dept', function () {
            layer.open({
                id: 'openWind',
                type: 1,
                title: '编辑部门名称',
                content: $('#edit_dept_wind').html(),
                area: ['460px', '200px'],
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    if (!$("#openWind .j-edit-dept-form").valid()) {
                        return;
                    }
                    var postData = {
                        id: deptTree.getSelectedNodes()[0].id,
                        parentId: deptTree.getSelectedNodes()[0].ParentDepartmentId,
                        name: $('#openWind input[name=name]').val().trim(),
                    }
                    for (var i = 0; i < zNodes.length; i++) {
                        if (zNodes[i].name == postData.name && zNodes[i].id != postData.id) {
                            layer.msg('部门名已存在！', { icon: 0 });
                            return;
                        }
                    }
                    if ($(layero).find('.layui-layer-btn0').hasClass('btn-disabled')) {
                        return;
                    }
                    $(layero).find('.layui-layer-btn0').addClass('btn-disabled');
                    $.ajax({
                        type: 'post',
                        url: ctx + '/department/updateDept',
                        data: postData,
                        success: function (msg) {
                            if (msg === 'success') {
                                layer.close(index);
                                layer.msg('编辑成功！', { icon: 1 });
                                $('#dept_name').text(postData.name);
                                //更新部门树
                                var node = deptTree.getSelectedNodes()[0];
                                node.name = postData.name;
                                deptTree.updateNode(node);
                                $.each(zNodes, function (index, obj) {
                                    if (obj.id == node.id) {
                                        obj.name = node.name;
                                        return false;
                                    }
                                });
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
                    $('#openWind input[name=name]').val(deptTree.getSelectedNodes()[0].name).focus();
                    //校验
                    $('#openWind .j-edit-dept-form').validate({
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
        //删除部门
        .on('click', '.j-del-dept', function () {
            if (deptTree.getSelectedNodes()[0].id == 1) {
                layer.msg('顶级部门不可删除！', { icon: 0 });
                return;
            }
            if (deptTree.getSelectedNodes()[0].id == 2) {
                layer.msg('未分组部门不可删除！', { icon: 0 });
                return;
            }
            $.ajax({
                type: 'get',
                url: ctx + '/department/deletecheck',
                data: { id: deptTree.getSelectedNodes()[0].id },
                success: function (msg) {
                    if (msg == 'parentLevel') {
                        layer.msg('该部门下有子部门，不可删除！', { icon: 0 });
                    }
                    else if (msg == 'hasUser' || msg == 'success') {
                        layer.confirm('确定要删除该部门吗？', {
                            btn: ['确定', '取消'] //按钮
                        }, function () {
                            $.ajax({
                                type: 'post',
                                url: ctx + '/department/delete',
                                data: { id: deptTree.getSelectedNodes()[0].id },
                                success: function (msg) {
                                    if (msg === 'success') {
                                        layer.msg('删除成功！', { icon: 1 });
                                        //更新部门树
                                        $.ajax({
                                            type: 'get',
                                            url: ctx + '/department/getnodes',
                                            success: function (msg) {
                                                zNodes = JSON.parse(msg);
                                                deptTree.destroy();
                                                initDeptTree(1);
                                            },
                                        })
                                    } else {
                                        layer.msg('删除失败！', { icon: 0 });
                                    }
                                },
                                error: function () {
                                    layer.msg('删除失败！', { icon: 2 });
                                }
                            })

                        });
                    }
                },
                error: function () {
                    layer.msg('部门删除较验失败！', { icon: 2 });
                }
            })
        })
        //添加用户
        .on('click', '.j-add-user', function () {
            layer.open({
                id: 'openWind',
                type: 1,
                title: '添加用户',
                content: $('#add_user_wind').html(),
                area: ['680px', '620px'],
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var postData = {};
                    postData.flag = 1;//1新增，2修改
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
                    postData.dep = deptTree.getSelectedNodes()[0].id;
                    postData.flag = 1;//1新增，2修改
                    $.ajax({
                        type: 'post',
                        url: ctx + '/user/addOrUpdateUser',
                        data: postData,
                        success: function (msg) {
                            if (msg === 'success') {
                                deptUserTable.ajax.reload(function () { }, true);
                                layer.close(index);
                                layer.msg('添加成功！', { icon: 1 });
                            } else {
                                layer.msg('用户已存在！', { icon: 0 });
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
        .on('click', '.j-del-user,.j-del-user-all', function () {
            var userID = '';
            var infoStr = '';
            if ($(this).hasClass('j-del-user-all')) {
                if ($('.j-check-user:checked').length == 0) {
                    layer.msg('请先选中用户！', { icon: 0 });
                    return;
                }
                $('.j-check-user:checked').each(function (index, obj) {
                    if (index == 0) {
                        userID += $(obj).closest('tr').find('.j-check-user').attr('data-id');
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
                    departmentId: deptTree.getSelectedNodes()[0].id,
                    userId: userID
                }
                postData[userKey] = token;
                $.ajax({
                    type: 'post',
                    url: ctx + '/department/deleteUserByDepartmentId',
                    data: postData,
                    success: function (msg) {
                        if (msg === 'success') {
                            deptUserTable.ajax.reload(function () { }, true);
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
        //添加部门
        .on('click', '.j-add-dept', function () {
            if (deptTree.getSelectedNodes()[0].id == 2) {
                layer.msg('该部门下不可添加部门!', { icon: 0 });
                return;
            }
            var parentDeptTree = null;//部门树
            layer.open({
                id: 'openWind',
                type: 1,
                title: '添加部门',
                content: $('#add_dept_wind').html(),
                area: ['460px', '400px'],
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    if (!$("#openWind .j-add-dept-form").valid()) {
                        return;
                    }
                    var postData = {
                        name: $('#openWind input[name=name]').val().trim(),
                        parentId: $('#openWind input[name=parent-dept]').attr('data-id')
                    }
                    for (var i = 0; i < zNodes.length; i++) {
                        if (zNodes[i].name == postData.name) {
                            layer.msg('部门名已存在！', { icon: 0 });
                            return;
                        }
                    }
                    if ($(layero).find('.layui-layer-btn0').hasClass('btn-disabled')) {
                        return;
                    }
                    $(layero).find('.layui-layer-btn0').addClass('btn-disabled');
                    $.ajax({
                        type: 'post',
                        url: ctx + '/department/add',
                        data: postData,
                        success: function (msg) {
                            if (msg === 'success') {
                                layer.close(index);
                                layer.msg('添加成功！', { icon: 1 });
                                //更新部门树
                                $.ajax({
                                    type: 'get',
                                    url: ctx + '/department/getnodes',
                                    success: function (msg) {
                                        zNodes = JSON.parse(msg);
                                        var selectID = deptTree.getSelectedNodes()[0].id;
                                        deptTree.destroy();
                                        initDeptTree(selectID);
                                    },
                                })
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
                    var setting = {
                        view: {
                            dblClickExpand: false,
                            showLine: true,
                            showIcon: true
                        },
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onClick: function (event, treeId, treeNode, clickFlag) {
                                $('#openWind .parent-dept').val(treeNode.name).attr('data-id', treeNode.id);
                                $('#openWind .parent-dept-tree-box').slideUp('fast');
                            }
                        }
                    };
                    //部门树
                    var zNodesBak = $.map(zNodes, function (obj) {
                        //不要包含未分组
                        if (obj.id != 2) {
                            return obj
                        }
                    });
                    parentDeptTree = $.fn.zTree.init($("#openWind .j-parent-dept-tree"), setting, zNodesBak);
                    if (zNodes.length > 0) {
                        var node = parentDeptTree.getNodeByParam('id', deptTree.getSelectedNodes()[0].id);
                        //默认选中顶级部门
                        $('#' + node.tId + '_a').click();
                    }
                    //校验
                    $('#openWind .j-add-dept-form').validate({
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
        //显示与隐藏上级部门树
        .on('click', '.parent-dept', function () {
            if ($('#openWind .parent-dept-tree-box').css('display') == 'block') {
                $('#openWind .parent-dept-tree-box').slideUp('fast');
            }
            else {
                $('#openWind .parent-dept-tree-box').slideDown('fast');
            }
            return false;
        })
        //空白处点击收起上级部门树
        .on('click', '.parent-dept-tree-box', function () {
            return false;
        })
        //阻止收起上级部门树
        .on('click', '', function () {
            $('#openWind .parent-dept-tree-box').slideUp('fast');
        })
        //部门用户全选或取消全选
        .on('change', '.j-check-user-all', function () {
            $('.j-check-user').prop('checked', $(this).prop('checked'));
        })
        //部门用户单个选择或取消选择
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
 * 初始化部门树
 *
 */
function initDeptTree(selectID) {
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            showIcon: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                initDeptUserTable(treeNode.id);
                $('#dept_name').text(treeNode.name);
                if (treeNode.id == 1) {
                    $('.j-add-dept,.j-edit-dept,.j-del-user-all').css('display', 'inline-block');
                    $('.j-del-dept').hide();
                }
                else if (treeNode.id == 2) {
                    $('.j-add-dept,.j-edit-dept,.j-del-dept,.j-del-user-all').hide();
                }
                else {
                    $('.j-add-dept,.j-edit-dept,.j-del-dept,.j-del-user-all').css('display', 'inline-block');
                }
                $('#form_dept_info input[name=name]').val(treeNode.name);
                $('.j-check-user-all').prop('checked', false);
            }
        }
    };
    //部门树
    deptTree = $.fn.zTree.init($("#dept_tree"), setting, zNodes);
    if (zNodes.length > 0) {
        //点击第一个节点
        var node = deptTree.getNodeByParam('id', selectID);
        $('#' + node.tId + '_a').click();
        $('.j-edit-dept').css('display', 'inline-block');
    }
    else {
        $('.j-edit-dept').css('display', 'none');
        $('#dept_user_table').DataTable();
    }

}
/**
 * 获部门下的成员
 *
 */
function initDeptUserTable(id) {
    if (deptUserTable) {
        deptUserTable.settings()[0].ajax.data.value = id;
        deptUserTable.ajax.reload();
        if (id == 2) {
            deptUserTable.columns([0, 7]).visible(false, false);
        } else {
            deptUserTable.columns([0, 7]).visible(true, true);
        }
        return;
    }
    deptUserTable = $('#dept_user_table').DataTable({ //表格初始化
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
                "type": 'department'
            },
        },
        "columnDefs": [{
            "targets": [0],
            "orderable": false,
            "width": "35px",
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
            "class": "text-ellipsis"
        }, {
            "targets": [4],
            "orderable": false,
            "visible": false,
            "class": "text-ellipsis"
        }, {
            "targets": [5],
            "orderable": false,
            "width": "180px",
            "class": "text-ellipsis"
        }, {
            "targets": [6],
            "orderable": false,
            "class": "text-ellipsis"
        }, {
            "targets": [7],
            "orderable": false,
            "class": "center-text",
            "width": "80px",
            "render": function (data, type, full) {
                return '<div class="table-opt-box">' +
                    '<i class="icon-shanchu table-opt-icon j-del-user" data-id="' + data + '"></i>' +
                    ' </div>'
            }
        }],
        //当每次表格重绘的时候触发一个操作，比如更新数据后或者创建新的元素
        "drawCallback": function (oTable) {
            var oTable = $("#dept_user_table").dataTable();
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
            "url": ctx + "/user/datalist",
            //改变从服务器返回的数据给Datatable
            "dataSrc": function (json) {
                var temp = [];
                $.each(json.data, function (index, obj) {
                    temp.push([obj[0], obj[0], obj[3], obj[2]]);

                });
                return temp;
            },
            //将额外的参数添加到请求或修改需要被提交的数据对象
            "data": {
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