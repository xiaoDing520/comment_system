layui.use(['form','layer','formSelects'],function (){
    var form = layui.form,
        layer = parent.layui === undefined ? layui.layer:top.layer,
        $ = layui.jquery;
    //引入formSelects模块
    formSelects = layui.formSelects;
    /**
     * 添加或更新新用户
     */
    form.on("submit(addOrUpdateUser)",function (data){
        //弹出loading层
        var index = top.layer.msg('数据提交中，请稍后',{icon: 16,time:false,shade:0.8});
        var url = ctx+"/userController/save";
        if ($("input[name='id']").val()){
            url = ctx + "/userController/update";
        }
        $.post(url,data.field,function (res){
            if (res.code==200){
                setTimeout(function (){
                    //关闭弹出层（返回值为index的弹出层）0
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    //关闭所有ifream层
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                },500);
            }else {
                layer.msg(res.msg,{icon: 5});
            }
        });
        return false;
    });

    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
        parent.layer.close(index);//在执行关闭
    });
    /**
     * 加载下拉框数据
     */
    var userId = $("input[name='id']").val();
    formSelects.config('selectSubId',{
        type:"post",
        searchUrl:ctx+"/subjectController/queryAllSubject?userId="+userId,
        //自定义返回数据中name的key,默认name
        keyName: 'subName',
        //自定义返回数据中value的key,默认value
        keyVal: 'id'
    },true);
    /**
     * 加载下拉框数据
     */
    var userId = $("input[name='id']").val();
    formSelects.config('selectMajorId',{
        type:"post",
        searchUrl:ctx+"/majorController/queryAllMajors?userId="+userId,
        //自定义返回数据中name的key,默认name
        keyName: 'majorName',
        //自定义返回数据中value的key,默认value
        keyVal: 'id'
    },true);

    /**
     * 加载下拉框数据
     */
    var userId = $("input[name='id']").val();
    formSelects.config('selectRole',{
        type:"post",
        searchUrl:ctx+"/RoleController/queryAllRoles?userId="+userId,
        //自定义返回数据中name的key,默认name
        keyName: 'roleName',
        //自定义返回数据中value的key,默认value
        keyVal: 'id'
    },true);
});

