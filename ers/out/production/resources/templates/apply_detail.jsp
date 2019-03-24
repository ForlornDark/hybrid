<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/31
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>申请详情</title>
    <%@include file="common.jspf"%>
    <style>
        body{
            padding: 10px;
        }

    </style>
    <script>
        $(function () {
            $('.back').click((e)=>{
                window.history.back();
            })
        })
    </script>
</head>
<body>
<div class="layui-col-sm-offset11 layui-col-sm1">
    <a class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal back" title="返回"><i class="layui-icon">&#xe65c;</i></a>
</div>
<fieldset class="layui-elem-field layui-field-title" >
    <legend>报修个人信息</legend>
</fieldset>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input type="text" value="${detail.fullName}"
                       class="layui-input" readonly >

            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.phone}"
                       class="layui-input" style="color: #ff452c" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">所属机构：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.departName}"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>报修详细信息</legend>
</fieldset>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">申请时间：</label>
            <div class="layui-input-inline">
                <input type="text" value="<fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd" />"
                       class="layui-input" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预约时间：</label>
            <div class="layui-input-inline">
                <input type="text" value="<fmt:formatDate value="${detail.expectTime}" pattern="yyyy-MM-dd" />"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">设备类型：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.equipName}"
                       class="layui-input" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">损坏类型：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.infoName}"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">详细地址：</label>
            <div class="layui-input-block">
                <div class="layui-col-sm6">
                    <textarea class="layui-textarea" readonly>${detail.detailAddress}</textarea>
                </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">问题描述：</label>
        <div class="layui-input-block">
            <div class="layui-col-sm6">
                <textarea class="layui-textarea" readonly>${detail.faultDescrip}</textarea>
            </div>
        </div>
    </div>
</div>
</body>
</html>
