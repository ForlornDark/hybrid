<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/7
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.bootcss.com/zTree.v3/3.5.33/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/zTree.v3/3.5.33/js/jquery.ztree.all.min.js"></script>

    <style>
        body{
            margin-top:9px;
            margin-left:18px;
            overflow-x: hidden;
        }
        #tree{
            padding: 10px;
        }
        #menu {
            position: absolute;
            margin: 0;
            padding: 2px;
        }

    </style>
    <%--<script>/*Fixing iframe window.innerHeight 0 issue in Safari*/document.body.clientHeight;</script>--%>
    <script src="https://gw.alipayobjects.com/os/antv/assets/g2/3.0.9/g2.min.js"></script>
    <script src="https://gw.alipayobjects.com/os/antv/assets/data-set/0.8.7/data-set.min.js"></script>

</head>

<body>
    <div id="tree" class="layui-col-xs2 layui-card ztree" style="min-height: 100%"></div>
    <div  class="layui-col-xs10">
        <div id="mountNode"></div>
    </div>

    <div id="menu" class="layui-unselect layui-form-select layui-form-selected layui-col-xs1 layui-hide">
        <dl class="layui-anim layui-anim-upbit" style="top: 0">
            <dd id="add" ><i class="layui-icon">&#xe654;</i> <a href="#"> 添加组织</a></dd>
            <dd id="update"><i class="layui-icon">&#xe642;</i><a href="#"> 修改组织</a></dd>
            <dd id="del"><i class="layui-icon">&#xe640;</i><a href="#"> 删除组织</a></dd>
        </dl>
    </div>



</body>
<script src="${ctx}/js/depart.js"></script>
</html>
