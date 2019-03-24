$(function () {
    init_table()
})
function init_table() {
    table = layui.table;
    table.render({
        elem: '#apply'
        , url: ctx + '/apply/applyList'
        , method: 'POST'
        , cellMinWidth: 60
        , cols: [[
            {field: 'fullName', title: '姓名'},
            {field: 'phone', title: '电话'},
            {field: 'departName', title: '所属机构'},
            // {field: 'detailAddress', title: '详细地址'},
            {field: 'expectTime', title: '维修时间',templet:(row)=>time(row.expectTime)},
            {field: 'createTime', title: '申请时间',templet:(row)=>time(row.expectTime)},
            {field: 'detailAddress', title: '详细地址'},
            {field: 'equipName', title: '设备类型'},
            {field: 'infoName', title: '损坏类型'},
            // {field: 'faultDescrip', title: '损坏描述'},
            {field: 'state', title: '状态',templet:(row)=>  state(row.state)},
            {title: '操作', align: 'center', width:120,toolbar: "#applyTool"}
        ]]
        , page: true
    });
    table.on('tool(operate)',(obj)=>{
        var data = obj.data
        console.log(data)
        if (obj.event == 'unHandle'){
            handle('是否不予处理此申请？','/apply/unHandle',data)
        }else if (obj.event == 'accept'){
            handle('是否接受此任务？','/apply/accept',data)
        }else if(obj.event == 'delete'){
            handle('是否撤销此申请?','/apply/delete',data)
        }else if(obj.event=='detail'){
            window.location.href = ctx+`/apply/applyDetail?applyId=${data.id}`
        }
    })
    // table.render({
    //     elem: '#repair',
    //     url: ctx + '/apply/repairList',
    //     method: 'POST',
    //     cellMinWidth: 60,
    //     cols: [[
    //         {title: 'line', width: 60, templet: "<div>{{ d.LAY_INDEX }}</div>"}
    //         , {field: 'name', title: '用户名'}
    //         , {field: 'fullName', title: '姓名'}
    //         , {field: 'sex', title: '性别', templet: "<div>{{d.sex?'女':'男'}}</div>"}
    //         , {field: 'phone', title: '手机号'}
    //         , {field: 'mail', title: '邮箱'}
    //         , {field: 'departName', title: '所属机构'}
    //         , {field: 'birthday', title: '生日', templet: (row) => time(row.birthday)}
    //         , {field: 'createDate', title: '创建时间', templet: (row) => time(row.createDate)} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
    //         , {field: 'note', title: '签名'}
    //         , {field: 'roleName', title: '角色'}
    //         , {title: '操作', align: 'center', toolbar: "#rowTool"}
    //     ]],
    //     page: true
    // });
}
function time(t) {
    return t == null?"-":"<div>{0}</div>".format(new Date(t).format('yyyy-MM-dd'))
}
function state(state) {
    if(state == 0){
       return '<div style="color: #ff4444">未处理</div>'
    }else if(state == 1){
        return '<div style="color: #4444ff">正在处理</div>'
    }else if(state == 2){
        return '<div style="color: #FFB800">不予解决</div>'
    }else{
        return '<div style="color: #44ff44">已完成</div>'

    }
}
function handle(title,url,data) {
    layer.confirm(title, {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.post(ctx+url,{applyId:data.id},re=>{
            layer.msg(re.msg)
            if(re.success){
                reload()
            }
        })
    })
}
function reload(config) {
    table = layui.table
    table.reload("apply",config)
}
