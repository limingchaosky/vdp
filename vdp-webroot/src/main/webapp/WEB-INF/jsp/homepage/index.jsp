<!-- userlist.jsp -->

<!--<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>-->

<head>
    <title>金盾OMS</title>
    <!--<link href="${ctxCss}/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css" />-->
    <link href="${ctxCss}/homepage/index.css" rel="stylesheet" type="text/css" />
    <!--<link href="${ctxCss}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />-->
    <!--<link href="${ctxCss}/workorder/pending/index.css" rel="stylesheet" type="text/css" />-->
</head>

<div class="main_right">
    <div class="row-top">
        <div class="cell">
            <div class="curr-visit-chart-box">
                <div id="curr_Visit_Chart"></div>
                <div class="curr_Visit_Number">
                    <span>当前正在访问数</span> <span class="number">360</span>
                </div>
            </div>
            <div class="serverinfo-chart-box">
                <div class="serverinfo-chart-title">
                    <div class="serverinfo-chart-name">CPU</div>
                    <div class="serverinfo-chart-value">0%</div>
                </div>
                <div id="serverinfo_cpu_chart"></div>
            </div>
            <div class="serverinfo-chart-box">
                <div class="serverinfo-chart-title">
                    <div class="serverinfo-chart-name">MYSQL</div>
                    <div class="serverinfo-chart-value">0%</div>
                </div>
                <div id="serverinfo_mysql_chart"></div>
            </div>
            <div class="serverinfo-chart-box">
                <div class="serverinfo-chart-title">
                    <div class="serverinfo-chart-name">内存</div>
                    <div class="serverinfo-chart-value">0%</div>
                </div>
                <div id="serverinfo_mem_chart"></div>
            </div>
        </div>
        <div class="cell">
            <p class="section-title">视频数据导出Top10</p>
            <div id="vedio_export"></div>
        </div>
        <div class="cell">
            <p class="section-title">潜在风险</p>
            <div id="potent_risk"></div>
        </div>
    </div>
    <div class="row-bottom">
        <div class="cell">
            <!--<p class="section-title">数据导出统计</p>-->
            <div id="data_export"></div>
        </div>
    </div>
</div>

<script src="${ctxJs}/plugins/echarts/echarts.common.min.js"></script>
<!--<script src="${ctxJs}/plugins/template/template-web.js"></script>-->
<!--<script src="${ctxJs}/plugins/ueditor/ueditor.config.js"></script>-->
<!--<script src="${ctxJs}/plugins/ueditor/ueditor.all.js"></script>-->
<!--<script src="${ctxJs}/plugins/jquery.PrintArea/jquery.PrintArea.js"></script>-->
<!--<script src="${ctxJs}/plugins/validate/jquery.validate.js"></script>-->
<!--<script src="${ctxJs}/validateExtent.js"></script>-->
<script src="${ctxJs}/plugins/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<!--<script src="${ctxJs}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>-->
<!--<script src="${ctxJs}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>-->
<script src="${ctxJs}/homepage/index.js"></script>