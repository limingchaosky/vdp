<!--<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>-->

<head>
    <title>角色</title>
    <link href="${ctxCss}/dataTables/dataTablesgray.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/ztree/ztree.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/user/permission/index.css" rel="stylesheet" type="text/css" />
</head>
<div class="main-right padding-horizontal-sm">
    <!--角色列表-->
    <div class="left-bar">
        <div class="top-bar">
            <div class="top-title">角色</div>
            <div class="fr margin-right-sm">
                <a href="javascript:void(0)" class="right-opt-item j-add-role" title="添加角色">
                    <i class="icon-tianjia text-sm"></i>
                </a>
            </div>
        </div>
        <div id="left_content_box" class="left-content-box">
            <ul id="permission_list_box" class="permission-list-box"></ul>
        </div>
    </div>
    <!--角色列表结束-->
    <div class="right-content-box">
        <!--内容区头部-->
        <div class="right-content-top">
            <span id="permission_title" class="right-content-title"></span>
        </div>
        <!--内容区头部结束-->
        <div class="right-content">
            <!--tab页-->
            <div class="tab-group">
                <div class="tab-box cf">
                    <div class="tab tab-active" tab-idx='0'>页签权限</div>
                    <div class="tab j-user-tab" tab-idx='1'>成员</div>
                    <div class="fr margin-right-sm j-user-opt none">
                        <!--<a href="javascript:void(0)" class="right-opt-item j-add-user">
                            <i class="icon-tianjia text-sm"></i>
                            <span class="text-sm">添加</span>
                        </a>-->
                        <a href="javascript:void(0)" class="right-opt-item del-user-all">
                            <i class="icon-shanchu text-sm"></i>
                            <span class="text-sm">删除</span>
                        </a>
                    </div>
                </div>
                <div class="tab-content">
                    <div class="tabperimssion-tree-box none">
                        <ul id="tabpermission_tree" class="tab-permission-tree ztree"></ul>
                        <div class="tabperimssion-save-box">
                            <button id="save_tabpermission" class="tabperimssion-save">保存</button>
                        </div>
                    </div>
                </div>
                <div class="tab-content hide">
                    <div class="user-table-box">
                        <table id="permissionUserTable" cellspacing="0" cellpadding="0" border="0" width="100%">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" class="j-check-user-all"></th>
                                    <th>用户名</th>
                                    <th>姓名</th>
                                    <th class="displayNone">所属组</th>
                                    <th>所属部门</th>
                                    <th class="displayNone">所属角色</th>
                                    <th>电话</th>
                                    <th style="text-align:center;">操作</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <!--tab页结束-->
        </div>
    </div>
</div>
<!--添加用户弹窗-->
<div id="add_user_wind" class="none">
    <table class="j-user-list-table" cellspacing="0" cellsadding="0" border="0" width="100%">
        <thead>
            <tr>
                <th><input type="checkbox" class="j-wind-check-user-all"></th>
                <th>用户名</th>
                <th>真实姓名</th>
                <th>所属部门</th>
            </tr>
        </thead>
    </table>
</div>
<!--添加用户弹窗结束-->
<!--添加角色与修改角色弹窗-->
<div id="add_role_wind" class="none">
    <div class="wind-box">
        <form class="padding-normal j-add-role-form">
            <div class="wind-row cf">
                <label for="" class="wind-label label-required">角色名称</label>
                <input type="text" class="form-input wind-normal-input" name="name" placeholder="不超过20个字符">
            </div>
        </form>
    </div>
</div>
<!--添加角色弹窗结束-->
<!--角色列表模板-->
<script id="temp_permission_list_box" type="text/html">
    {{each}}
    <li class="left-list" data-id="{{$value.id}}">
        <i class="icon-bumen text-normal list-icon"></i>
        <span class="list-name">{{$value.name}}</span> {{if $value.id!=1}}<i class="icon-bianji list-edit" data-id="{{$value.id}}"
            title="编辑"></i> <i class="icon-shanchu list-del" data-id="{{$value.id}}" title="删除"></i>{{/if}}
    </li>
    {{/each}}
</script>
<!--角色列表模板结束-->
<!--用户表操作模板-->
<script id="temp_user_list" type="text/html">
    {{each}}
    <li class="dropbox-option" data-val="{{$value.id}}">{{$value.id}}</li>
    {{/each}}
</script>
<!--用户表操作模板结束-->
<script src="${ctxJs}/plugins/dataTables/jquery.dataTables.min.js"></script>
<script src="${ctxJs}/plugins/dataTables/ColReorderWithResize.min.js"></script>
<script src="${ctxJs}/plugins/validate/jquery.validate.js"></script>
<script src="${ctxJs}/validateExtent.js"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/template/template-web.js" type="text/javascript"></script>
<script src="${ctxJs}/user/permission/index.js"></script>
<script>
    var zNodes = JSON.parse('${zNodes}');
    var userKey = "${_csrf.parameterName}";
    var token = "${_csrf.token}";

</script>