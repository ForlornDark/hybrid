<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/29
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
            padding: 0px 10px;
        }
    </style>
    <script src="${ctx}/js/apply_index.js"></script>
    <script>
        var role = ${user.roleId}
    </script>
</head>
<body>

<%--<div class="layui-tab layui-tab-brief">--%>
    <%--<ul class="layui-tab-title">--%>
        <%--<li class="layui-this">申请列表</li>--%>
        <%--<li>维修列表</li>--%>
    <%--</ul>--%>
    <%--<div class="layui-tab-content">--%>
        <%--<div class="layui-tab-item layui-show">--%>
           <table id="apply" lay-filter="operate"></table>
        <%--</div>--%>
        <%--<div class="layui-tab-item">--%>
            <%--<table id="repair"></table>--%>
        <%--</div>--%>
    <%--</div>--%>
    <script id="applyTool" type="text/html">
        {{#  if(d.state == 0 && role == 1){ }}
        <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="unHandle" title="不予处理"><i class="layui-icon">&#xe6c5;</i></a>
        {{#  }else if(d.state == 0 && role == 2){ }}
        <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="accept" title="接受"><i class="layui-icon">&#xe618;</i></a>
        {{#  }else if(d.state == 0 && role == 3){ }}
        <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete" title="删除"><i class="layui-icon">&#xe640;</i></a>
        {{#  } }}
        <a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="detail" title="查看详情"><i class="layui-icon">&#xe609;</i></a>
    </script>
</div>
</body>
</html>
