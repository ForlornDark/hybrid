var form
var option = " <option value=''>直接选择或搜索选择</option>"
$(function () {
  init()
})
function init() {
    var laydate = layui.laydate;
    var now = new Date()
    var min = now.format('yyyy-MM-dd HH:mm:ss')
    laydate.render({
        elem:'#date',
        min:min,
        type:'datetime'
    })
    form = layui.form
    $.post(ctx+"/equip/list",(res)=>{
        var type = $("#equip_type")
        type.append(option)
        if(res.length > 0 ){
            load(res[0].id)
            res.forEach(v=>type.append(`<option value='${v.id}'>${v.name}</option>`))
        }
        form.render('select')
    })
    form.on('select(equip_type)', data=> load(data.value))
    form.on('select(fault)',data=>tool.form().fill('form',{faultId:data.value}))
    form.on('submit(save)', function(target){
        var data = target.field
        console.log(data)
        $.post(ctx+"/apply/save",data,(re)=>{
            layer.msg(re.msg)
            if (re.success){
                window.parent.location.href=ctx+"/index#/apply/index"
            }
        })
        return false
    });
}
function load(id) {
    var param = {equipId:id}
    $.post(ctx+"/equip/faultList",param,res=>{
        var fault = $('#fault')
        fault.empty()
       fault.append(option)
        if(res.data.length > 0){
            faultId = res.data[0].id
            res.data.forEach(v=>fault.append(`<option value='${v.id}'>${v.name}</option>`))
        }
        tool.form().fill('form',{faultId:faultId})
        form.render('select')
    })
}