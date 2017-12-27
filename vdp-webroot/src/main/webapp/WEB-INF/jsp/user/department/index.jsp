<!--<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>-->

<head>
    <title>部门</title>
    <link href="${ctxCss}/dataTables/dataTablesgray.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/ztree/ztree.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/user/department/index.css" rel="stylesheet" type="text/css" />
</head>
<div class="main-right padding-horizontal-sm">
    <!--左侧部门树-->
    <div class="left-bar">
        <div class="top-bar">
            <div class="top-title">部门</div>
            <div class="fr margin-right-sm">
                <a href="javascript:void(0)" class="right-opt-item j-add-dept" title="添加部门">
                    <i class="icon-tianjia text-sm"></i>
                </a>
                <a href="javascript:void(0)" class="right-opt-item j-edit-dept" title="编辑部门名称">
                    <i class="icon-bianji text-sm"></i>
                </a>
                <a href="javascript:void(0)" class="right-opt-item j-del-dept" title="删除部门">
                    <i class="icon-shanchu text-sm"></i>
                </a>
            </div>
            <div class="left-content">
                <ul id="dept_tree" class="deptTree ztree"></ul>
            </div>
        </div>
    </div>
    <!--左侧部门树结束-->
    <div class="right-content-box">
        <!--右侧顶部操作-->
        <div class="right-content-top">
            <span id="dept_name" class="content-title"></span>
        </div>
        <!--右侧顶部操作结束-->
        <div class="right-content">
            <!--用户表-->
            <div class="sub-right-content-top ">
                <span class="content-title">成员</span>
                <div class="fr margin-right-sm">
                    <!--<a href="javascript:void(0)" class="right-opt-item j-add-user">
                        <i class="icon-tianjia text-sm"></i>
                        <span class="text-sm">添加</span>
                    </a>-->
                    <a href="javascript:void(0)" class="right-opt-item j-del-user-all">
                        <i class="icon-shanchu text-sm"></i>
                        <span class="text-sm">删除</span>
                    </a>
                </div>
            </div>
            <div class="user-table-box">
                <table id="dept_user_table" cellspacing="0" cellpadding="0" border="0" width="100%">
                    <thead>
                        <tr>
                            <th><input type="checkbox" class="j-check-user-all"></th>
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>所属组</th>
                            <th class="displayNone">所属部门</th>
                            <th>角色</th>
                            <th>电话</th>
                            <th style="text-align:center;">操作</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <!--用户表结束-->
        </div>
    </div>
</div>
<!--添加用户弹窗-->
<div id="add_user_wind" class="none">
    <table class="j-user-list-table" cellspacing="0" cellpadding="0" border="0" width="100%">
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
<!--添加部门弹窗-->
<div id="add_dept_wind" class="none">
    <div class="wind-box">
        <form class="padding-normal j-add-dept-form">
            <div class="wind-row cf">
                <label for="" class="wind-label label-required">部门名称</label>
                <input type="text" class="form-input wind-normal-input" name="name">
            </div>
            <div class="wind-row cf">
                <label for="" class="wind-label label-required">上级部门</label>
                <input type="text" class="form-input wind-normal-input parent-dept" readonly name="parent-dept" placeholder="请选择上级部门">
                <div class="parent-dept-tree-box none">
                    <ul class="ztree j-parent-dept-tree"></ul>
                </div>
            </div>
        </form>
    </div>
</div>
<!--添加部门弹窗结束-->
<!--修改部门弹窗-->
<div id="edit_dept_wind" class="none">
    <div class="wind-box">
        <form class="padding-normal j-edit-dept-form">
            <div class="wind-row cf">
                <label for="" class="wind-label label-required">部门名称</label>
                <input type="text" class="form-input wind-normal-input" name="name">
            </div>
        </form>
    </div>
</div>
<!--添加部门弹窗结束-->
<!--用户表操作模板-->
<script id="temp_opt_box" type="text/html">
    <div class="table-opt-box">
        <i class="iconfont icon-xitong table-opt-icon"></i>
    </div>
</script>
<!--用户表操作模板结束-->
<script src="${ctxJs}/plugins/dataTables/jquery.dataTables.min.js"></script>
<script src="${ctxJs}/plugins/dataTables/ColReorderWithResize.min.js"></script>
<script src="${ctxJs}/plugins/validate/jquery.validate.js"></script>
<script src="${ctxJs}/validateExtent.js"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/template/template-web.js" type="text/javascript"></script>
<script src="${ctxJs}/user/department/index.js"></script>
<script>
    var zNodes = JSON.parse('${zNodes}');
    var userKey = "${_csrf.parameterName}";
    var token = "${_csrf.token}";

</script>