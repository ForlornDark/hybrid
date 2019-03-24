$(function () {
    init_table()
})
function init_table() {
    table = layui.table;
    table.render({
        elem: '#repair'
        , url: ctx + '/apply/repairList'
        , method: 'POST'
        , cellMinWidth: 60
        , cols: [[
            {field: 'fullName', title: '姓名'},
            {field: 'phone', title: '电话'},
            {field: 'departName', title: '所属机构'},
            {field: 'expectTime', title: '维修时间',templet:(row)=>time(row.expectTime)},
            {field: 'createTime', title: '申请时间',templet:(row)=>time(row.createTime)},
            {field: 'equipName', title: '设备类型'},
            {field: 'infoName', title: '损坏类型'},
            {field: 'state', title: '状态',templet:(row)=>  state(row)},
            {field:'evaluateMark',title:'评分', align: 'center',templet:(row)=>row.evaluateMark == null? '--':row.evaluateMark+'分'},
            {title: '操作', align: 'center', toolbar: "#applyTool"}
        ]]
        , page: true
    });
    table.on('tool(operate)',(obj)=>{
        var data = obj.data
        if (obj.event == 'done'){
            done(data)
        }else if (obj.event == 'evaluate'){
            evaluate(data)
        }else if(obj.event=='detail'){
            window.location.href = ctx+`/apply/repairDetail?applyId=${data.id}`
        }
    })
}
function time(t) {
    return t == null?"-":"<div>{0}</div>".format(new Date(t).format('yyyy-MM-dd'))
}
function state(row) {
    var state = row.state
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
    table.reload("repair",config)
}
function done(data) {
    layer.prompt({title: '完成备注', formType: 2}, function(text, index){
        $.post(ctx+"/apply/done",{applyId:data.id,note:text},re=>{
            layer.msg(re.msg)
            if(re.success){
                layer.close(index)
                reload({})
            }
        })
    });
}
function evaluate(data) {
    lay_open(ctx+'/apply/evaluate_edit','评价',function (index) {
        var form = layui.form
        form.on('submit(save)',(d)=>{
            $.post(ctx+'/apply/evaluate',d.field,re=>{
                layer.msg(re.msg)
                if(re.success){
                    layer.close(index)
                    reload({})
                }
            })
            return false
        })
        tool.form().fill('#evaluate',{applyId:data.id})
    })
}