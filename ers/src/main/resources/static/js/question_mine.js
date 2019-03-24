var queryParam = {page:1,limit:15}
$(function () {
    $.get(ctx+"/question/mine",queryParam,function (re) {
        parseItem(re.data)
        initPage(re.count)
    })
    // //固定Bar
    // var util = layui.util
    // util.fixbar({
    //     bar1: '&#xe642;',
    //     bgcolor: '#009688',click: function(type){
    //         if(type === 'bar1'){
    //             location.href = ctx+'/question/edit';
    //         }
    //     }
    // });
})
function parseItem(data) {
    $("#fly-list").empty()
    data.forEach((i,v)=>{
        // console.log(i)
        var time = new Date(i.createTime).format('yyyy-MM-dd HH:mm:ss')
        var detail = ctx + '/question/detail_index/'+i.id
        var edit = ctx + '/question/edit?question='+i.id
        var html =
            `<li>
                        <a class="jie-title" href="${detail}">${i.title}</a>
                        <i>${time}</i>
                        <a class="mine-edit" href="${edit}">编辑</a>
                        <em>
        <i class="iconfont icon-pinglun1" title="回答"></i> ${i.commentCount}
        </em>
                    </li>`

        $("#fly-list").append(html)
    })

}
function initPage(count) {
    $("#count").html(count)
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

