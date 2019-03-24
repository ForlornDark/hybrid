var chart1,chart2,chart3

$(function () {
    init_chart1()
    init_chart2()
    init_chart3()
})
function init_chart1() {
    $.get(ctx+"/statistic/repairCount",(re)=>{
        const ds = new DataSet();
        const dv = ds.createView().source(re).transform({
                type: 'sort',
                callback(a, b) { // 排序依据，和原生js的排序callback一致
                    return a.value - b.value;
                }
            });
        chart1 = new G2.Chart({
            container: 'chart1',
            forceFit: true,
            height:window.innerHeight * 0.4,
            padding: 'auto'
        });
        chart1.source(dv);
        chart1.axis('name', {
            label: {
                offset: 3
            }
        });
        chart1.coord().transpose();
        chart1.legend(false)
        chart1.interval().position('name*value').color('name')
        chart1.render();
    })
}
function init_chart2() {

    $.get(ctx+"/statistic/markOrder",(re)=>{
        const ds = new DataSet();
        const dv = ds.createView().source(re).transform({
            type: 'sort',
            callback(a, b) { // 排序依据，和原生js的排序callback一致
                return a.value - b.value;
            }
        });

        chart2 = new G2.Chart({
            container: 'chart2',
            forceFit: true,
            height:window.innerHeight * 0.4,
            padding: 'auto'
        });
        chart2.source(dv);
        // chart2.source(dv, {
        //     percent: {
        //         formatter: val => {
        //             val = (val * 100).toFixed(2) + '%';
        //             return val.toFixed(2);
        //         }
        //     }
        // });
        chart2.axis('name', {
            label: {
                offset: 3
            }
        });
        chart2.coord().transpose();
        chart2.legend(false)
        chart2.interval().position('name*value').color('name');
        chart2.render();
    })
}

function init_chart3() {

    $.get(ctx+'/statistic/equipTypeCount',(re)=>{
        const { DataView } = DataSet;
        const dv3 = new DataView();
        dv3.source(re).transform({
            type: 'percent',
            field: 'value',
            dimension: 'name',
            as: 'percent'
        });
        chart3 = new G2.Chart({
            container: 'chart3',
            forceFit: true,
            height: window.innerHeight * 0.8,
            padding:'auto'
        });
        chart3.source(dv3, {
            percent: {
                formatter: val => {
                    val = (val * 100).toFixed(2) + '%';
                    return val;
                }
            }
        });
        chart3.coord('theta', {
            radius: 0.75
        });
        chart3.tooltip({
            showTitle: false,
            itemTpl: '<li><span style="background-color:{color};" class="g2-tooltip-marker"></span>{name}: {value}</li>'
        });
        chart3.intervalStack()
            .position('percent')
            .color('name')
            .label('percent', {
                formatter: (val, item) => {
                    console.log(item)
                    return item.point.name + ': ' + val;
                }
            })
            .tooltip('name*percent', (name, percent) => {
                percent = (percent * 100).toFixed(2) + '%';
                return {
                    name: name,
                    value: percent
                };
            })
            .style({
                lineWidth: 1,
                stroke: '#fff'
            });
        chart3.render();
    })
}