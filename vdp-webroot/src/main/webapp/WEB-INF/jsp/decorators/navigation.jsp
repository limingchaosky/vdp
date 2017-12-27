<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- menu.jsp -->

<div id="menus">
    <div id="wlogo"></div>
    <div id="menubtn"></div>
    <div id="menulist">
        <ul class="mul0">
        <c:forEach items="${navigationlist}" var="nav">
                    <c:choose>
                    <c:when test="${nav.nLevel==1&&nav.url!=null&&nav.url!=''}">
                        <li class="mli0">
                            <a href="${ctx}${nav.url}" class="ma0"><span class="mname ${nav.iconUrl} namehide">${nav.title}</span></a>
                        </li>
                    </c:when>
                    <c:when test="${nav.nLevel==1}">
                        <li class="mli0">
                            <a href="javascript:void(0)" class="ma0"><span class="mname ${nav.iconUrl} namehide">${nav.title}</span></a>
                            <ul class="mul1">
                                <div class="listhover"></div>
                                <c:forEach items="${navigationlist}" var="navsec">
                                <c:if test="${navsec.parentId==nav.id}">
                                <li class="mli1">
                                    <c:choose>
                                    <c:when test="${navsec.url!=null&&navsec.url!=''}">
                                    <a class="ma1" href="${ctx}${navsec.url}">${navsec.title}</a>
                                    </c:when>
                                    <c:otherwise>
                                    <a class="ma1" href="javaScript:void(0);">${navsec.title}</a>
                                    <ul class="mul2">
                                       <div class="listhover2"></div>
                                       <c:forEach items="${navigationlist}" var="navthr">
                                          <c:if test="${navthr.parentId==navsec.id}">
                                              <li class="mli2">
                                                 <a class="ma2" href="${ctx}${navthr.url}">${navthr.title}</a>
                                              </li>
                                          </c:if>
                                       </c:forEach>
                                    </ul>
                                    </c:otherwise>
                                    </c:choose>
                                </li>
                                </c:if>
                                </c:forEach>
                            </ul>
                    </li>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                    </c:choose>
        </c:forEach>
         </ul>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        var path='${pageContext.request.contextPath}';
        var ctxImg='${ctxImg}';
        bgurl="url('${ctxImg}/leftmenu/logo.png')";
        $('#wlogo').css({
            width:'100%',
            height:'50px',
            backgroundImage:bgurl,
            backgroundRepeat:'no-repeat',
            backgroundPositionY: '-8px'
        });
})

</script>
