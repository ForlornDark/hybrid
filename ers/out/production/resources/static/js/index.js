$(function(){
    //菜单点击
    reload()
    $(".jump").on('click',function(){
        var url = $(this).attr('data-jump');
        changeUrl(url)
        // return false;
    });
    window.onhashchange = function() {
        console.log("changed")
        reload()
    }
});
function changeUrl(url){
    var u = window.location.href;
    //因为每次获取的链接中都有之前的旧锚点，
    //所以需要把#之后的旧锚点去掉再来加新的锚点（即传入的url参数）
    var end = u.indexOf("#");
    var rurl = u.substring(0,end);
    //设置新的锚点
    window.location.href = rurl + "#" + url;
}
function reload(){
    var hash = location.hash;
    $('.layui-nav-item').removeClass('layui-nav-itemed')
    if(hash.length > 0){
        var url = hash.substring(1,hash.length);
        var t
        $(".jump").each((i,v)=>{
            t = $(v)
            if(url == t.attr('data-jump')){
                t.parents('.layui-nav-item').addClass('layui-nav-itemed')
                t.addClass('layui-this')
            }else{
            t.removeClass('layui-this')}
        })
        $("#content").attr("src", url);
    }
}