layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns = table.render({
        elem: '#saleChanceList', // 表格绑定的ID
        url : ctx + '/subjectController/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "saleChanceListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'subName', title: '课程名',align:"center"},
            {field: 'userId', title: '教师编号',  align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '修改时间', align:'center'},
            {field: 'state', title: '状态', align:'center',templet:function(d){return formatterState(d.state);}},
            {field: 'operate',title: '操作', templet:function (d){
                return aa(d.state)
                },fixed:"right",align:"center", minWidth:150}
        ]]
    });






    function aa(state){
        if(state==1) {
            return "";
        }
        if(state==0){
            return '<button class="layui-btn layui-btn-xs" id="edit" lay-event="edit" name="edit">评价</button>';
        }
    }













    /**
     * 格式化分配状态
     * 0 - 未分配
     * 1 - 已分配
     * 其他 - 未知
     * @param state
     * @returns {string}
     */
     function formatterState(state){
        if(state==0) {
            return "<div style='color: yellow'>未评价</div>";
        } else if(state==1) {
            return "<div style='color: green'>已评价</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }

    }











    /*实现搜索功能，页面重载*/
    $(".search_btn").click(function(){
        table.reload("saleChanceListTable",{
            where: {
                //设定异步数据接口的额外参数，任意设
                subName: $("#subName").val(),
                state: $("#state").val()
            },
            page: {
                //重新从第 1 页开始
                curr: 1
            }
        });
    });













    function openAddSubjectDialog(subId) {
        var title = "<h2>评价</h2>";
        var url = ctx+"/subjectController/addSubject";

        if (subId){
            title = "<h2>评价</h2>";
            url = url+"?id="+subId;
        }
        console.log(url);

        layui.layer.open({
            title: title,
            type: 2,
            content: url,
            area: ["500px","500px"],
            maxmin: true
        });
    }






    //绑定行内工具栏
    table.on('tool(subjects)',function (obj){
        var data = obj.data;
        console.log(data.id+"---->"+data);
        var layEvent = obj.event;
        var tr = obj.tr;

        if (layEvent==='edit'){
            //
            var subId = data.id;

            openAddSubjectDialog(subId);
        }
    });





});