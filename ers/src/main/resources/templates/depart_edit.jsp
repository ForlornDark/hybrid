<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="layui-form" id="depart-form" style="padding-top: 10px">
    <input type="hidden" name="id">
    <input type="hidden" name="parentId">
    <input type="hidden" name="code">
    <div class="layui-form-item">
        <label class="layui-form-label">机构名称</label>
        <div class="layui-input-inline">
            <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入机构名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">纳入统计</label>
        <div class="layui-input-inline">
            <input type="checkbox" name="statistic" lay-skin="switch" lay-text="ON|OFF" checked>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <textarea name="note" placeholder="请输入备注" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" lay-filter="save"  lay-submit>保存</button>
        </div>
    </div>
</form>
</body>
<script>

</script>
</html>
