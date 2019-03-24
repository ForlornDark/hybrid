<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/11
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<script src="${ctx}/js/password.js"></script>
<script src="${ctx}/js/md5.js"></script>
<body class="layui-container">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改密码</legend>
</fieldset>
<form class="layui-form" method="post" action="#">
    <div class="layui-form-item">
        <label class="layui-form-label">旧密码</label>
        <div class="layui-input-block">
            <input type="password" name="oldPwd" lay-verify="oldPwd" autocomplete="off" placeholder="请输入原密码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" name="pwd1" lay-verify="pwd1" placeholder="请输入新密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" name="pwd2" lay-verify="pwd2" placeholder="请确认新密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn"  lay-filter="save" lay-submit>立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
</html>
