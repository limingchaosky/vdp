<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<title>500错误</title>

<style type="text/css">
body, code, dd, div, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6,
	input, legend, li, ol, p, pre, td, textarea, th, ul {
	margin: 0;
	padding: 0
}

body {
	font: 14px/1.5 'Microsoft YaHei', 'Microsoft YaHei', Helvetica, Sans-serif;
	min-width: 1200px;
	background: #f0f1f3;
}

:focus {
	outline: 0
}

h1, h2, h3, h4, h5, h6, strong {
	font-weight: 700
}

a {
	color: #428bca;
	text-decoration: none
}

a:hover {
	text-decoration: underline
}

.error-page {
	background: #f0f1f3;
	padding: 80px 0 180px
}

.error-page-container {
	position: relative;
	z-index: 1
}

.error-page-main {
	position: relative;
	background: #f9f9f9;
	margin: 0 auto;
	width: 617px;
	-ms-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 50px 50px 70px
}

.error-page-main:before {
	content: '';
	display: block;
	background: url(${ctxImg}/errorPageBorder.png);
	height: 7px;
	position: absolute;
	top: -7px;
	width: 100%;
	left: 0
}

.error-page-main h3 {
	font-size: 24px;
	font-weight: 400;
	border-bottom: 1px solid #d0d0d0
}

.error-page-main h3 strong {
	font-size: 54px;
	font-weight: 400;
	margin-right: 20px
}

.error-page-main h4 {
	font-size: 20px;
	font-weight: 400;
	color: #333
}

.error-page-actions {
	font-size: 0;
	z-index: 100
}

.error-page-actions div {
	font-size: 14px;
	display: inline-block;
	padding: 30px 0 0 10px;
	width: 50%;
	-ms-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	color: #838383
}

.error-page-actions ol {
	list-style: decimal;
	padding-left: 20px
}

.error-page-actions li {
	line-height: 2.5em
}

.error-page-actions:before {
	content: '';
	display: block;
	position: absolute;
	z-index: -1;
	bottom: 17px;
	left: 50px;
	width: 200px;
	height: 10px;
	-moz-box-shadow: 4px 5px 31px 11px #999;
	-webkit-box-shadow: 4px 5px 31px 11px #999;
	box-shadow: 4px 5px 31px 11px #999;
	-moz-transform: rotate(-4deg);
	-webkit-transform: rotate(-4deg);
	-ms-transform: rotate(-4deg);
	-o-transform: rotate(-4deg);
	transform: rotate(-4deg)
}

.error-page-actions:after {
	content: '';
	display: block;
	position: absolute;
	z-index: -1;
	bottom: 17px;
	right: 50px;
	width: 200px;
	height: 10px;
	-moz-box-shadow: 4px 5px 31px 11px #999;
	-webkit-box-shadow: 4px 5px 31px 11px #999;
	box-shadow: 4px 5px 31px 11px #999;
	-moz-transform: rotate(4deg);
	-webkit-transform: rotate(4deg);
	-ms-transform: rotate(4deg);
	-o-transform: rotate(4deg);
	transform: rotate(4deg)
}
</style>

</head>
<body>

	<div class="error-page">
		<div class="error-page-container">
			<div class="error-page-main">
				<h3>
					<strong>500</strong>服务器内部错误
				</h3>
				<div class="error-page-actions">
					<div>
						<h4>可能原因：</h4>
						<ol>
							<li>资源实例不存在或信息不完整</li>
							<li>程序内部异常报错</li>
						</ol>
					</div>
					<div>
						<h4>可以尝试：</h4>
						<ul>
							<li><a href="${ctx}/system/homepage/homepage">返回首页</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>