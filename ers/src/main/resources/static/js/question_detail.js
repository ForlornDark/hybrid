$(function () {
    layui.config({
        version: "3.0.0"
        ,base: ctx+'/js/mods/'
    }).extend({
        fly: 'index'
    }).use(['fly', 'face'], function(){
        var $ = layui.$
            ,fly = layui.fly;
        $('.detail-body').each(function(){
            var othis = $(this), html = othis.html();
            othis.html(fly.content(html));
        });
    });
    var util = layui.util
    util.fixbar({
        // bar1: '&#xe642;',
        bar2:'&#xe65c',
        bgcolor: '#009688',click: function(type){
            if(type == 'bar2'){
                window.history.back();
            }
        }
    });
    var form = layui.form
    form.on('submit(save)',function (t) {
        $.post(ctx+'/question/comment',t.field,function (re) {
            if (re.success){
                location.reload()
            }else {
                layer.msg(re.msg)
            }
        })
        return false;
    })
    $.get(ctx+'/question/comment_list',{question:$("#question").val(),page:0,limit:0},function (re) {
        parse(re.data,true)
    })
    //点击事件
    var c = $("#jieda")
    c.on('click','.jieda-reply span',function () {
        var othis = $(this), type = othis.attr('type');
        reply[type].call(this, othis.parents('li'));
    })

})
function parse(data,first) {
    if (data.length > 0){
        if (first){
            var c = $("#jieda")
            c.empty()
            data.forEach(v=>{
                c.append(getHtml(v))
            })
        }
    }
}
function getHtml(d) {
    var photo = ctx+(d.photo == null ? '/img/hole.png':'/source/'+d.photo)
    var time = parseTime(d.createTime)
    var content = layui.fly.content(d.content)
 var html = `<li data-id="${d.id}" class="jieda-daan">
                        <!--<a name="item-1111111111"></a>-->
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="${photo}" alt="">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>${d.userName}</cite>
                                    <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                                    <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                                </a>
                                <!--<span>(楼主)</span>-->
                                <!--
                                <span style="color:#5FB878">(管理员)</span>
                                <span style="color:#FF9E3F">（社区之光）</span>
                                <span style="color:#999">（该号已被封）</span>
                                -->
                            </div>
                            <div class="detail-hits">
                                <span>${time}</span>
                            </div>
                            <!--<i class="iconfont icon-caina" title="最佳答案"></i>-->
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p>${content}</p>
                        </div>
                        <div class="jieda-reply">
              <span class="jieda-zan ${d.agreed?'zanok':'zan'}" type="agree">
                <i class="iconfont icon-zan"></i>
                <em>${d.agreeCount}</em>
              </span>
                            <!--<span type="reply">-->
                <!--<i class="iconfont icon-svgmoban53"></i>-->
                <!--回复-->
              <!--</span>-->
                            <!--<div class="jieda-admin">-->
                                <!--<span type="edit">编辑</span>-->
                                <!--<span type="del">删除</span>-->
                                <!--&lt;!&ndash; <span class="jieda-accept" type="accept">采纳</span> &ndash;&gt;-->
                            <!--</div>-->
                        </div>
                    </li>`
    return html
}
var reply= {
    agree: function(li){ //赞
        var othis = $(this), ok = othis.hasClass('zanok');
        if (ok){
            return
        }
        var q = $("#question").val()
        var id = li.data('id')
        $.post(ctx+'/question/agree',{question:q, comment:id},function (re) {
            if (re.success){
                var zans = othis.find('em').html()|0;
                othis[ok ? 'removeClass' : 'addClass']('zanok');
                othis.find('em').html(ok ? (--zans) : (++zans));
            }else {
                layer.msg('点赞失败');
            }
        })
    }
}