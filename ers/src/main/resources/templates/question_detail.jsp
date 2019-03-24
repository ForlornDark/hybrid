<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/5/16
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>问题详情</title>
    <%@include file="common.jspf"%>
    <link rel="stylesheet" href="${ctx}/css/global.css">
</head>
<body  style="margin: 0px;padding: 15px">
<div id="body">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md11 content detail">
            <div class="fly-panel detail-box">
                <h1>${item.title}</h1>
                <div class="fly-detail-info">
                     <%--<span class="layui-badge">审核中</span>--%>
                    <%--<span class="layui-badge layui-bg-green fly-detail-column">动态</span>--%>

                    <%--<span class="layui-badge" style="background-color: #999;">未结</span>--%>
                    <span class="layui-badge" style="background-color: #5FB878;">提问</span>

                    <%--<span class="layui-badge layui-bg-black">置顶</span>--%>
                    <%--<span class="layui-badge layui-bg-red">精帖</span>--%>

                    <div class="fly-admin-box" data-id="123">
                        <%--<span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>--%>

                        <%--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>--%>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->

                        <%--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>--%>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
                    </div>
                    <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ${item.commentCount}</a>
            <%--<i class="iconfont" title="人气">&#xe60b;</i> 99999--%>
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="javascript:;">
                        <img src="${ctx}${item.photo}" alt="${userName}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="javascript:;" class="fly-link">
                            <cite>${item.userName}</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
                            <%--<i class="layui-badge fly-badge-vip">VIP3</i>--%>
                        </a>
                        <span><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/> </span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span style="padding-right: 10px; color: #FF7200"></span>
                        <span class=" jie-admin" type="edit"></span>
                    </div>
                </div>
                <div class="detail-body photos">
                    ${item.content}
                </div>
                <div class="layui-form layui-form-pane">
                    <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>解答</legend>
                    </fieldset>
                    <form action="#" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" id="question" lay-verify="required"  name="question" value="${item.id}">
                            <button class="layui-btn" lay-filter="save" lay-submit>提交回复</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">




                    <!-- 无数据时 -->
                    <li class="fly-none">消灭零回复</li>
                </ul>


            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/js/question_detail.js"></script>
</html>
