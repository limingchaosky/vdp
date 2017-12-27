<!--<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>-->

<head>
    <title>部门</title>
    <link href="${ctxCss}/dataTables/dataTablesgray.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/ztree/ztree.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/user/clientUser/index.css" rel="stylesheet" type="text/css" />
</head>
<div class="main-right">
    <!--左侧部门树-->
    <div class="left-bar">
        <div class="top-bar">
            <div class="top-title">部门</div>
        </div>
        <div class="left-content">
            <ul id="dept_tree" class="deptTree ztree"></ul>
        </div>
    </div>
    <!--左侧部门树结束-->
    <div class="right-content-box">
        <!--右侧顶部操作-->
        <div class="right-content-top">
            <div class="bar-item-box">
                <div class="bar-item bar-item-search">
                    <input id="bar_searchstr" type="text" placeholder="部门名称">
                    <i id="bar_searchstr_icon" class="icon-search"></i>
                </div>
                <a id="bar_add_dept" class="bar-item bar-item-icon icon-add" title="添加部门"></a>
                <a id="bar_export" class="bar-item bar-item-icon icon-export" title="导出"></a>
            </div>
        </div>
        <!--右侧顶部操作结束-->
        <div class="right-content">
            <!--部门表-->
            <div class="dept-table-box">
                <table id="dept_table" cellspacing="0" cellpadding="0" border="0" width="100%">
                    <thead>
                    <tr>
                        <th>部门名称</th>
                        <th>上级部门</th>
                        <th>部门负责人</th>
                        <th>电话</th>
                        <th>设备总数</th>
                        <th style="text-align:center;">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <!--部门表结束-->
        </div>
    </div>
</div>
<!--添加部门弹窗-->
<div id="add_dept_wind" class="none">
    <div class="wind-box">
        <form class="padding-normal j-add-dept-form">
            <div class="wind-row cf j-dept-name">
                <label for="" class="wind-label label-required">部门名称</label>
                <input type="text" class="form-input wind-normal-input" name="name" maxlength="20">
            </div>
            <div class="wind-row cf j-parent-dept">
                <label for="" class="wind-label label-required">上级部门</label>
                <input type="text" class="form-input wind-normal-input parent-dept" readonly name="parent-dept" placeholder="请选择上级部门">
                <div class="parent-dept-tree-box none">
                    <ul class="ztree j-parent-dept-tree"></ul>
                </div>
            </div>
            <div class="wind-row cf">
                <label for="" class="wind-label">负责人</label>
                <input type="text" class="form-input wind-normal-input" name="owner" maxlength="20">
            </div>
            <div class="wind-row cf">
                <label for="" class="wind-label">电话</label>
                <input type="text" class="form-input wind-normal-input" name="departmentTel">
            </div>
            <div class="wind-row cf">
                <label for="" class="wind-label">设备总数</label>
                <input type="text" class="form-input wind-normal-input" name="deviceNum">
            </div>
        </form>
    </div>
</div>
<!--添加部门弹窗结束-->
<!--部门表操作模板-->
<script id="temp_opt_box" type="text/html">
    <div class="table-opt-box">
        <i class="icon-setting table-opt-icon"></i>
        <div class="opt-hover-box">
            <div class="opt-hover-row j-opt-hover-edit" data-id="{{id}}">
                <i class="icon-bianji"></i>
                <span class="text-sm margin-left-xs">编辑</span>
            </div>
            <div class="opt-hover-row j-opt-hover-delete" data-id="{{id}}">
                <i class="icon-shanchu"></i>
                <span class="text-sm margin-left-xs">删除</span>
            </div>
        </div>
    </div>
</script>
<!--部门表操作模板结束-->
<script src="${ctxJs}/plugins/dataTables/jquery.dataTables.min.js"></script>
<!--<script src="${ctxJs}/plugins/dataTables/ColReorderWithResize.min.js"></script>-->
<script src="${ctxJs}/plugins/zTree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/template/template-web.js" type="text/javascript"></script>
<script src="${ctxJs}/user/department/index.js"></script>
<script>
  var zNodes = JSON.parse('${zNodes}');
</script>