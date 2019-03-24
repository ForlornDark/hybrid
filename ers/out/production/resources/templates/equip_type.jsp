<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/7
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@include file="common.jspf"%>

<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/zTree.v3/3.5.33/js/jquery.ztree.all.min.js"></script>
    <script src="${ctx}/js/equip.js"></script>
    <style>
        #menu{
            position: absolute;
        }

    </style>
</head>
<body class="layui-card-body">
   <div id="tree" class="layui-card-body layui-col-xs2 ztree" style="min-height: 300px"></div>
   <div class="layui-col-xs10" >
       <div class="layui-btn-group toolbar">
           <button class="layui-btn layui-btn-normal" id="bt_add"><i class="layui-icon">&#xe654;</i> 添加</button>
           <button class="layui-btn layui-btn-danger" id="bt_del"><i class="layui-icon">&#xe640;</i>删除</button>
       </div>
       <table id="table"  lay-filter="table"></table>
   </div>
   <div id="menu" class="layui-unselect layui-form-select layui-form-selected layui-col-xs1 layui-hide">
       <dl class="layui-anim layui-anim-upbit" style="top: 0">
           <dd id="add" ><i class="layui-icon">&#xe654;</i> <a href="#"> 添加设备类型</a></dd>
           <dd id="update"><i class="layui-icon">&#xe642;</i><a href="#"> 修改设备类型</a></dd>
           <dd id="del"><i class="layui-icon">&#xe640;</i><a href="#"> 删除设备类型</a></dd>
       </dl>
   </div>
</body>
<script type="text/html" id="operate">
    <a class="layui-btn layui-btn-sm" lay-event="update"><i class="layui-icon">&#xe642;</i>编辑</a>
</script>
</html>
