<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/13
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>报修申请</title>
    <script src="${ctx}/js/apply_edit.js"></script>
</head>
<body class="layui-card-body">
<form class="layui-form layui-col-xs8" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>报修个人信息 如有误，前往个人资料修改</legend>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" value="${info.fullName}" class="layui-input layui-disabled"
                       disabled>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="tel" value="${info.phone}"
                       class="layui-input layui-disabled" disabled>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">所属机构</label>
            <div class="layui-input-inline">
                <input type="text" value="${info.departName}" class="layui-input layui-disabled"
                       disabled>
            </div>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>报修详细信息</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">期望时间</label>
        <div class="layui-input-inline">
            <input type="text" name="expectTime" id="date" lay-verify="required" placeholder="请选择日期" autocomplete="off"
                   class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">选择期望的维修时间</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">设备类型</label>
            <div class="layui-input-inline">
                <select id="equip_type" lay-filter="equip_type"    lay-verify="required">
                </select>
            </div>
        </div>
        <input type="hidden" name="faultId">
        <div class="layui-inline">
            <label class="layui-form-label">故障类型</label>
            <div class="layui-input-inline">
                <select lay-verify="required"  id="fault"  lay-filter="fault">
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">详细地址</label>
        <div class="layui-input-block">
            <input type="text" name="address" lay-verify="required" autocomplete="off" placeholder="请输入详细地址"
                   class="layui-input">
            <div class="layui-form-mid layui-word-aux">如：10栋666</div>
        </div>

    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">故障描述</label>
        <div class="layui-input-block">
            <textarea name="note" placeholder="请输入故障详细描述" lay-verify="required" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
</html>

