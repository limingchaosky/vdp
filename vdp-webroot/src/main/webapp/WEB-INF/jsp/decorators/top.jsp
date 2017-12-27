<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!--<style type="text/css">-->
    <!--.numbermess {-->
        <!--margin-top: 30px;-->
        <!--margin-bottom: 10px;-->
        <!--margin-left: 20px;-->
    <!--}-->

    <!--.tishi {-->
        <!--margin-left: 20px;-->
    <!--}-->

    <!--#wmessage {-->
        <!--position: relative;-->
    <!--}-->

    <!--.mestop {-->
        <!--overflow-x: hidden;-->
        <!--position: absolute;-->
        <!--left: 5px;-->
        <!--top: 10px;-->
        <!--background: red;-->
        <!--height: 10px;-->
        <!--width: 10px;-->
        <!--line-height: 20px;-->
        <!--text-align: center;-->
        <!-- -webkit-border-radius: 50%;-->
        <!-- -moz-border-radius: 50%;-->
        <!--border-radius: 50%;-->
        <!--font-size: 12px;-->
        <!--color: #fff;-->
    <!--}-->

    <!--#warnlayer+.layui-layer-setwin .layui-layer-close {-->
        <!--background: url("${ctxImg}/icon-close.png");-->
        <!--position: relative;-->
        <!--top: 0px;-->
    <!--}-->

    <!--.userinfo-box {-->
        <!--margin-top: 12px;-->
    <!--}-->
<!--</style>-->

<c:set var="loginFlag" value="1"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="outUrl" value="/logout"/>
<c:if test="${loginFlag == '1'}">
    <c:set var="pro" value="/tsa/logout"/>
    <c:if test="${!empty serverName}">
        <c:set var="outUrl" value="http://${serverName}${pro}"/>
    </c:if>
    <c:if test="${!empty serverPort}">
        <c:set var="outUrl" value="http://${serverName}:${serverPort}${pro}"/>
    </c:if>
</c:if>

<c:url value="${outUrl}" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<div id="wtop">
    <div id="wtright">
        <div class="nav-top">
            <!--navtestright-->
            <div class="nav-topright">
                <ul>
                    <li>
                        <c:if test="${empty (sessionScope.unauthority) }">
                            <a id="bell" href="${ctx}/workOrder/pending/index" class="top-msg-box" title="消息">
	                           <span id="top_msg" class="top-msg"></span>
	                        </a>
                        </c:if>
                    </li>
                    <li>
                        <a href="javascript:void(0)" class="top-user-info">
                            <span class="top-user-name" id="top_user_name" data-id="${userId}">
                                <sec:authentication property="name" />
                            </span>
                            <div class="user-info-hover-box">
                                <div class="user-info-hover-row j-top-edit-user">
                                    <i class="icon-yonghu"></i>
                                    <span>用户信息</span>
                                </div>
                                <div class="user-info-hover-row" onclick="javascript:$('#logoutForm').submit()">
                                    <i class="icon-power"></i>
                                    <span>退出</span>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <!--navright END-->
            <div class="clear"></div>
        </div>
    </div>
    <div class="clear"></div>
</div>

<div id="userinfo-html" style="display:none;">
    <script src="${ctxJs}/plugins/validate/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctxJs}/validateExtent.js" type="text/javascript"></script>
    <div class="userinfo-box">
        <form id="userinfo-form">
            <div class="u-row">
                <div class="label-box"><label>用户名：</label></div><input type="text" name="userName" readonly class="readonly-input" style="border:none !important;"
                />
            </div>
            <div class="u-row">
                <div class="label-box "><label class="j-label-role">管理角色：</label></div><input type="text" name="roleTypeName" readonly class="readonly-input"
                    style="border:none !important;" />
            </div>
            <div class="u-row">
                <div class="label-box"><label>现有密码：</label></div><input type="password" name="pwd" placeholder="不可超过24个字符" />
            </div>
            <div class="u-row">
                <div class="label-box"><label>新密码：</label></div><input type="password" name="newpwd" placeholder="不可超过24个字符" />
            </div>
            <div class="u-row">
                <div class="label-box"><label>确认密码：</label></div><input type="password" name="newpwd_x" placeholder="不可超过24个字符" />
            </div>
            <div class="u-row u-row-first none">
                <div class="label-box"><label class="label-required">真实姓名：</label></div><input type="text" name="first" placeholder="不可超过24个字符"
                />
            </div>
            <div class="u-row">
                <div class="label-box"><label>电话：</label></div><input type="text" name="phone" placeholder="请输入11位正整数" />
            </div>
            <div class="u-row">
                <div class="label-box"><label>邮箱：</label></div><input type="text" name="email" placeholder="请输入邮箱" />
            </div>
            <input type="hidden" name="id" value="" />
        </form>
    </div>
</div>
<input type="hidden" name="" class="messhid" value="">
<!--<script src="${ctxJs}/reconnecting-websocket.js"></script>-->
<script type="text/javascript">
    var ctx = '${ctx}';
    var host = window.location.host;
    //较验授权信息，弹窗提示
    var promptMsg = decodeURIComponent(getCookie("promptMsg"));
    if (promptMsg != 'null' && promptMsg != '') {
        layer.confirm(promptMsg, {
            btn: ['不再提示', '关闭'],
            end: function () {
                delCookie("promptMsg");
            }
        }, function (index) {
            $.ajax({
                type: 'post',
                url: ctx + '/user/insertRefusePromptUser',
            });
            layer.close(index);
        });
    }

</script>