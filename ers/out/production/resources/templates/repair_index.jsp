<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/27
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@include file="common.jspf"%>
    <style>
        body{
            padding: 0 10px;
        }
    </style>
    <script>
        var  role = ${user.roleId}
    </script>
    <script src="${ctx}/js/repair_index.js"></script>
</head>
<body>
<div>
    <table id="repair" lay-filter='operate'></table>
</div>
</body>
<script id="applyTool" type="text/html">
    {{#  if(d.state == 1 && role == 2){ }}
    <a class="layui-btn layui-btn-sm layui-btn-warm" lay-event="done" title="已处理"><i class="layui-icon">&#xe605;</i></a>
    {{#  }else if(d.state == 3 && role == 3 && d.evaluateMark == null){ }}
    <a class="layui-btn layui-btn-sm layui-btn-warm" lay-event="evaluate" title="评价"><i class="layui-icon">&#xe6b2;</i></a>
    {{#  } }}
    <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="detail" title="查看详情"><i class="layui-icon">&#xe609;</i></a>
</script>
</html>
