layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx+'/subjectController/su',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'userName', title: '用户名', minWidth:50, align:"center"},
            {field: 'subName', title: '课程名称', minWidth:100, align:'center'},
            {field: 'createDate', title: '开始时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '结束时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });


    /*实现搜索功能，页面重载*/
    $(".search_btn").click(function(){
        //这里以搜索为例
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                userName: $("input[name=userName]").val(),
                subName:$("input[name=subName]").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });



    /*头部工具栏绑定*/
    //头工具栏事件
    table.on('toolbar(users)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);



        switch(obj.event){
            case 'add':
                //layer.msg("添加");
                openAddOrUpdateUserPage();
                break;
            case 'del':
                layer.msg("dels");
                deleterUser(checkStatus.data);
                break;
            case 'end':
                layer.msg("ends");
                endUser(checkStatus.data);
                break;
        };
    });



    /*结束分配*/
    function endUser(datas) {

        if(datas.length==0){
            layer.msg("请选择要结束的数据");
            return ;
        }
        layer.confirm("确定结束吗?",{
            btn:["确认","取消"]
        },function(index){
            //关闭
            layer.close(index);
            //收集数据
            var ids="&ids=";
            for (var i = 0; i < datas.length ; i++) {
                if(i< datas.length -1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }
            console.log(ids);
            //发送ajax删除数据
            $.post(ctx+"/userController/end",ids,function(result){
                if(result.code==200){
                    //重新加载数据
                    tableIns.reload();
                }else{
                    //提示一下
                    layer.msg(result.msg,{icon:5 });
                }
            },"json");
        });
    }


    /**
     * 添加-更新的
     * @param userId
     */
    function openAddOrUpdateUserPage(userId){
        var title="<h2>课程管理---添加</h2>";
        var url=ctx+"/subjectController/add";

        //判断是否修改还是添加
        if(userId){
            title="<h2>课程管理---更新</h2>";
            url=url+"?id="+userId;
        }
        //
        layer.open({
            title:title,
            content:url,
            type:2,//iframe
            area:["650px","400px"],
            maxmin:true,
        })
    }




    /*    /!*行内工具栏的绑定*!/
        //监听行工具事件
        table.on('tool(users)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                //layer.msg("修改")
                openAddOrUpdateUserPage(data.id);
            }
        });*/


    table.on('tool(users)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除么', function(index){
                $.post(ctx + "/userController/delete",{ids:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            });

        } else if(obj.event === 'edit'){
            //layer.msg("修改")
            openAddOrUpdateUserPage(data.id);
        }
    });




    /*删除*/
    function deleterUser(datas){
        if(datas.length==0){
            layer.msg("请选择要要删除的数据");
            return ;
        }
        layer.confirm("确定删除吗?",{
            btn:["确认","取消"]
        },function(index){
            //关闭
            layer.close(index);
            //收集数据
            var ids="&ids=";
            for (var i = 0; i < datas.length ; i++) {
                if(i< datas.length -1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }
            console.log(ids);
            //发送ajax删除数据
            $.post(ctx+"/userController/deleteSubByIds",ids,function(result){
                if(result.code==200){
                    //重新加载数据
                    tableIns.reload();
                }else{
                    //提示一下
                    layer.msg(result.msg,{icon:5 });
                }
            },"json");
        });
    }
});