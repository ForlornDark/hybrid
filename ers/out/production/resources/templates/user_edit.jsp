<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="layui-form" style="padding-top: 10px">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" id="username" name="name" lay-verify="name" autocomplete="off" placeholder="请输入用户名" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-inline">
            <input type="text" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-inline">
            <select name="roleId" id="roleId" lay-verify="required"  lay-search="">
                <option value="2">repairman</option>
                <option value="3">user</option>
                <option value="1">admin</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="false" title="男" checked>
            <input type="radio" name="sex" value="true" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" id="ok" class="layui-btn" lay-filter="save"  lay-submit>保存</button>
        </div>
    </div>
</form>
</body>
<script>
    layui.use("form", function(){
        var form = layui.form;
        form.render()
    });
    $("#cancel").click(()=>{
        layer.closeAll('page')
    })
    $("#ok").click(()=>{

    })
</script>
</html>
