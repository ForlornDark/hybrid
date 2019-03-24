$(function () {
    $("#register").click(() => window.location.href = "/register")
    var form = layui.form;
    form.render()
    form.verify({
        username:[/[a-zA-Z0-9]{5,14}$/,"用户名必须由字母数字5-14位组成"],
        password: [/(.+){6,16}$/, '密码必须6到16位']
    });
    //监听提交
    form.on('submit(login)', function (target) {
        var data = target.field
        data.password = hex_md5(data.password)
        // $("input[name=password]").val(data.password)
        $.post(ctx + "/user/login", data, (re) => {
            layer.msg(re.msg)
            if (re.success) {
                window.location.href = "index"
            }
        })
        return false
    })
})