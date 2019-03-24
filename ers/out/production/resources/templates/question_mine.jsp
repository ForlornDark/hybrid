<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/5/16
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>发帖管理</title>
    <%@include file="common.jspf"%>
    <link rel="stylesheet" href="${ctx}/css/global.css">
</head>
<body style="margin: 0px;padding: 15px">
<div class="fly-panel fly-panel-user" pad20>
    <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->
    <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title" id="LAY_mine">
            <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span id="count"></span>）</li>
            <%--<li data-type="collection" data-url="/collection/find/" lay-id="collection">我收藏的帖（<span>16</span>）</li>--%>
        </ul>
        <div class="layui-tab-content" style="padding: 20px 0;">
            <div class="layui-tab-item layui-show">
                <ul id="fly-list" class="mine-view jie-row">
                </ul>
                <div style="text-align: center">
                    <div id="laypage-main"></div>
                </div>
                <div id="LAY_page"></div>
            </div>
            <div class="layui-tab-item">
                <ul class="mine-view jie-row">
                    <li>
                        <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
                        <i>收藏于23小时前</i>  </li>
                </ul>
                <div id="LAY_page1"></div>
            </div>
        </div>
    </div>
</div>
</div>

</body>
<script src="${ctx}/js/question_mine.js"></script>

</html>
