//jquery增加插件
(function($) {
	$.extend({
		postJson : function(url, data, back1) {
			var defaults = {
				url : url,
				type : "POST",
				contentType : 'application/json; charset=utf-8',
				async : true,
				dataType : "JSON",
				data : data,
				success : back1
			}
			$.ajax(defaults)
		}
	})
})(jQuery)
//封装部分方法
function $package(ns) {
	if (typeof (ns) != "string")
		return;
	ns = ns.split(".");
	var o, ni;
	for ( var i = 0, len = ns.length; i < len, ni = ns[i]; i++) {
		try {
			o = (o ? (o[ni] = o[ni] || {}) : (eval(ni + "=" + ni + "||{}")));
		} catch (e) {
			o = eval(ni + "={}");
		}
	}
}
$package("tool.form");
tool.form = function() {
	var _this;
	_this = {
		/**
		 * 重置表单方法 follow :String 表单对象id
		 */
		reset : function(form) {
			var inputs = $(form).find("[name]");
			$.each(inputs, function(i, n) {
				var _this = $(this);
				if (_this.attr("name") != "_token"&&_this.attr("reset")==undefined) {
					_this.val("");
				}
			})
		},
		/**
		 * 表单填充 follow 表单对象id obj 需要填的对象
		 */
		fill : function(form, obj) {
			$.each(obj, function(i, n) {
				type($("[name="+i+"]",form),n)
			})
		}
	}
	function type(obj,n) {
        switch ( obj.attr('type')){
            case 'text':
            case 'hidden':
                obj.val(n)
                break
            case 'checkbox':
                obj.attr('checked',n)
                break
            case 'radio':
                var t
                for(var i = 0;i < obj.length;i++){
                    t = $(obj[i])
                    if(t.val() == String(n)){
                        t.attr('checked',true)
                    }
                }
                break
            default :
                   obj.val(n)

        }
    }
	return _this;
}

String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg= new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
Date.prototype.format=function(fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
        "H+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    var week = {
        "0" : "/u65e5",
        "1" : "/u4e00",
        "2" : "/u4e8c",
        "3" : "/u4e09",
        "4" : "/u56db",
        "5" : "/u4e94",
        "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

//JavaScript代码区域

function title(index) {
    var items =  $(".layui-layout-left > .layui-nav-item")
    $(items[index]).addClass("layui-this")
}
function lay_open(url,title,handle) {
    $.get(url,function (re) {
        var index = layer.open({
            title:title,
            type: 1,
            scrollbar: false,
            content: re
        });
        handle(index)
    })
}
function parseTime(time) {
    var d = new Date(time)
    var n = new Date()
    var delt = n.getTime() - d.getTime()
    if(delt > 3 * 24 * 60 * 60 * 1000){
        return d.format('yyyy-MM-dd')
    }else if(delt >= 24 * 60 * 60 * 1000){
        return parseInt(delt / (24 * 60 * 60 * 1000)) + '天前'
    }else if(delt >= 60 * 60 * 1000){
        return parseInt(delt / (60 * 60 * 1000)) + "小时前"
    }else if(delt >= 60 * 1000){
        return parseInt(delt/(60*1000)) + "分钟前"
    }else{
        return "刚刚"
    }
}