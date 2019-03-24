<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/5
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@include file="common.jspf"%>
<html>
<head>
    <title>个人资料</title>
</head>
<style>
    body{
        margin-left: 5%;
        margin-top: 15px;
    }
</style>
<link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/zTree.v3/3.5.33/js/jquery.ztree.all.min.js"></script>
<script src="${ctx}/js/personal.js"></script>

<body>
<div class="layui-col-xs8">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名:</label>
            <div class="layui-input-block">
                <input type="text" value="${info.name}" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户组:</label>
            <div class="layui-input-block">
                <input type="text" value="${info.roleName}" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名:</label>
            <div class="layui-input-block">
                <input type="text" name="fullName" value="${info.fullName}" lay-verify="fullName" placeholder="请输入真实姓名" class="layui-input realName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="false" title="男"<c:if test="${info.sex == false}">checked</c:if> >
                <input type="radio" name="sex" value="true" title="女" <c:if test="${info.sex}">checked</c:if>>
            </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码:</label>
            <div class="layui-input-block">
                <input type="tel" name="phone" value="${info.phone}" placeholder="请输入手机号码" lay-verify="phone" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生年月:</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" value="<c:choose><c:when test="${info.birthday == null}">1996-01-01</c:when><c:otherwise><fmt:formatDate value="${info.birthday}" pattern="yyyy-MM-dd" /></c:otherwise></c:choose>" id="picker" placeholder="请输入出生年月"  class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱:</label>
            <div class="layui-input-block">
                <input type="text" name="mail" value="${info.mail}" placeholder="请输入邮箱" lay-verify="email" class="layui-input">
            </div>
        </div>
            <input name="departId" type="hidden" value="${info.departId}">
        <div class="layui-form-item">
            <label class="layui-form-label">所属机构:</label>
            <div class="layui-input-block">
                <input type="text" name="departName" id="depart" value="${info.departName}" lay-verify="required" placeholder="请选择机构" class="layui-input" readonly>
                <ul class="ztree layui-hide" id="tree"></ul>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">自我评价:</label>
            <div class="layui-input-block">
                <textarea name="note"  placeholder="请输入内容" class="layui-textarea">${info.note}</textarea>
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 5%;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
            </div>
        </div>
        </div>
    </form>
</div>

<div class="layui-col-xs4" style="padding-left: 20px">
    <div class="layui-upload-drag" id="upload">
        <i class="layui-icon"></i>
        <p>点击上传头像，或将文件拖拽到此处</p>
        <%--<img  class="layui-circle" id="preview" style="max-width: 200px;max-height: 200px">--%>
    </div>
    <%--<div class="layui-input-block">--%>
        <%--<button id="bt_upload" class="layui-btn layui-btn-normal ">上传</button>--%>
    <%--</div>--%>
    <img src="${ctx}<c:choose>
<c:when test="${empty info.photo}">/img/hole.png</c:when>
<c:otherwise>/source/${info.photo}</c:otherwise>
</c:choose>" id="photo" class="layui-circle" style="max-width: 200px;max-height: 200px">
</div>
</body>
</html>
