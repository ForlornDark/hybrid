$(function () {
    init_form()

    $("#depart").click((e)=> $("#tree").toggleClass('layui-hide'))
    init_tree()
})
function init_form() {
    var  form = layui.form
    var laydate = layui.laydate;
    form.render()

    form.verify({
        fullName: function (value) {
            if (value.length < 2 || value.length > 6) {
                return '姓名至少得2-6个字符啊';
            }
        }
    });
    form.on('submit(save)', function (target) {
        var data = target.field
        $.post(ctx+"/user/saveDetail",data,(re)=>{
            layer.msg(re.msg)
        })
        return false
    });


    var now = new Date()
    var max = new Date(now.getFullYear() - 10,now.getMonth(),now.getDay()).format('yyyy-MM-dd')
    laydate.render({
        elem:'#picker',
        max:max,
        // value:'1996-01-01'
    })
    //初始化文件上传
    var upload = layui.upload;
    var uploadInst = upload.render({
        elem: '#upload' //绑定元素
        ,url: ctx+'/user/photo' //上传接口
        ,accept:'images'
        ,size:512,
        field:'photo'
        ,bindAction:'#bt_upload'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            // console.log(obj)
            // obj.preview(function(index, file, result){
            //     console.log(file)
            //     $('#preview').attr('src', result); //图片链接（base64）
            // });
        }
        ,done: function(res){
            //上传完毕回调
            console.log(res)
            if (res.success){
                var url  =ctx+'/source/'+res.data
                $("#photo").attr('src',url)
                parent.$("#photo").attr('src',url)
            }
            layer.msg("上传完毕")
        }
        ,error: function(){
            //请求异常回调
            layer.msg("error")
        }
    });
}
function init_tree() {
    var setting = {
        async: {
            enable: true,
            url: ctx+"/depart/list"
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",

            }, key: {
                name: "name",
            }
        }, view: {
            dblClickExpand: false,
            showLine: true
        },
        callback: {
            onClick: zTreeOnClick
        }
    }
    $.fn.zTree.init($("#tree"), setting, []);
}
// 节点点击事件
function zTreeOnClick(event, treeId, treeNode) {
    console.log(treeNode)
    tool.form().fill("form",{
        departId:treeNode.id,
        departName:treeNode.name
    })
    $('#tree').toggleClass('layui-hide')
}