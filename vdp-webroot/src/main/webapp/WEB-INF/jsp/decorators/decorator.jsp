<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title><sitemesh:write property='title' /></title>
    <!--bootstrap样式引入-->
    <link href="${ctxCss}/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!--初始化样式-->
    <link href="${ctxCss}/init.css" rel="stylesheet" type="text/css" />
    <!--<link href="${ctxCss}/layout/inpage.css" rel="stylesheet" type="text/css" />-->
    <!--整体结构-->
    <link href="${ctxCss}/world.css" rel="stylesheet" type="text/css" />
    <!--右侧顶部-->
    <link href="${ctxCss}/wtop.css" rel="stylesheet" type="text/css" />
    <!--图标集合-->
    <link href="${ctxCss}/icons.css" rel="stylesheet" type="text/css" />
    <!--左侧菜单CSS-->
    <link href="${ctxCss}/leftmenu.css" rel="stylesheet" type="text/css" />
     <!--公共样式-->
    <link href="${ctxCss}/common.css" rel="stylesheet" type="text/css" />
    <!-- iconfont -->
    <link href="${ctxCss}/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${ctxCss}/iconfont/iconfont-change.css" rel="stylesheet" type="text/css" />
    <link rel="icon" href="${ctxImg}/logo.ico" type="image/x-ico" />
    <script src="${ctxJs}/jquery-1.11.0.min.js"></script>
    <script src="${ctxJs}/plugins/layer/layer.js" type="text/javascript"></script>

    <!--<script src="${ctxJs}/gold.js"></script>-->
    <!--<script type="text/javascript" src="${ctxJs}/plugins/sha/sha256.js"></script>-->
    <!--<script type="text/javascript" src="${ctxJs}/plugins/sha/encrypt.js"></script>-->
    <!--左侧菜单JS-->
    <script src="${ctxJs}/menu.js"></script>
    <script src="${ctxJs}/wtop.js"></script>
    <script src="${ctxJs}/public.js"></script>
    <!--公共js-->
    <script src="${ctxJs}/common.js"></script>
    <!--调试js-->
    <!--<script src="${ctxJs}/log.js"></script>-->
    <sitemesh:write property='head' />

</head>
<body>
<div id="world">

    <jsp:include page="/system/navigation/usernavigation.do" flush="true"/>
    <!--content-->
    <div id="wcontent">
    <jsp:include page="/system/navigation/top.do" flush="true"/>
    <sitemesh:write property='body' />
    </div>

</div>
</body>
</html>