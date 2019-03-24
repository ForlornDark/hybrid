<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/5/15
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>提问</title>
    <%@ include file="common.jspf"%>
    <link rel="stylesheet" href="${ctx}/css/global.css">
</head>
<body style="margin-top: 15px">
<div class="layui-container">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <form action="" method="post">
                            <input name="question"value="${item.id}" hidden>
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md9">
                                    <label for="L_title" class="layui-form-label">标题</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="L_title" name="title" value="${item.title}" required lay-verify="required" autocomplete="off" class="layui-input">
                                        <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea id="L_content" name="content" lay-verify="required"  placeholder="详细描述" class="layui-textarea fly-editor" style="height: 260px;">${item.content}</textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-filter="save" lay-submit>立即发布</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script>--%>
    <%--layui.use('form', function(){--%>
        <%--var form = layui.form;--%>
        <%--form.render()--%>
        <%--//各种基于事件的操作，下面会有进一步介绍--%>
    <%--});--%>
<%--</script>--%>
<script>
    // layui.cache.page = 'jie';
    // layui.cache.user = {
    //     username: '游客'
    //     ,uid: -1
    //     ,avatar: '../../res/images/avatar/00.jpg'
    //     ,experience: 83
    //     ,sex: '男'
    // };
    layui.config({
        version: "3.0.0"
        ,base: ctx+'/js/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
    var util = layui.util
    util.fixbar({
        bgcolor: '#009688',
        bar1:'&#xe65c'
        ,click: function(type){
            if(type == 'bar1'){
                window.history.back();
            }
        }
    });
    layui.use('form',function () {
        form = layui.form
        form.render()
        form.on('submit(save)',function (t) {
            $.post(ctx+'/question/save',t.field,function (re) {
                if (re.success){
                    layer.msg("保存成功")
                    location.href = ctx+'/question/index';
                }else {
                    layer.msg("保存失败")
                }
            })
            return false
        })
    })

</script>
</body>
</html>
