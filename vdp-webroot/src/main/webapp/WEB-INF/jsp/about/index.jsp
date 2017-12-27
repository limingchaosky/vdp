<!-- userlist.jsp -->
<!-- <%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%> -->


<head>
    <title>关于1111</title>
    <link href="${ctxCss}/layout/inpage.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        .aboutmain {
            background: #fff;
            margin-left: 10px;
            margin-right: 10px;
            min-width: 1110px;
            position: absolute;
            left: 0;
            right: 0;
            bottom: 10px;
            top: 10px;
        }

        .aboutmain .toplogo {
            padding: 20px 50px;
        }

        .aboutmain .toplogo .toplogoleft {
            margin: 0 auto;
            margin-bottom: 14px;
        }


        .aboutmain .toplogo .toplogoright {
            width: 200px;
        }

        .aboutmain .toplogo .toplogoright li {
            line-height: 30px;
            color: #576783;
            font-size: 14px;
        }

        .width-95 {
            width: 95px;
        }

        .width-52 {
            width: 52px;
        }

        .messh4 {
            color: #666;
            padding-bottom: 5px;
            font-size: 14px;
        }

        .accreditbox {
            padding: 20px 0;
        }

        .messagebox-tsa {
            padding-left: 50px;
            padding-right: 50px;
        }

        .messagebox-oms {
            padding-left: 50px;
            padding-right: 50px;
        }



        .accreditbox .messagebox ul {
            height: 30px;
            line-height: 30px;
            font-size: 14px;
        }

        .accreditbox .messagebox ul li {
            float: left;
            height: 30px;
            line-height: 30px;
            color: #6b686a;
        }

        .goldenbox .goldlogo {
            height: 70px;
            padding-left: 50px;
        }

        .goldenbox .goldlogo img {
            margin-top: 30px;
        }

        .goldenbox .goldchild,
        .copymess {
            line-height: 26px;
            padding-left: 50px;
            color: #6b686a;
            font-size: 14px;
        }

        .copymess {
            color: #aaaaaa;
        }

        .goldenbox .goldchild ul {
            overflow: auto;
        }

        .goldenbox .goldchild ul li {
            float: left;
        }

        .outacc {
            cursor: pointer
        }

        input.uploadFile {
            position: absolute;
            opacity: 0;
            filter: alpha(opacity=0);
            cursor: pointer;
            width: 1px;
            height: 1px;
            overflow: hidden;
        }

        .inputup {
            height: 32px !important;
            width: 200px;
            box-sizing: border-box;
            border: 1px solid #DFDFDF;
            padding: 0 5px;
        }

        .input-link {
            display: inline-block;
            height: 32px;
            width: 40px;
            border: 1px solid #d3d3d3;
            border-left: 0;
            text-align: center;
            position: relative;
            left: -4px;
            top: 1px;
            line-height: 32px;
            box-sizing: border-box;
            color: #6b686a;
        }

        .lookview {
            color: #F3D581;
        }

        .explort-box {
            margin-top: 20px;
        }

        .explort-box i {
            color: #50AAEE;
        }

        .logo-box {
            display: inline-block;
            text-align: center;
        }

        .hline {
            height: 1px;
            border: none;
            border-top: 1px solid #EAEAEA;
            margin-left: 20px;
            margin-right: 20px;
        }

        .relative {
            position: relative;
        }

        .tip {
            position: absolute;
            line-height: 14px;
            bottom: -20px;
            color: red;
        }

        .goldenbox {
            padding-bottom: 20px;
        }
    </style>
</head>

<div class="main_right">
    <div class="aboutmain">
        <!-- logo -->
        <div class="mainbox">
            <!-- 授权信息 -->
            <div class="auth-info">
                <div class="accreditbox">
                    <form action="${ctx}/about/fileupload" method="post" id="beanForm_oms" enctype="multipart/form-data">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="messagebox messagebox-oms">
                            <h4 class="messh4">OMS授权信息1111111</h4>
                            <div class="messagebox">
                                <ul>
                                    <li class="width-95">产品名称</li>
                                    <li>Operation management system&nbsp;&nbsp&nbsp;&nbsp运营管理系统</li>
                                </ul>
                                <ul>
                                    <li class="width-95">版本信息</li>
                                    <li>6.0.0</li>
                                </ul>
                                <ul>
                                    <li class="width-95">客户名称</li>
                                    <li>${authInfo.company}</li>
                                </ul>
                                <ul>
                                    <li class="width-95">授权期限</li>
                                    <li>${authInfo.beginEndDate}</li>
                                </ul>
                                <ul>
                                    <li class="width-95">维保时间</li>
                                    <li>${authInfo.supportDate}</li>
                                </ul>
                                <ul>
                                    <li class="width-95">授权码</li>
                                    <li>${authInfo.deviceUnique}</li>
                                </ul>
                                <ul>
                                    <li class="width-95">授权文件</li>
                                    <li class="relative">
                                        <input type="hidden" name="MAX_FILE_SIZE" value="10000000000">
                                        <input type="text" id="incCopyFileLinux_oms" class="input-style inputup" readonly name="incCopyFileLinux" />
                                        <a href="javascript:void(0);" id="btn_select_oms" class="input-link btn btn"><i class="icon-wenjianjia lookview" title="浏览"></i></a>
                                        <input type="file" name="file" accept=".oms" id="incTrueFileLinux_oms" class="uploadFile btn" style="width:350px; left:195px;right:none"
                                        />
                                        <input type="button" class="btn btn-primary width100px" value="授权" id="getauthority_oms" />
                                        <input type="hidden" name="type" value="oms" />
                                        <span id="installTarLinuxTip_oms" class="tip"></span>
                                        <iframe id="" name="installUpdateLinux" style="display:none;"></iframe>
                                    </li>
                                </ul>
                                <ul class="explort-box">
                                    <li class="width-95"></li>
                                    <li class="outacc"><i class="iconfont icon-tuichu btn-parimy"></i>
                                        <a href="javascript:void(0);" style="text-decoration: none;color: #6B686A" onclick="isFileExistOMS();">导出&nbsp;&nbsp;</a>
                                        <a id="downLoadFile_oms" href="${ctx}/about/fileload" style="text-decoration: none;">
                                            <p></p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <hr class="hline">
        <!-- 公司信息 -->
        <div class="goldenbox">
            <div class="goldlogo">
                <img src="${ctxImg}/logo-web.png" class="logogold"/>
            </div>
            <div class="goldchild">
                <ul>
                    <li class="width-52">官网</li>
                    <li>www.neiwang.cn</li>
                </ul>
                <ul>
                    <li class="width-52">传真</li>
                    <li>0531-66897021</li>
                </ul>
                <ul>
                    <li class="width-52">电话</li>
                    <li>400-6868-531</li>
                </ul>
                <ul>
                    <li class="width-52">邮箱</li>
                    <li>sales@neiwang.cn</li>
                </ul>
            </div>
            <p class="copymess">Copyright &copy; 2017 goldencis Inc. All rights reserved.</p>
        </div>
    </div>
</div>
<script src="${ctxJs}/about/index.js"></script>