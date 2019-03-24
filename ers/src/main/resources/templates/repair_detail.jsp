<%--
  Created by IntelliJ IDEA.
  User: lfm
  Date: 2018/3/31
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>申请详情</title>
    <%@include file="common.jspf"%>
    <link href="${ctx}/lib/bootstrap-star-rating/css/star-rating.css" media="all" rel="stylesheet" type="text/css" />

    <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
    <link href="${ctx}/lib/bootstrap-star-rating/themes/krajee-svg/theme.css" media="all" rel="stylesheet" type="text/css" />
    <script src="${ctx}/lib/bootstrap-star-rating/js/star-rating.js" type="text/javascript"></script>

    <!-- optionally if you need to use a theme, then include the theme file as mentioned below -->
    <script src="${ctx}/lib/bootstrap-star-rating/themes/krajee-svg/theme.js"></script>

    <!-- optionally if you need translation for your language then include locale file as mentioned below -->
    <script src="${ctx}/lib/bootstrap-star-rating/js/locales/zh.js"></script>
    <style>
        body{
            padding: 10px;
            overflow-x: hidden;
        }

    </style>
    <script>
        $(function () {
            $('.back').click((e)=>{
                window.history.back();
            })
        })
    </script>
</head>
<body>
<div class="layui-col-sm-offset11 layui-col-sm1">
    <a class="layui-btn layui-btn-lg layui-btn-radius layui-btn-normal back" title="返回"><i class="layui-icon">&#xe65c;</i></a>
</div>
<fieldset class="layui-elem-field layui-field-title" >
    <legend>报修个人信息</legend>
</fieldset>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input type="text" value="${detail.fullName}"
                       class="layui-input" readonly >

            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.phone}"
                       class="layui-input" style="color: #ff452c" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">所属机构：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.departName}"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
</div>
<fieldset class="layui-elem-field layui-field-title" >
    <legend>维修员个人信息</legend>
</fieldset>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input type="text" value="${detail.fullName2}"
                       class="layui-input" readonly >

            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.phone2}"
                       class="layui-input" style="color: #ff452c" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">所属机构：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.departName2}"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>报修详细信息</legend>
</fieldset>
<div class="layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">申请时间：</label>
            <div class="layui-input-inline">
                <input type="text" value="<fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd" />"
                       class="layui-input" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预约时间：</label>
            <div class="layui-input-inline">
                <input type="text" value="<fmt:formatDate value="${detail.expectTime}" pattern="yyyy-MM-dd" />"
                       class="layui-input" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">接受时间：</label>
            <div class="layui-input-inline">
                <input type="text" value="<fmt:formatDate value="${detail.expectTime}" pattern="yyyy-MM-dd" />"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">设备类型：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.equipName}"
                       class="layui-input" readonly >
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">损坏类型：</label>
            <div class="layui-input-inline">
                <input type="tel" value="${detail.infoName}"
                       class="layui-input" readonly >
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">详细地址：</label>
        <div class="layui-input-block">
            <div class="layui-col-sm6">
                <textarea class="layui-textarea" readonly>${detail.detailAddress}</textarea>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">问题描述：</label>
        <div class="layui-input-block">
            <div class="layui-col-sm6">
                <textarea class="layui-textarea" readonly>${detail.faultDescrip}</textarea>
            </div>
        </div>
    </div>
</div>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>报修时间线</legend>
    </fieldset>
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title"><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h3>
                <p>
                    提交报修申请
                </p>
            </div>
        </li>
    <c:if test="${detail.state == 1}">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title"><fmt:formatDate value="${detail.acceptTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h3>
                <p>维修员受理申请</p>
            </div>
        </li>
    </c:if>
    <c:if test="${detail.state == 3}">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title"><fmt:formatDate value="${detail.repairTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h3>
                <p>维修完成备注</p>
                <blockquote class="layui-elem-quote">
                    ${detail.repairNote}
                </blockquote>
            </div>
        </li>
    </c:if>
    <c:if test="${null != detail.evaluateNote}">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title"><fmt:formatDate value="${detail.evaluateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h3>
                <p>用户维修评价：</p>
                <div id="mark2" style="margin-top: 15px"></div>
                <blockquote class="layui-elem-quote">
                    ${detail.evaluateNote}
                </blockquote>
            </div>
                <script>
                    var mark2 =  $("#mark2")
                    mark2.rating({
                        min: 1,
                        max: 10,
                        step: 1,
                        size: 'xs',
                        readonly:true,
                        language: 'zh',
                        filledStar:'<i class="layui-icon layui-icon-rate-solid" style="color: #1E9FFF;"></i>',
                        emptyStar:'<i class="layui-icon layui-icon-rate" style="color: #1E9FFF;"></i>',
                        starCaptions: function (val) {
                            return val+'星'
                        }
                    });
                    mark2.on('rating:change', function(event, value, caption) {
                        $("input[name=mark]").val(value)
                    });
                    mark2.rating('update', ${detail.evaluateMark});
                </script>
        </li>
    </c:if>
    </ul>
</div>
</body>

</html>
