<!DOCTYPE html>

<html lang="en" class="no-js">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>金盾OMS</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <c:set var="ctxJs" value="${pageContext.request.contextPath}/js" />
    <c:set var="ctxCss" value="${pageContext.request.contextPath}/skin/default/css" />
    <c:set var="ctxImg" value="${pageContext.request.contextPath}/skin/default/images" />

    <link href="${ctxCss}/login/login.css" rel="stylesheet">
    <link rel="icon" href="${ctxImg}/logo.ico" type="image/x-ico" />
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
	    <script type="text/javascript" src="${ctxJs}/html5.js"></script>
        <![endif]-->
    <script type="text/javascript" src="${ctxJs}/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${ctxJs}/plugins/sha/sha256.js"></script>
    <script type="text/javascript" src="${ctxJs}/plugins/sha/encrypt.js"></script>
    <script type="text/javascript" src="${ctxJs}/login/login.js"></script>
</head>

<body>
    <div id="page-container">
        <div class="main">
            <div class="login_logo">
                <img src="${ctxImg}/Logo.png">
            </div>
            <div class="login_box">
                <div class="login_form">
                    <form id="loginForm" name='loginForm' action="${ctx}/login" method='POST'>
                        <div class="form-group">
                            <div class="form-group-in-user">
                                <input id="name" value="" name="username" type="text" class="form-control in" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-group-in-pass">
                                <input id="password" value="" name="password" type="password" class="password form-control in">
                            </div>
                        </div>
                        <c:if test="${SEC_CODE_FLAG == true}">
                            <div class="form-group">
                                <div class="form-group-in-code">
                                    <input value="" name="sec_code_parameter" placeholder="验证码" type="text" class="code form-control in">
                                    <img  id="img_code" src="${ctx}/getCode" data-src="${ctx}/getCode" class="img-code">
                                </div>
                            </div>
                        </c:if>
                        <div class="login">
                            <label class="t"></label>
                            <div id="submit_btn" class="login_button">
                                <span id="loginsubmitword">&nbsp;登&nbsp;录&nbsp;</span>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="errorMessage" id="errorMessage">
                            ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message} ${error}
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <!-- Javascript -->
        <script src="${ctxJs}/plugins/supersized/supersized.3.2.7.min.js"></script>
        <script src="${ctxJs}/plugins/supersized/supersized-init.js"></script>
</body>

</html>