//菜单加载
var id
$(function() {
     init_table()

    init_tree()
    $("#add,#update,#del").click(function(){
        switch($(this).attr("id")){
            case "add":add();break;
            case "update":update();break;
            case "del":del();break;
        }
        hideMenu();
    });

})

function refresh(){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.reAsyncChildNodes(null, "refresh");
}

function getSelectedNode(selector){
    var treeObj = (typeof selector) ?  $.fn.zTree.getZTreeObj("tree") :  $.fn.zTree.getZTreeObj(getSelectedNode);
    if (treeObj != null)
        return treeObj.getSelectedNodes()[0];
}

function add(){
    lay_open(ctx+"/equip/type_edit","添加",function (index) {
        register(index)
    })
}
function register(index) {
    var form = layui.form;
    form.render()
    form.verify({
        name: function (value) {
            if (value.length < 2) {
                return '设备类型至少得2个字符啊';
            }
        }
    });
    form.on('submit(save)', function (target) {
        var data = target.field
        console.log(data)
        $.post(ctx+"/equip/save",data,(re)=>{
            layer.msg(re.msg)
            if (re.success){
                refresh()
                layer.close(index)
            }
        })
        return false
    });

}
function update(){
    lay_open(ctx+"/equip/type_edit","修改",function (index) {
        var node = getSelectedNode();
        var obj = {id:node.id,name:node.name,note:node.note}
        tool.form().fill("#form",obj)
        register(index)
    })
}
function del(){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var node = getSelectedNode();
    var nodeArray =  treeObj.transformToArray(node);
    layer.confirm( "确定删除该设备类型?",{
        icon:5,
        btn:['是','否']
    },function(){
        delPost(nodeArray)
    });
}
function delPost(nodeArray){
    var items = [];
    var item = {};
    for(var i = 0 ;i < nodeArray.length;i++){
        var item = {id:nodeArray[i].id};
        items.push(item);
    }
    $.postJson(ctx+"/equip/delete",JSON.stringify(items),(data)=>{
        layer.msg(data.msg)
        if(data.success){
            refresh()
        }
    })
}

// 加载树菜单
function init_tree() {
    var setting = {
        async: {
            enable: true,
            url: ctx+"/equip/list"
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                // pIdKey: "parentId",

            }, key: {
                name: "name",
            }
        }, view: {
            dblClickExpand: false,
            showLine: true
        },
        callback: {
            onClick: zTreeOnClick,
            onRightClick: rightClick,
            onAsyncSuccess: zTreeOnAsyncSuccess
        }
    }
    $.fn.zTree.init($("#tree"), setting, null);
}
function zTreeOnAsyncSuccess() {
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.expandAll(true);
    var nodes = treeObj.getNodes()
    if (nodes.length > 0) {
        treeObj.selectNode(nodes[0])
        id = nodes[0].id
        refresh_table(nodes[0].id)
    }
}
// 节点点击事件
function zTreeOnClick(event, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var node = getSelectedNode()
    if (node != undefined){
        id = node.id
        refresh_table(id)
    }
}
function rightClick(event, treeId, treeNode){
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var menuType = 0;
    if(!treeNode){
        zTree.cancelSelectedNode();
    }else{
        zTree.selectNode(treeNode);
        menuType = 1;
    }
    showMenu(menuType,event.clientX - 18,event.clientY - 9,treeNode);
}
/**
 *
 * @param type 菜单类型 0 为创建父节点 1为子节点管理
 * @param x
 * @param y
 * @param treeNode
 * @returns
 */
function showMenu(type,x,y,treeNode){
    $("#menu").removeClass("layui-hide")
    if(type == 0){
        $("#update").hide();
        $("#del").hide();
    }else if(type == 1){
        $("#add").show()
        $("#update").show();
        $("#del").show();
    }
    y += document.body.scrollTop -5;
    x += document.body.scrollLeft- 5;
    $("#menu").css({"top":y+"px", "left":x+"px"});
    $("#menu").mouseleave(()=>hideMenu())
}
function hideMenu(){
    $("#menu").addClass("layui-hide");
}
/**
 * 初始化表格数据
 * @returns
 */
function init_table(url){
    var table = layui.table
    table.render({
        elem: '#table',
        url:"",
        method:'post',
        page:false,
        cols: [[ //表头
            {type:'checkbox'},
            {field: 'id', title: 'ID',sort: true,align:'center'}
            ,{field: 'name', title: '问题名称',align:'center'}
            ,{field: 'createTime', title: '创建时间',align:'center',templet:"<div>{{new Date(d.createTime).format('yyyy-MM-dd HH:mm:ss')}}</div>"}
            ,{field: 'updateTime', title: '更新时间',align:'center',templet:(row)=>{
                return row.updateTime == null?"-":"<div>{0}</div>".format(new Date(row.updateTime).format('yyyy-MM-dd HH:mm:ss'))
            }}
            ,{field: 'note', title: '解决方式',align:'center'}
            ,{title:'操作',align:'center',toolbar:'#operate'}
        ]]
    })
    table.on('tool(table)',(obj)=>{
        var data = obj.data
        if (obj.event == 'update'){
            lay_open(ctx+"/equip/fault_edit","编辑",(index)=>{
                register2(index)
                tool.form().fill("form",{equipId:data.equipId,id:data.id,name:data.name,note:data.note})
            })
        }
    })
    $("#bt_add").click(()=>{
        var node = getSelectedNode()
        if( node == undefined){
            layer.msg("请选择设备类型",{icon:5})
            return
        }
        lay_open(ctx+"/equip/fault_edit","添加",(index)=>{
            register2(index)
            tool.form().fill("form",{equipId:node.id})
        })
    })
    $("#bt_del").click(()=>{
        var check = table.checkStatus('table');
        var param = []
        check.data.forEach((v)=>{
            param.push(v)
        })
        if (param.length == 0){
            layer.msg("请选择需要删除的行",{icon:5})
            return
        }
        $.postJson(ctx+"/equip/faultDelete",JSON.stringify(param),(re)=>{
            layer.msg(re.msg)
            if (re.success){
                refresh_table(id)
            }
        })
    })
}
function register2(index) {
    var form = layui.form;
    form.render()
    form.verify({
        name: function (value) {
            if (value.length < 2) {
                return '名称至少得2个字符啊';
            }
        }
    });
    form.on('submit(save)', function (target) {
        var data = target.field
        $.post(ctx+"/equip/faultSave",data,(re)=>{
            layer.msg(re.msg)
            if (re.success){
                refresh_table(data.equipId)
                layer.close(index)
            }
        })
        return false
    });

}
function refresh_table(id) {
    var table = layui.table
    table.reload('table',{
        url:ctx+'/equip/faultList',
        where:{
            equipId:id
        }
    })
}