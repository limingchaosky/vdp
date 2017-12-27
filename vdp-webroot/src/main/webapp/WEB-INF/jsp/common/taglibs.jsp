<!-- taglibs.jsp -->
<%@ page import="sun.misc.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- //ctx -->
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxJs" value="${pageContext.request.contextPath}/js"/>
<c:set var="ctxCss" value="${pageContext.request.contextPath}/skin/default/css"/>
<c:set var="ctxImg" value="${pageContext.request.contextPath}/skin/default/images"/>

<!-- taglibs.jsp END-->
