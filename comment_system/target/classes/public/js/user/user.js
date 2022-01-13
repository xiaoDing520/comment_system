layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var  tableIns = table.render({
        elem: '#userList',
        url : ctx+'/userController/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80,align:"center"},
            {field: 'userName', title: '用户名', minWidth:50, align:"center"},
            {field: 'sex', title: '性别', minWidth:100, align:'center'},
            {field: 'age', title: '年龄', minWidth:100, align:'center'},
            {field: 'subName', title: '课程', align:'center',minWidth:150},
            {field: 'majorName', title: '专业名称', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });
    /*绑定搜索按钮的点击事件*/
    $(".search_btn").on("click",function (){
        table.reload("userListTable",{
            page: {
                curr:1//重新从第一页开始
            },
            where:{
                userName:$("input[name='userName']").val(),//用户名
            },
            url: ctx+"/userController/list2"
        })
    });
    /**
     * 头部工具栏事件
     */
    table.on("toolbar(users)",function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event){
            case "add":
                openAddOrUpdateUserPage();
                break;
            case "del":
                deleteUser(checkStatus.data);
                break;
        }
    });
    //批量删除用户
    function deleteUser(datas){
        if(datas.length==0){
            layer.msg("请选择要要删除的数据",{icon:5});
            return ;
        }
        layer.confirm("你确定删除当前用户么？",{
            btn:["确认","取消"]//按钮
        },function(index){
            //关闭
            layer.close(index);
            //收集数据
            var ids="ids=";
            for (var i = 0; i < datas.length ; i++) {
                if(i< datas.length -1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }
            //发送ajax删除数据
            $.ajax({
                type:"post",
                url:ctx+"/userController/delete",
                data:ids,
                dataType:"json",
                success:function (data){
                    if (data.code==200){
                        tableIns.reload();
                    }else {
                        layer.msg(data.msg,{icon:5});
                    }
                }
            })
        });
    }

    /**
     * 行内工具栏的绑定
     * 监听行工具事件
     */
    table.on('tool(users)',function (obj) {
        var layEvent = obj.event;
        //监听编辑事件
        if (layEvent === "edit") {
            openAddOrUpdateUserPage(obj.data.id);
        }else if (layEvent === "del"){
            //监听删除事件
            layer.confirm('你确定狠心删除数据吗?',{icon:3,title:"用户管理"},function (index){
                $.post(ctx+"/userController/delete",{ids:obj.data.id},function (data){
                    if (data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                    }else {
                        layer.msg(data.msg,{icon:5});
                    }
                })
            })
        }
    });



    /**
     * 打开用户添加或更新对话框
     */
    function openAddOrUpdateUserPage(userId){
        var url = ctx+"/userController/addOrUpdateUserPage";
        var title = "用户管理-用户添加";
        if(userId) {
            url = url + "?id="+userId;
            title = "用户管理-用户更新"
        }
        layer.open({
            title : title,
            content:url,
            type: 2,
            area:["650px","400px"],
            maxmin:true
        });
    }
});