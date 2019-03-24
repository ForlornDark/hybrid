$(function () {
    initTable()
    $("#del").click(()=>{
        var checks = table.checkStatus('table');
        del(checks.data)
    })
    $("#add").click(()=>{
        lay_open(ctx+'/user/edit',"添加",(index)=>{
            init_form(index)
        })
    })

})

/**
 * 初始化表格
 */
var table
function initTable() {
    layui.use('table', function(){
        table = layui.table;
        table.render({
            elem: '#table'
            ,url:ctx+'/user/list'
            ,method:'POST'
            ,cellMinWidth: 60
            ,cols: [[
                {type:'checkbox'},
                {title: 'line',width:60,templet:"<div>{{ d.LAY_INDEX }}</div>"}
                ,{field:'name',  title: '用户名'}
                ,{field:'fullName',title:'姓名'}
                ,{field:'sex', title: '性别',templet:"<div>{{d.sex?'女':'男'}}</div>"}
                ,{field:'phone', title: '手机号'}
                ,{field:'mail', title: '邮箱'}
                ,{field:'departName', title: '所属机构'}
                ,{field:'birthday', title: '生日',templet:(row)=>time(row.birthday)}
                ,{field:'createDate', title: '创建时间',templet:(row)=>time(row.createDate)} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'note', title: '签名'}
                ,{field:'roleName', title: '角色'  }
                ,{title: '操作',align:'center', toolbar:"#rowTool"}
            ]]
            ,page:true
        });
        table.on('tool(operate)',(obj)=>{
            var data = obj.data
            if (obj.event == 'edit'){
                lay_open(ctx+'/user/edit','编辑',(index)=>{
                    console.log(data)
                    tool.form().fill('form',{
                        id:data.id,
                        name:data.name,
                        phone:data.phone,
                        sex:data.sex,
                        roleId:data.roleId
                    })
                    init_form(index)
                    $('#username').attr("disabled",true);
                })
            }
        })
    })
}
function time(t) {
    return t == null?"-":"<div>{0}</div>".format(new Date(t).format('yyyy-MM-dd'))
}

    /**
     * 删除操作
     */
    function del(list) {
        console.log(list)
        var bt = {btn: ['确定','取消'] }
        var param = []
        list.forEach((e)=>param.push(e.id))
        layer.confirm('确定删除用户？',bt , (index)=>{
            $.post("user/delete",{"list":""+param.join(",")},(data=>{
                console.log(data)
                if(data.success){
                    reload({})
                }
                layer.msg(data.msg)
            }))
    }
    )}
    function reload(config) {
         table.reload("table",config)
    }
    function init_form(index) {
        var form = layui.form
        form.verify({
            name: function (value) {
                if(value.length < 5){
                    return '用户名至少得5个字符啊';
                }
            }
        });
        form.on('submit(save)', function (target) {
            var data = target.field
            console.log(data)
            $.post(ctx+"/user/saveUser",data,(re)=>{
                layer.msg(re.msg)
                if (re.success){
                    reload({})
                    layer.close(index)
                }
            })
            return false
        });
        form.render()
    }