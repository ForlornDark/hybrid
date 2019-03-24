<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/31
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="${ctx}/lib/bootstrap-star-rating/css/star-rating.css" media="all" rel="stylesheet" type="text/css" />

    <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
    <link href="${ctx}/lib/bootstrap-star-rating/themes/krajee-svg/theme.css" media="all" rel="stylesheet" type="text/css" />
    <script src="${ctx}/lib/bootstrap-star-rating/js/star-rating.js" type="text/javascript"></script>

    <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
    <script src="${ctx}/lib/bootstrap-star-rating/themes/krajee-svg/theme.js"></script>

    <!-- optionally if you need translation for your language then include locale file as mentioned below -->
    <script src="${ctx}/lib/bootstrap-star-rating/js/locales/zh.js"></script>

</head>
<body>
<form class="layui-form" id="evaluate">
    <input type="hidden" name="applyId">
    <div class="layui-form-item">
        <label class="layui-form-label">评分：</label>
        <div class="layui-input-inline">
            <div style="margin-top: 10px">
                <div id="mark" style="margin-top: 15px">
            </div>

            </div>
            <input name="mark" value="10" type="hidden">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">完成时间：</label>
        <div class="layui-input-inline">
            <input type="text" name="repairTime" lay-verify="datetime"
                   id="picker" placeholder="请选择时间" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-inline">
            <textarea class="layui-textarea" name="note"></textarea>
        </div>
    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label"></label>
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit="" lay-filter="save">提交</button>
        </div>
    </div>
</form>

</body>
<script>
    $("#mark").rating({
        min: 1,
        max: 10,
        step: 1,
        size: 'xs',
        language: 'zh',
        filledStar:'<i class="layui-icon layui-icon-rate-solid" style="color: #1E9FFF;"></i>',
        emptyStar:'<i class="layui-icon layui-icon-rate" style="color: #1E9FFF;"></i>',
        starCaptions: function (val) {
            return val+'星'
        }
    });
    $('#mark').on('rating:change', function(event, value, caption) {
        $("input[name=mark]").val(value)
    });
    $('#mark').rating('update', 10);
    var now = new Date()
    var max =now.format('yyyy-MM-dd HH:mm:ss')
    var laydate = layui.laydate
    laydate.render({
        elem:'#picker',
        max:max,
        type:'datetime',
        value:now
    })
</script>
</html>
