$(function () {
    init_form()
})
function init_form() {
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form;
        form.render()
        form.verify({
            username:[/[a-zA-Z0-9]{5,14}$/,"用户名必须由字母数字5-14位组成"],
            password: [/(.+){6,16}$/, '密码1必须6到16位'],
            password2:[/(.+){6,16}$/, '密码2必须6到16位']
        });
        //监听提交
        form.on('submit(register)', function(target){
            var data = target.field
            if(data.password != data.password2){
                layer.msg("两次密码不一样啊",{icon:5})
                return false
            }
            data.password = hex_md5(data.password)
            data.password2 = hex_md5(data.password2)
            $.post(ctx+"/user/register",data,(re)=>{
                if (re.success){
                    layer.msg(re.msg,{icon:6})
                    window.setTimeout(function(){
                        window.location.href=ctx+"/login"
                    },3000);
                }else{
                    layer.msg(re.msg,{icon:5})
                }
            })
            return false
        });
    })

}