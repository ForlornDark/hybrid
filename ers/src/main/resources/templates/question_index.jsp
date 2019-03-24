<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/5/16
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>提问首页</title>
    <%@ include file="common.jspf"%>
    <link rel="stylesheet" href="${ctx}/css/global.css">
</head>
<body style="margin: 0px;padding: 15px">
<div class="" style="overflow:hidden">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md11">
            <div class="fly-panel" style="margin-bottom: 0;">

                <div class="fly-panel-title fly-filter">
                    <a href="" class="layui-this">综合</a>
                    <span class="fly-mid"></span>
                    <a href="">未结</a>
                    <span class="fly-mid"></span>
                    <a href="">已结</a>
                    <span class="fly-mid"></span>
                    <a href="">精华</a>
                    <span class="fly-filter-right layui-hide-xs">
            <a href="" class="layui-this">按最新</a>
            <span class="fly-mid"></span>
            <a href="">按热议</a>
          </span>
                </div>
                <ul class="fly-list">
                    <%--<li>--%>
                        <%--<a href="user/home.html" class="fly-avatar">--%>
                            <%--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">--%>
                        <%--</a>--%>
                        <%--<h2>--%>
                            <%--<a class="layui-badge">提问</a>--%>
                            <%--<a href="detail.html">基于 layui 的极简社区页面模版</a>--%>
                        <%--</h2>--%>
                        <%--<div class="fly-list-info">--%>
                            <%--<a href="user/home.html" link>--%>
                                <%--<cite>贤心</cite>--%>
                                <%--<!----%>
                                <%--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>--%>
                                <%--<i class="layui-badge fly-badge-vip">VIP3</i>--%>
                                <%---->--%>
                            <%--</a>--%>
                            <%--<span>刚刚</span>--%>

                            <%--&lt;%&ndash;<span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>&ndash;%&gt;--%>
                            <%--<!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->--%>
                            <%--<span class="fly-list-nums">--%>
                <%--<i class="iconfont icon-pinglun1" title="回答"></i> 66--%>
              <%--</span>--%>
                        <%--</div>--%>
                        <%--<div class="fly-list-badge">--%>
                            <%--&lt;%&ndash;<span class="layui-badge layui-bg-black">置顶</span>&ndash;%&gt;--%>
                            <%--<!--<span class="layui-badge layui-bg-red">精帖</span>-->--%>
                        <%--</div>--%>
                    <%--</li>--%>

                </ul>

                <!-- <div class="fly-none">没有相关数据</div> -->

                <div style="text-align: center">
                    <div id="laypage-main"></div>
                </div>

            </div>
        </div>
        </div>
    </div>
</div>
<script src="${ctx}/js/question_index.js"></script>
</body>
</html>
