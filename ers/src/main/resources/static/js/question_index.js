var queryParam = {page:1,limit:15}
var types =["提问"]
$(function () {
    $.get(ctx+"/question/items",queryParam,function (re) {
        parseItem(re.data)
        initPage(re.count)
    })
    //固定Bar
    var util = layui.util
    util.fixbar({
        bar1: '&#xe642;',
        bgcolor: '#009688',click: function(type){
            if(type === 'bar1'){
                location.href = ctx+'/question/edit';
            }
        }
    });
})
function parseItem(data) {
    $(".fly-list").empty()
    data.forEach((i,v)=>{
        // console.log(i)
        var type = types[i.type]
        var photo = ctx+(i.photo == null ? '/img/hole.png':'/source/'+i.photo)
        var time = parseTime(i.createTime)
        var detail = ctx + '/question/detail_index/'+i.id
        var html = `<li>
    <a href="javascript:;" class="fly-avatar">
        <img src="${photo}" alt="${i.userName}">
        </a>
        <h2>
        <a class="layui-badge">${type}</a>
        <a href="${detail}">${i.title}</a>
    </h2>
    <div class="fly-list-info">
        <a href="javascript:;" link>
    <cite>${i.userName}</cite>
    </a>
    <span>${time}</span>
    <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
        <span class="fly-list-nums">
        <i class="iconfont icon-pinglun1" title="回答"></i> ${i.commentCount}
        </span>
        </div>
        <div class="fly-list-badge">
        </div>
        </li>`

        $(".fly-list").append(html)
    })

}
function initPage(count) {
    var laypage = layui.laypage
    //不显示首页尾页
    laypage.render({
        elem: 'laypage-main'
        ,count: count,
        limit:queryParam.limit
        ,first: false
        ,last: false,
        jump: function(obj,first){
            if (!first){
                reloadItem(obj.curr)
            }

        }
    });
}
function reloadItem(page) {
    $.get(ctx+"/question/items",{page:page,limit:queryParam.limit},function (re) {
        parseItem(re.data)
    })
}

