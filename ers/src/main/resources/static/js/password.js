$(function () {
    var form = layui.form
    form.verify({
        oldPwd:[/(.+){6,16}$/, '旧密码必须6到16位'],
        pwd1:[/(.+){6,16}$/, '密码1必须6到16位'],
        pwd2:[/(.+){6,16}$/, '密码2必须6到16位']
    });
    form.on('submit(save)', function (target) {
        var data = target.field
        if (data.pwd1 != data.pwd2){
            layer.msg("确认密码不一致啊",{icon:5})
        }else {
                data.oldPwd = hex_md5(data.oldPwd)
                data.pwd1 = hex_md5(data.pwd1)
                data.pwd2 = hex_md5(data.pwd2)
                $.post(ctx+"/user/editPwd",data,(re)=>{
                    layer.msg(re.msg)
                    if (re.success){
                        window.location.href = ctx+"/login"
                    }
                })
            }
        return false
    })
})