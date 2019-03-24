//菜单加载

$(function() {
    init_tree()
    $("#add,#update,#del").click(function(){
        switch($(this).attr("id")){
            case "add":addOrg();break;
            case "update":updateOrg();break;
            case "del":del();break;
        }
        hideMenu();
    });
    init_chart()
})

function refresh(){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.reAsyncChildNodes(null, "refresh");
}

function getSelectedNode(){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    if (treeObj != null)
        return treeObj.getSelectedNodes()[0];
}
function register(index) {
    var form = layui.form;
    form.render()
    form.verify({
        name: function (value) {
            if (value.length < 2) {
                return '机构名至少得2个字符啊';
            }
        }
    });
    form.on('submit(save)', function (target) {
        var data = target.field
        data.statistic = data.statistic == undefined ? 0 : 1
        $.post(ctx+"/depart/save",data,(re)=>{
            layer.msg(re.msg)
            if (re.success){
                refresh()
                layer.close(index)
            }
        })
        return false
    });

}
function addOrg(){
    lay_open(ctx+"/depart/edit","添加",function (index) {
        var node = getSelectedNode()
        if(node != undefined){
            var obj = {code:node.code,parentId:node.id}
            console.log(obj)
            tool.form().fill("#depart-form",obj)
        }
      register(index)
    })
}

function updateOrg(){
    lay_open(ctx+"/depart/edit","修改",function (index) {
        var node = getSelectedNode();
        var obj = {id:node.id,name:node.name,note:node.note,code:node.code,statistic:node.statistic != 0}
        tool.form().fill("#depart-form",obj)
        // $("[name= 'statistic']:checkbox").attr('checked',node.statistic != 0)
        // console.log($("[name= 'note']").attr("type"))
        register(index)
    })
}
function del(){
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    var node = getSelectedNode();
    var nodeArray =  treeObj.transformToArray(node);
    var content = node.isParent ? "确定删除该结构(该下属机构也将删除)?":"确定删除该机构？";
    layer.confirm(content,{
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
        var item = {id:nodeArray[i].id,parentId:nodeArray[i].parentId,orgCode:nodeArray[i].orgCode};
        items.push(item);
    }
    $.postJson(ctx+"/depart/delete",JSON.stringify(items),(data)=>{
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
            url: ctx+"/depart/list"
            // , autoParam: ["id", "name"]
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
            onClick: zTreeOnClick,
            onRightClick: rightClick,
            onAsyncSuccess: zTreeOnAsyncSuccess
        }
    }
    $.fn.zTree.init($("#tree"), setting, []);
}
function zTreeOnAsyncSuccess() {
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.expandAll(true);
    // var nodes = treeObj.getNodes()
    // if (nodes.length > 0) {
    //     treeObj.selectNode(nodes[0])
    //     // refresh_table()
    // }
}
// 节点点击事件
function zTreeOnClick(event, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.expandNode(treeNode,true,false,true);
    // refresh_table()
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
    $("#tb_position").bootstrapTable({
        striped	:true,
        method:'POST',
        queryParams : _queryParams,
        search : true,
        pagination:true,
        sidePagination :"server",
        toolbar:"#toolbar",
        showRefresh:true,
        showColumns:true,
        columns : [ {
            checkbox : true,
        }, {
            field : 'id',
            title : 'ID',
        },{
            field : 'name',
            title : '职位名称'
        },{
            field : 'departmentName',
            title : '所属部门',
        },{
            field : 'createDate',
            title : '创建时间'
        }, {
            field : 'note',
            title : '备注'
        },{
            field : 'updateTime',
            title : '修改时间'
        },{
            field : 'operate',
            title : '操作',
            align : 'center',
            events : operateEvents,
            formatter : function(){
                return "<span id='edit' class='glyphicon glyphicon-pencil' aria-hidden='true' style='cursor: pointer;'></span>"
            }
        }]
    })
}
var _queryParams = function(params) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo")
    var id
    if(treeObj != null){
        var nodes =  treeObj.getSelectedNodes()
        id = nodes[0].id
    }
    var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit : params.limit, // 页面大小
        page : params.offset / params.limit + 1, // 页码
        search: {
            departmentId:id,
            name:params.search
        }
    };
    return JSON.stringify(temp);
}
/**
 * 修改点击
 */
var operateEvents = {
    'click #edit' : function(e, value, row, index) {
        loadModal("/ss/position/edit",function(){
            updateModalTitle(4)
            register("#positionForm","/ss/position/save")
            tool.form().fill("#positionForm",{id:row.id,name:row.name,
                departmentId:row.departmentId,
                departmentName:row.departmentName,
                note:row.note
            })
        })
    }

}
function init_chart() {
    $.get(ctx+'/statistic/departUserCount',(re)=>{
        const chart = new G2.Chart({
            container: 'mountNode',
            forceFit: true,
            height: window.innerHeight
        });
        chart.source(re);
        // chart.scale('name', {
        //     tickInterval: 20
        // });
        chart.scale('value', {
            min: 0
        });
        chart.tooltip({
            crosshairs: {
                type: 'line'
            }
        });
        chart.line().position('name*value');
        chart.point().position('name*value').size(4).shape('circle').style({
            stroke: '#fff',
            lineWidth: 1
        });
        chart.render();
    })
}