
layui.define('jquery',function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var $ = layui.jquery;
    var obj = {
        canshu : {
            // 选择器，创建全部星元素存放的选择器
            xuanzeqi: $('.mymt-xing'),
            // 表单name，表单提交name得值
            namezhi: 'xingshuliang',
            // 单星宽度
            kuandu: 23,
            // 单星高度
            gaodu: 22,
            // 单星之间右外边局
            jianju: 5,
            // 总星个数
            geshu: 5,
            // 未选星样式
            weixuan: 'images/xkuang.png',
            // 全选星样式
            quanxuan: 'images/xquan.png',
            // 半选星样式
            banxuan: 'images/xban.png',
            // 鼠标经过手势样式，更多样式参考CSS cursor 属性
            shoushi: 'pointer',
        },
        chuangjian : function(xuanzeqi){

            var canshu = this.canshu;
            var shezhiyangshi = this.shezhiyangshi;

            // 选择器为空，使用默认选择器
            if(xuanzeqi != ''){
                canshu.xuanzeqi = $(xuanzeqi);
            }

            // 初始化全部星元素存放的元素
            canshu.xuanzeqi.css({'width':canshu.kuandu * canshu.geshu + (canshu.jianju * canshu.geshu - 1) +'px', 'position': 'relative', 'cursor': canshu.shoushi});

            // 追加创建单星元素
            for(var i = 0; i < canshu.geshu; i++){
                canshu.xuanzeqi.append('<span></span>');
            }

            // 初始化单星样式。
            canshu.xuanzeqi.find('span').css({
                'margin-right': canshu.jianju + 'px',
                'display': 'inline-block',
                'background': 'url('+canshu.weixuan+')',
                'width': canshu.kuandu + 'px',
                'height': canshu.gaodu + 'px',
                'position': 'relative',
                'background-size': canshu.kuandu+'px '+canshu.gaodu+'px',
            });
            // 最后一颗单星去掉右外边距。
            canshu.xuanzeqi.find('span:last').css({
                'margin-right': '0px',
            });

            // 添加name隐藏表单
            canshu.xuanzeqi.append('<input type="text" name="'+canshu.namezhi+'" style="display:none"/>');


            // 鼠标离开事件
            canshu.xuanzeqi.mouseleave(function(e){

                var xinfenshu = $(this).find('input').val();

                // 没有设置星星分数
                if(xinfenshu == ''){

                    $(this).find('span').css({'background': 'url('+canshu.weixuan+')','background-size': canshu.kuandu+'px '+canshu.gaodu+'px',});
                }
                // 设置了星星分数
                if(xinfenshu != ''){
                    // 更新星级样式
                    shezhiyangshi($(this),parseFloat(xinfenshu),canshu);
                }


                // 鼠标左侧划出，清除样式
                var zuobiao = e.pageX - $(this).offset().left;
                if(zuobiao < 0){
                    $(this).find('span').css({'background': 'url('+canshu.weixuan+')','background-size': canshu.kuandu+'px '+canshu.gaodu+'px',});
                }
            });

            // 鼠标点击事件
            canshu.xuanzeqi.click(function(e){

                // 鼠标坐标
                var zuobiao = e.pageX - $(this).offset().left;
                // 计算鼠标当前所在星级范围
                var quanxing = parseInt(zuobiao/(canshu.kuandu+canshu.jianju));
                var banxing = zuobiao%(canshu.kuandu+canshu.jianju);
                if(banxing <= canshu.kuandu/2-1){
                    banxing = 0.5;
                }else{
                    banxing = 1;
                }

                // 设置星星分数
                $(this).find('input').val(quanxing+banxing);
                // 更新星级样式
                shezhiyangshi($(this),quanxing+banxing,canshu);
            })

            // 鼠标经过事件
            canshu.xuanzeqi.mousemove(function(e){
                // 鼠标坐标
                var zuobiao = e.pageX - $(this).offset().left;
                // 计算鼠标当前所在星级范围
                var quanxing = parseInt(zuobiao/(canshu.kuandu+canshu.jianju));
                var banxing = zuobiao%(canshu.kuandu+canshu.jianju);
                if(banxing <= canshu.kuandu/2-1){
                    banxing = 0.5;
                }else{
                    banxing = 1;
                }
                shezhiyangshi($(this),quanxing+banxing,canshu);
            })
        },
        // 动态设置分数
        shezhifenshu : function(xuanzeqi,xingshuliang){
            // 更新样式
            this.shezhiyangshi($(xuanzeqi),xingshuliang,this.canshu);
            // 设置星星分数
            $(xuanzeqi).find('input').val(xingshuliang);
        },
        // 动态获取分数
        huoqufenshu : function(xuanzeqi){

            // 获取星星分数
            return $(xuanzeqi).find('input').val();
        },
        // 设置星星样式
        shezhiyangshi : function(xuanzeqi,xingshuliang,canshu){

            var geshihua = xingshuliang.toFixed(1).split('.');
            var quanxing = geshihua [0];
            var banxing = geshihua [1]/10;
            // 设置前初始化星星样式
            xuanzeqi.find('span').css({'background': 'url('+canshu.weixuan+')','background-size': canshu.kuandu+'px '+canshu.gaodu+'px',});

            // 设置当前鼠标前星星样式为全
            xuanzeqi.find('span:lt('+(quanxing)+')').css({'background': 'url('+canshu.quanxuan+')','background-size': canshu.kuandu+'px '+canshu.gaodu+'px'});

            // 设置当前鼠标所在位置星星样式
            if(banxing != 0 ){   // 如果半星为0则忽略

                if(banxing > 0 && banxing < 0.6){  // 半星大于0小于0.6为半星
                    xuanzeqi.find('span:eq('+(quanxing)+')').css({'background': 'url('+canshu.banxuan+')','background-size': canshu.kuandu+'px '+canshu.gaodu+'px'});
                }else{ // 半星大于0小于0.6为全星
                    xuanzeqi.find('span:eq('+(quanxing)+')').css({'background': 'url('+canshu.quanxuan+')','background-size': canshu.kuandu+'px '+canshu.gaodu+'px'});
                }
            }
        }
    };
    //输出test接口
    exports('xingji', obj);
});