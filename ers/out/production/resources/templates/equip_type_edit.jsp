<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/14
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="layui-form" id="form" style="padding-top: 10px">
    <input name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">设备类型</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入设备类型" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设备描述</label>
        <div class="layui-input-inline">
            <textarea name="note" placeholder="请输入设备描述" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" lay-filter="save"  lay-submit>保存</button>
        </div>
    </div>
</form>
</body>
</html>
