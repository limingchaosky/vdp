<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<head>
    <title>用户</title>
    <link href="${ctxCss}/dataTables/dataTablesgray.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/ztree/ztree.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/user/user/index.css" rel="stylesheet" type="text/css" />
</head>
<div class="main-right">
    <!--内容区头部-->
    <div class="top-bar">
        <div class="top-title">用户</div>
        <div class="left-opt-item-box">
            <div class="left-opt-item-search">
                <input id="search_user" type="text" class="left-opt-item left-opt-item-input" placeholder="用户名">
                <i id="search_user_icon" class="icon-search"></i>
            </div>
            <select id="search_role" class="left-opt-item left-opt-item-select"></select>
        </div>
        <div class="fr margin-right-sm">
            <a href="javascript:void(0)" class="right-opt-item j-add-user">
                <i class="icon-tianjia text-sm"></i>
                <span class="text-sm">添加</span>
            </a>
            <a href="javascript:void(0)" class="right-opt-item j-del-user-all">
                <i class="icon-shanchu"></i>
                <span class="text-sm">删除</span>
            </a>
            <div id="export_all" class="right-opt-item j-export">
                <i class="icon-tuichu"></i>
                <span class="text-sm">导出</span>
                <div class="opt-drop">
                    <a class="opt-drop-item j-export-current">导出本页</a>
                    <a class="opt-drop-item  j-export-all">导出全部</a>
                </div>
            </div>
        </div>
    </div>
    <!--内容区头部结束-->
    <!--内容区-表格-->
    <div class="content-box">
        <table id="userTable" cellspacing="0" cellpadding="0" border="0" width="100%">
            <thead>
                <tr>
                    <th><input type="checkbox" class="j-check-user-all"></th>
                    <th>账户名称</th>
                    <th>管辖部门</th>
                    <th>角色</th>
                    <th>真实姓名</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th style="text-align:center;">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <!--内容区-表格结束-->
</div>
<!--添加用户-->
<div id="add_user_wind" class="none">
    <div class="wind-box">
        <form class="padding-vertical-normal j-add-user-form">
            <div class="wind-row cf">
                <div class="wind-cell">
                    <label for="" class="wind-label label-required">账户名</label>
                    <input type="text" class="wind-input" tabindex="1" name="userName">
                </div>
                <div class="wind-cell">
                    <label for="" class="wind-label">真实姓名</label>
                    <input type="text" class="wind-input" tabindex="4" name="first">
                </div>
            </div>
            <div class="wind-row cf">
                <div class="wind-cell">
                    <label for="" class="wind-label">密码</label>
                    <input type="password" class="wind-input" tabindex="2" name="pwd">
                </div>
                <div class="wind-cell">
                    <label for="" class="wind-label">电话</label>
                    <input type="text" class="wind-input" tabindex="5" name="phone">
                </div>
            </div>
            <div class="wind-row cf">
                <div class="wind-cell">
                    <label for="" class="wind-label">确认密码</label>
                    <input type="password" class="wind-input" tabindex="3" name="repwd">
                </div>
                <div class="wind-cell">
                    <label for="" class="wind-label">邮箱</label>
                    <input type="text" class="wind-input" tabindex="6" name="email">
                </div>
            </div>
            <div class="wind-row cf">
                <div class="wind-cell">
                    <label for="" class="wind-label label-required">角色</label>
                    <select class="wind-input" name="roleType"></select>
                </div>
                <div class="wind-cell">
                    <label for="" class="wind-label">平台账号</label>
                    <div style="float:right;width:80%;margin-top:1px;">
                        <input class="" style="margin:auto;top:1px;" type="checkbox" name="platformAccount">
                    </div>
                </div>
            </div>
            <input type="hidden" class="j-user-info" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <div class="dept-box">
            <span class="dept-title label-required">部门权限</span>
            <div class="tree-box">
                <ul class="deptTree ztree"></ul>
            </div>
        </div>
    </div>
</div>
<!--添加用户结束-->
<!--用户表的操作模板-->
<script id="temp_opt_box" type="text/html">
    <div class="table-opt-box">
        <i class="iconfont icon-xitong table-opt-icon"></i>
        <div class="opt-hover-box">
            <div class="opt-hover-row j-opt-hover-edit" data-id="{{id}}" data-status="{{status}}">
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
<!--用户表的操作模板结束-->
<!-- 用户详情 -->
<script type="text/html" id="temp_user_info">
    <div class="padding-horizontal-xxl padding-vertical-sm">
        <div class="detail-info-box">
            <div class="cf">
                <div class="detail-info-item">
                    <label class="detail-info-label">账户名称：</label>
                    <span class="detail-info-value">{{@userName}}</span>
                </div>
                <div class="detail-info-item">
                    <label class="detail-info-label">角色：</label>
                    <span class="detail-info-value">{{@roleNmae}}</span>
                </div>
            </div>
            <div class="cf">
                <div class="detail-info-item">
                    <label class="detail-info-label">真实姓名：</label>
                    <span class="detail-info-value">{{@realName}}</span>
                </div>
                <div class="detail-info-item">
                    <label class="detail-info-label">电话：</label>
                    <span class="detail-info-value">{{@phone}}</span>
                </div>
            </div>
            <div class="cf">
                <div class="detail-info-item">
                    <label class="detail-info-label">管辖部门：</label>
                    <span class="detail-info-value">{{@department}}</span>
                </div>
                <div class="detail-info-item">
                    <label class="detail-info-label">邮箱：</label>
                    <span class="detail-info-value">{{@email}}</span>
                </div>
            </div>
        </div>
        {{if processList.length + orderList.length + groupList.length > 0}}
        <div class="flow-info-box">
            {{if processList.length>0}}
            <label class="flow-info-label">相关流程：</label>
            <div class="flow-info-value-box">
                {{each processList}}
                <div class="flow-info-value">{{$value.name}}</div>
                {{/each}}
            </div>
            {{/if}}
            {{if orderList.length>0}}
            <label class="flow-info-label">相关任务：</label>
            <div class="flow-info-value-box">
                {{each orderList}}
                <div class="flow-info-value">{{$value.uuid}}-{{$value.title}}-{{$value.name}}</div>
                {{/each}}
            </div>
            {{/if}}
            {{if groupList.length>0}}
            <label class="flow-info-label">相关组：</label>
            <div class="flow-info-value-box">
                {{each groupList}}
                <div class="flow-info-value">{{$value.name}}</div>
                {{/each}}
            </div>
            {{/if}}
        </div>
        {{/if}}
    </div>
</script>
<!-- 用户详情结束 -->
<script src="${ctxJs}/plugins/dataTables/jquery.dataTables.min.js"></script>
<script src="${ctxJs}/plugins/dataTables/ColReorderWithResize.min.js"></script>
<script src="${ctxJs}/plugins/validate/jquery.validate.js"></script>
<script src="${ctxJs}/validateExtent.js"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/zTree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="${ctxJs}/plugins/template/template-web.js" type="text/javascript"></script>
<script src="${ctxJs}/user/user/index.js"></script>
<script>
    var zNodes = JSON.parse('${zNodes}');
    var userKey = "${_csrf.parameterName}";
    var token = "${_csrf.token}";

</script>